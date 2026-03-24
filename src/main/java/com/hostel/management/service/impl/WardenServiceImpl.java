package com.hostel.management.service.impl;

import com.hostel.management.dto.request.WardenRequest;
import com.hostel.management.dto.response.WardenResponse;
import com.hostel.management.exception.BadRequestException;
import com.hostel.management.exception.ResourceNotFoundException;
import com.hostel.management.model.User;
import com.hostel.management.model.Warden;
import com.hostel.management.repository.UserRepository;
import com.hostel.management.repository.WardenRepository;
import com.hostel.management.service.WardenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class WardenServiceImpl implements WardenService {

    private final WardenRepository wardenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public WardenServiceImpl(WardenRepository wardenRepository, UserRepository userRepository,
                              PasswordEncoder passwordEncoder) {
        this.wardenRepository = wardenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public WardenResponse createWarden(WardenRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new BadRequestException("Username already exists: " + request.getUsername());
        if (userRepository.existsByEmail(request.getEmail()))
            throw new BadRequestException("Email already registered: " + request.getEmail());

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(User.Role.WARDEN);
        userRepository.save(user);

        Warden warden = new Warden();
        warden.setUser(user);
        wardenRepository.save(warden);
        return toResponse(warden);
    }

    @Override
    @Transactional
    public WardenResponse updateWarden(Long wardenId, WardenRequest request) {
        Warden warden = wardenRepository.findById(wardenId)
                .orElseThrow(() -> new ResourceNotFoundException("Warden", "id", wardenId));
        User user = warden.getUser();

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
        return toResponse(warden);
    }

    @Override
    @Transactional
    public void deleteWarden(Long wardenId) {
        Warden warden = wardenRepository.findById(wardenId)
                .orElseThrow(() -> new ResourceNotFoundException("Warden", "id", wardenId));
        wardenRepository.delete(warden);
        userRepository.delete(warden.getUser());
    }

    @Override
    public WardenResponse getWardenById(Long wardenId) {
        Warden warden = wardenRepository.findById(wardenId)
                .orElseThrow(() -> new ResourceNotFoundException("Warden", "id", wardenId));
        return toResponse(warden);
    }

    @Override
    public List<WardenResponse> getAllWardens() {
        return wardenRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    private WardenResponse toResponse(Warden w) {
        return new WardenResponse(w.getWardenId(), w.getUser().getUserId(),
                w.getUser().getUsername(), w.getUser().getEmail());
    }
}