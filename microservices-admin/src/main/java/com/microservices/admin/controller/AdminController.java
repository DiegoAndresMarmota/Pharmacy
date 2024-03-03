package com.microservices.admin.controller;

import java.util.stream.Collectors;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.admin.controller.request.CreateAdminDTO;
import com.microservices.admin.models.AdminEntity;
import com.microservices.admin.models.ERole;
import com.microservices.admin.models.RoleEntity;
import com.microservices.admin.repositories.AdminRepository;
import jakarta.validation.Valid;

@RestController
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/secured")
    public String Secured(){
        return "route secured";
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody CreateAdminDTO createAdminDTO){

        Set<RoleEntity> roles = createAdminDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        AdminEntity adminEntity = AdminEntity.builder()
                .adminname(createAdminDTO.getAdminname())
                .password(passwordEncoder.encode(createAdminDTO.getPassword()))
                .email(createAdminDTO.getEmail())
                .roles(roles)
                .build();

        adminRepository.save(null);

        return ResponseEntity.ok(adminEntity);

    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        adminRepository.deleteById(Long.parseLong(id));
        return "Admin eliminado: ".concat(id);
    }
}
