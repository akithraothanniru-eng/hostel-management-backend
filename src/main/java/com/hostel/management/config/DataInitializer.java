package com.hostel.management.config;

import com.hostel.management.model.User;
import com.hostel.management.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Seeds the default admin user on first startup if it doesn't already exist.
 * This replaces the MySQL-syntax schema.sql seed which doesn't work on PostgreSQL.
 *
 * Default credentials:
 *   username : admin
 *   password : admin123
 *   email    : admin@hostel.com
 */
@Component
public class DataInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@hostel.com");
            admin.setRole(User.Role.ADMIN);
            userRepository.save(admin);
            log.info("✅ Default admin user created (username: admin, password: admin123)");
        } else {
            log.info("ℹ️  Admin user already exists — skipping seed");
        }
    }
}