package com.afroditigkotsi.maritimefpaplatform.config;

import com.afroditigkotsi.maritimefpaplatform.entity.Role;
import com.afroditigkotsi.maritimefpaplatform.entity.User;
import com.afroditigkotsi.maritimefpaplatform.repository.RoleRepository;
import com.afroditigkotsi.maritimefpaplatform.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDataInitializer {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDataInitializer(UserRepository userRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initializeUsers() {

        return args -> {

            createUser(
                    "Admin",
                    "User",
                    "admin@maritimefpa.com",
                    "admin123",
                    "ADMIN"
            );

            createUser(
                    "Manager",
                    "User",
                    "manager@maritimefpa.com",
                    "manager123",
                    "MANAGER"
            );

            createUser(
                    "Analyst",
                    "User",
                    "analyst@maritimefpa.com",
                    "analyst123",
                    "ANALYST"
            );

            System.out.println("=== Users initialized successfully ===");

        };
    }

    private void createUser(String firstName,
                            String lastName,
                            String email,
                            String password,
                            String roleName) {

        if (userRepository.existsByEmail(email)) {
            return;
        }

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() ->
                        new RuntimeException("Role not found: " + roleName));

        User user = new User(
                firstName,
                lastName,
                email,
                passwordEncoder.encode(password)
        );

        user.getRoles().add(role);

        userRepository.save(user);
    }
}