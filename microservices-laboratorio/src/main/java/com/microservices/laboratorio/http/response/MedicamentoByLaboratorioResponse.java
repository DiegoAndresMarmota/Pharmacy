package com.microservices.laboratorio.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import com.microservices.laboratorio.dto.MedicamentoDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicamentoByLaboratorioResponse {

    private String nombreLaboratorio;
    private Long rutLaboratorio;
    private List<MedicamentoDTO> medicamentoDTOList;

}