package com.microservices.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;

import com.microservices.admin.security.filters.JwtAuthenticationFilter;
import com.microservices.admin.security.jwt.JwtUtils;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
        
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return httpSecurity
            .csrf(config -> config.disable())
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/admin").hasRole("ADMIN");
                auth.requestMatchers("/user").hasRole("USER");
                auth.requestMatchers("/").permitAll();
                auth.requestMatchers("/medicamentos").permitAll();
                auth.requestMatchers("/higiene-y-cuidado-personal").permitAll();
                auth.requestMatchers("/dermocosmetica").permitAll();
                auth.requestMatchers("/vitaminas-y-suplementos").permitAll();
                auth.requestMatchers("/infantil-y-maternidad").permitAll();
                auth.requestMatchers("/dispositivos-medicos").permitAll();
                auth.requestMatchers("/bebidas-y-alimentos").permitAll();
                auth.requestMatchers("/mundo-mascota").permitAll();
                auth.requestMatchers("/recetario-magistral").permitAll();
                auth.anyRequest().authenticated();
            })
            .sessionManagement(session -> {
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            })
            .addFilter(jwtAuthenticationFilter)
            .build();
    }

    @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
            User.withUsername("JPabloQuezada").password("jpq1901").roles("ADMIN").build());
        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder)
                .and().build();
    }




}
