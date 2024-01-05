package com.microservices.laboratorio.service;

import java.util.List;
import com.microservices.laboratorio.entities.Laboratorio;


public interface ILaboratorioService {

    List<Laboratorio> findAll();

    Laboratorio findById(Long id);

    void save(Laboratorio laboratorio);
    
}
