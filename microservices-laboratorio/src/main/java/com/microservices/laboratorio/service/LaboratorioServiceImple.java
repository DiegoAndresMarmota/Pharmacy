package com.microservices.laboratorio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.microservices.laboratorio.client.MedicamentoClient;
import com.microservices.laboratorio.dto.MedicamentoDTO;
import com.microservices.laboratorio.entities.Laboratorio;
import com.microservices.laboratorio.http.response.MedicamentoByLaboratorioResponse;
import com.microservices.laboratorio.persistence.ILaboratorioRepositorio;


public class LaboratorioServiceImple implements ILaboratorioService {

    @Autowired
    private ILaboratorioRepositorio laboratorioRepositorio;

    @Autowired
    private MedicamentoClient medicamentoClient;

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

    @Override
    public MedicamentoByLaboratorioResponse findAllMedicamentoByLaboratorio(Long laboratorioId) {
        
        Laboratorio laboratorio = Laboratorio.findById(laboratorioId).orElse(new Laboratorio());
        
        List<MedicamentoDTO> medicamentoDTO = medicamentoClient.findAllMedicamentoByLaboratorio(laboratorioId);
        
        return MedicamentoByLaboratorioResponse.builder()
                .nombreLaboratorio(laboratorio.getNombreLaboratorio())
                .rutLaboratorio(laboratorio.getRutLaboratorio())
                .medicamentoDTOList(medicamentoDTO)
                .build();

    }
        
}

