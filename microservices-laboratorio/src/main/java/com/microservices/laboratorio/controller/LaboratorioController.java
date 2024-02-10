package com.microservices.laboratorio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
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

@RestController
@RequestMapping("/api/laboratorio")
public class LaboratorioController<SessionRegistryLaboratorio, SessionInformationLaboratorio> {

    @Autowired
    private ILaboratorioService laboratorioService;

    @Autowired
    private SessionRegistryLaboratorio sessionRegistry;

    @SuppressWarnings("unused")
    @Autowired
    private SessionInformationLaboratorio SessionInformation;

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

    @GetMapping("/session-laboratorio")
    public ResponseEntity<?> getDetailsSession(){

        String sessionId = "";
        User userObject = null;

        List<Object> sessions = sessionRegistry.getAllPrincipals();

        for (Object session : sessions) {
            if(session instanceof User) {
                userObject = (User) session;
            }

            List<SessionInformationLaboratorio> sessionInformations = sessionRegistry.getAllSessions(session, false);
        
            for(SessionInformationLaboratorio  sessionInformation : sessionInformations){
                sessionId = sessionInformation.getSessionId();
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("sessionId", sessionId);
        response.put("sessionUser", userObject);

        return ResponseEntity.ok(response);
    }
}
