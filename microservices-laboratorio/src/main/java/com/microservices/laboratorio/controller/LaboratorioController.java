package com.microservices.laboratorio.controller;

import java.util.List;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.laboratorio.entities.Laboratorio;
import com.microservices.laboratorio.service.ILaboratorioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;

@RestController
@RequestMapping("/api/laboratorio")
public class LaboratorioController {

    @Autowired
    private ILaboratorioService laboratorioService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMedicamento(@RequestBody Laboratorio laboratorio) {
        laboratorioService.save(laboratorio);
    }
    
    @GetMapping("/search{id}")
    public ResponseEntity<?> findById(@PathVariable Long Id){
        return ResponseEntity.ok(laboratorioService.findById(Id)) ;
    }

    @GetMapping("/search-all")
    public ResponseEntity<?> findAllMedicamento(){
        return ResponseEntity.ok(laboratorioService.findAll());
    }

    @GetMapping("/search-all-medicamento/{laboratorioId}")
    public ResponseEntity<?> findAllMedicamentoByLaboratorio(Long laboratorioId){
        return ResponseEntity.ok(laboratorioService.findAllMedicamentoByLaboratorio(laboratorioId));
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

