package com.microservices.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
            .authorizeHttpRequests(authorize -> {
                authorize.requestMatchers("/admin/auth/**").permitAll();
                authorize.anyRequest().authenticated();
            })
            .formLogin(form -> form
                .successHandler(successHandlerOK())
			    .loginPage("/login")
			    .permitAll()
		    )
            .sessionManagement((session) -> session
                .sessionFixation().migrateSession() 
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/invalidSession")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/login")
                .sessionRegistry(null)
            )
            .logout(logout -> logout
                .deleteCookies("JSESSIONID")
            )
            .build();
    }

    public AuthenticationSuccessHandler successHandlerOK(){
        return ((request, response, authentication) -> {
            response.setStatus(200);
            response.getWriter().write("Login Successful");
            response.getWriter().flush();
            response.getWriter().close();
        });
    }
}
