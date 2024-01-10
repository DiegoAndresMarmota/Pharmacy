package com.microservices.laboratorio.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicamentoDTO {

    private String nombreMedicamento;
    private String nombreLaboratorio;
    private String principioActivo;
    private Long registroSanitario;
    private Long concentracion;
    private String formaFarmaceutica;
    private Long laboratorioId;

}