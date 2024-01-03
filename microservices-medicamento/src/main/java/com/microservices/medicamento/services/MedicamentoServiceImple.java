package com.microservices.medicamento.services;

import com.microservices.medicamento.entities.Medicamento;
import com.microservices.medicamento.persistence.MedicamentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoServiceImple implements IMedicamentoService {

    @Autowired
    private MedicamentoRepositorio medicamentoRepositorio;

    @Override
    public List<Medicamento> findAll() {
        return (List<Medicamento>) medicamentoRepositorio.findAll();
    }

    @Override
    public Medicamento findById(Long id) {
        return medicamentoRepositorio.findById(id).orElseThrow();
    }

    @Override
    public void save(Medicamento medicamento) {
        medicamentoRepositorio.save(medicamento);

    }

    @Override
    public List<Medicamento> findByIdLaboratorio(Long IdLaboratorio) {
        return medicamentoRepositorio.findAllMedicamento(IdLaboratorio);
    }
}
