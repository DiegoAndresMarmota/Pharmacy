package com.microservices.laboratorio.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.laboratorio.dto.MedicamentoDTO;

@FeignClient(
    name = "microservices-medicamento", 
    url = "localhost:8090//api/medicamento"  
)
public interface MedicamentoClient {

    @GetMapping("/searchByLaboratorio/{laboratorioId}")
    List<MedicamentoDTO> findAllMedicamentoByLaboratorio(@PathVariable Long laboratorioId);
    
}
