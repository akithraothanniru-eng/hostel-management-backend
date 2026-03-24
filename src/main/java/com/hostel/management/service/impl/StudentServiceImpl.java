package com.hostel.management.service.impl;

import com.hostel.management.dto.request.StudentRequest;
import com.hostel.management.dto.response.StudentResponse;
import com.hostel.management.exception.BadRequestException;
import com.hostel.management.exception.ResourceNotFoundException;
import com.hostel.management.model.Room;
import com.hostel.management.model.Student;
import com.hostel.management.model.User;
import com.hostel.management.repository.RoomRepository;
import com.hostel.management.repository.StudentRepository;
import com.hostel.management.repository.UserRepository;
import com.hostel.management.service.StudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository,
                               RoomRepository roomRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public StudentResponse createStudent(StudentRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new BadRequestException("Username already exists: " + request.getUsername());
        if (userRepository.existsByEmail(request.getEmail()))
            throw new BadRequestException("Email already registered: " + request.getEmail());

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(User.Role.STUDENT);
        userRepository.save(user);

        Student student = new Student();
        student.setUser(user);
        student.setName(request.getName());
        student.setPhone(request.getPhone());
        student.setCourse(request.getCourse());
        student.setYear(request.getYear());

        if (request.getRoomId() != null) {
            Room room = roomRepository.findById(request.getRoomId())
                    .orElseThrow(() -> new ResourceNotFoundException("Room", "id", request.getRoomId()));
            validateRoomCapacity(room);
            student.setRoom(room);
        }

        studentRepository.save(student);
        return toResponse(student);
    }

    @Override
    @Transactional
    public StudentResponse updateStudent(Long studentId, StudentRequest request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        User user = student.getUser();

        if (!user.getUsername().equals(request.getUsername()) &&
                userRepository.existsByUsername(request.getUsername()))
            throw new BadRequestException("Username already exists: " + request.getUsername());
        if (!user.getEmail().equals(request.getEmail()) &&
                userRepository.existsByEmail(request.getEmail()))
            throw new BadRequestException("Email already registered: " + request.getEmail());

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isBlank())
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        student.setName(request.getName());
        student.setPhone(request.getPhone());
        student.setCourse(request.getCourse());
        student.setYear(request.getYear());

        if (request.getRoomId() != null) {
            Room room = roomRepository.findById(request.getRoomId())
                    .orElseThrow(() -> new ResourceNotFoundException("Room", "id", request.getRoomId()));
            if (student.getRoom() == null || !student.getRoom().getRoomId().equals(request.getRoomId()))
                validateRoomCapacity(room);
            student.setRoom(room);
        } else {
            student.setRoom(null);
        }

        studentRepository.save(student);
        return toResponse(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        studentRepository.delete(student);
        userRepository.delete(student.getUser());
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        return toResponse(student);
    }

    @Override
    public StudentResponse getStudentByUserId(Long userId) {
        Student student = studentRepository.findByUserUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "userId", userId));
        return toResponse(student);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentResponse assignRoom(Long studentId, Long roomId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));
        if (student.getRoom() == null || !student.getRoom().getRoomId().equals(roomId))
            validateRoomCapacity(room);
        student.setRoom(room);
        studentRepository.save(student);
        return toResponse(student);
    }

    private void validateRoomCapacity(Room room) {
        long occupancy = studentRepository.findByRoomRoomId(room.getRoomId()).size();
        if (occupancy >= room.getCapacity())
            throw new BadRequestException("Room " + room.getRoomNumber() + " is at full capacity");
    }

    public StudentResponse toResponse(Student s) {
        StudentResponse r = new StudentResponse();
        r.setStudentId(s.getStudentId());
        if (s.getUser() != null) {
            r.setUserId(s.getUser().getUserId());
            r.setUsername(s.getUser().getUsername());
            r.setEmail(s.getUser().getEmail());
        }
        r.setName(s.getName());
        r.setPhone(s.getPhone());
        r.setCourse(s.getCourse());
        r.setYear(s.getYear());
        if (s.getRoom() != null) {
            r.setRoomId(s.getRoom().getRoomId());
            r.setRoomNumber(s.getRoom().getRoomNumber());
        }
        return r;
    }
}