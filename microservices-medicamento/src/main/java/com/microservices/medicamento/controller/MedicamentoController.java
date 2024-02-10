package com.microservices.medicamento.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class MedicamentoController<SessionRegistryMedicamento, SessionInformationMedicamento> {
    
    @Autowired
    private IMedicamentoService medicamentoService;

    @SuppressWarnings("unused")
    @Autowired
    private SessionRegistryMedicamento sessionRegistry;

    @SuppressWarnings("unused")
    @Autowired
    private SessionInformationMedicamento SessionInformation;
    
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

    @GetMapping("/session-laboratorio")
    public ResponseEntity<?> getDetailsSession(){

        String sessionId = "";
        User userObject = null;

        List<Object> sessions = sessionRegistry.getAllPrincipals();

        for (Object session : sessions) {
            if(session instanceof User) {
                userObject = (User) session;
            }

            List<SessionInformationMedicamento> sessionInformations = sessionRegistry.getAllSessions(session, false);
        
            for(SessionInformationMedicamento sessionInformation : sessionInformations){
                sessionId = sessionInformation.getSessionId();
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("sessionId", sessionId);
        response.put("sessionUser", userObject);

        return ResponseEntity.ok(response);
    }

}
