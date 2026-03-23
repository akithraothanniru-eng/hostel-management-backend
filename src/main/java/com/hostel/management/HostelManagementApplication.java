package com.hostel.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hostel.management.model.User;
import com.hostel.management.model.User.Role;
import com.hostel.management.repository.UserRepository;

@SpringBootApplication
public class HostelManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HostelManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner createAdmin(UserRepository repo) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(encoder.encode("admin123")); // 🔐 encrypted
                admin.setRole(Role.ADMIN);

                repo.save(admin);

                System.out.println("✅ Admin user created!");
            } else {
                System.out.println("⚠️ Admin already exists");
            }
        };
    }
}