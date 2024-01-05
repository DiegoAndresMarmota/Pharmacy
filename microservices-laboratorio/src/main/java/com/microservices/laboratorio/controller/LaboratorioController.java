package com.microservices.laboratorio.controller;

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

@RestController
@RequestMapping("/api/laboratorio")
public class LaboratorioController {

    @Autowired
    private ILaboratorioService laboratorioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMedicamento(@RequestBody Laboratorio laboratorio) {
        laboratorioService.save(laboratorio);
    }
    
    @GetMapping("/search{id}")
    public ResponseEntity<?> findById(@PathVariable Long Id){
        return ResponseEntity.ok(laboratorioService.findById(Id)) ;
    }

    @GetMapping("/searchAll")
    public ResponseEntity<?> findAllMedicamento(){
        return ResponseEntity.ok(laboratorioService.findAll());
    }




}
