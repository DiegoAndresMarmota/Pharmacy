package com.microservices.medicamento.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.medicamento.entities.Medicamento;
import com.microservices.medicamento.service.IMedicamentoService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/medicamento")
public class MedicamentoController {
    
    @Autowired
    private IMedicamentoService medicamentoService;

    @Autowired
    private SessionRegistry sessionRegistry;
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void saveMedicamento(@RequestBody Medicamento medicamento){
        medicamentoService.save(medicamento);
    }

    @GetMapping("/search{id}")
    public ResponseEntity<?> findById(@PathVariable Long Id){
        return ResponseEntity.ok(medicamentoService.findById(Id)) ;
    }

    @GetMapping("/searchAll")
    public ResponseEntity<?> findAllMedicamento(){
        return ResponseEntity.ok(medicamentoService.findAll());
    }

    @GetMapping("/searchByLaboratorio/{laboratorioId}")
    public ResponseEntity<?> findByIdLaboratorio(@PathVariable Long laboratorioId){
        return ResponseEntity.ok(medicamentoService.findByIdLaboratorio(laboratorioId));
    }

    @GetMapping("/session")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getDetailsSession(){

        String sessionId = "";
        User userObject = null;

        List<Object> sessions = sessionRegistry.getAllSessions();

        for(Object session : sessions){
            if (session instanceof User) {
                userObject = (User) session;
            }
            
            List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(session, false);
        
            for(SessionInformation sessionInformation: sessionInformations){
                sessionId = sessionInformation.getSessionId();
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("sessionId", sessionId);
        response.put("sessionUser", userObject);

        return ResponseEntity.ok(response);
    }
}
