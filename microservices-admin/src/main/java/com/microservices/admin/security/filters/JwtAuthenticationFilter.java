package com.microservices.admin.security.filters;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.microservices.admin.models.AdminEntity;
import com.microservices.admin.security.jwt.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    private JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {

        AdminEntity adminEntity = null;
        String adminname = "";
        String password = "";

        try{
            adminEntity = new ObjectMapper().readValue(request.getInputStream(), AdminEntity.class);
            adminname = adminEntity.getAdminname();
            password = adminEntity.getPassword();
        }catch(StreamReadException e){
            throw new RuntimeException(e);
        }catch(DatabindException e){
            throw new RuntimeException(e);
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(adminname, password);
        
        return getAuthenticationManager().authenticate(authenticationToken);

    }

    
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    
        User admin = (User) authResult.getPrincipal();
        String token = jwtUtils.generateAccessToken(admin.getUsername());
        
        response.addHeader("Authorization", "Bearer " + token);

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("Token", token);
        httpResponse.put("Message", "Login Successful");
        httpResponse.put("Status", 200);
        httpResponse.put("Admin", admin.getUsername());

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush();

        return;
    }
}
