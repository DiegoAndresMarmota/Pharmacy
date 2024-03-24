package com.microservices.admin;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.microservices.admin.models.AdminEntity;
import com.microservices.admin.models.ERole;
import com.microservices.admin.models.RoleEntity;
import com.microservices.admin.repositories.AdminRepository;

@SpringBootApplication
public class MicroservicesAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesAdminApplication.class, args);
	}

	 @Bean
    CommandLineRunner init(AdminRepository userRepository) {

            /* CREATE USERS */
            AdminEntity adminPharm = AdminEntity.builder()
                    .adminname("Tanganica")
                    .password("$2a$1Y29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOPqCBshkNfrEf6")
                    .isEnabled(true)
					.accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of())
                    .build();

					AdminEntity techPharm = AdminEntity.builder()
                    .adminname("Tanganana")
                    .password("$2a$10$RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNshkNfrEf6")
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of())
                    .build();

            userRepository.saveAll(List.of(adminPharm, techPharm ));

        };
    }

