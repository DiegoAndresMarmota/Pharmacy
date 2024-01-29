package com.microservices.medicamento.controller;

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

}
