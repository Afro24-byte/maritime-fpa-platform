package com.afroditigkotsi.maritimefpaplatform.config;

import com.afroditigkotsi.maritimefpaplatform.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.afroditigkotsi.maritimefpaplatform.entity.Role;

@Configuration
public class DataInitializer {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Bean
    CommandLineRunner init() {

        return args -> {

            if (!roleRepository.existsByName("ADMIN")) {
                roleRepository.save(new Role(
                        "ADMIN",
                        "System Administrator"
                ));
            }

            if (!roleRepository.existsByName("MANAGER")) {
                roleRepository.save(new Role(
                        "MANAGER",
                        "Fleet and Financial Manager"
                ));
            }

            if (!roleRepository.existsByName("ANALYST")) {
                roleRepository.save(new Role(
                        "ANALYST",
                        "FP&A Analyst"
                ));
            }

            System.out.println("=== Roles initialized successfully ===");

        };
    }
}