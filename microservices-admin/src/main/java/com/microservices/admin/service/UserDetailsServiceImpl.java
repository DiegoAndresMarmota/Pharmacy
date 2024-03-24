package com.microservices.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservices.admin.models.AdminEntity;
import com.microservices.admin.repositories.AdminRepository;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String adminname) throws UsernameNotFoundException {
        
        AdminEntity adminEntity = adminRepository.findByAdminname(adminname)
                    .orElseThrow(() -> new UsernameNotFoundException("Admin Not Found with his adminname: " + adminname));
        
        Collection<? extends GrantedAuthority> authorities = adminEntity.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(
                        "ROLE_".concat(role.getName().name()
                    )))
                    .collect(Collectors.toSet());

        return new User(adminEntity.getAdminname(),
                        adminEntity.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        authorities);
    }
}
