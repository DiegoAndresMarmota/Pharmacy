package com.microservices.laboratorio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.microservices.laboratorio.entities.Laboratorio;
import com.microservices.laboratorio.persistence.ILaboratorioRepositorio;


public class LaboratorioServiceImple implements ILaboratorioService {

    @Autowired
    private ILaboratorioRepositorio laboratorioRepositorio;

    @Override
    public List<Laboratorio> findAll() {
       return (List<Laboratorio>) laboratorioRepositorio.findAll();
    }

    @Override
    public Laboratorio findById(Long id) {
        return laboratorioRepositorio.findById(id).orElseThrow();
    }

    @Override
    public void save(Laboratorio laboratorio) {
        laboratorioRepositorio.save(laboratorio);

    }
        
    
}
