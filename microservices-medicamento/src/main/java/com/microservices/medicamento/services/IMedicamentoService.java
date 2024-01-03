package com.microservices.medicamento.services;

import com.microservices.medicamento.entities.Medicamento;

import java.util.List;

public interface IMedicamentoService {

    List<Medicamento> findAll();

    Medicamento findById(Long id);

    void save(Medicamento medicamento);

    List<Medicamento> findByIdLaboratorio(Long IdLaboratorio);
}
