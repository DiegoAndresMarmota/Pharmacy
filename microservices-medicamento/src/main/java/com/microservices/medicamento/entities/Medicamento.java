package com.microservices.medicamento.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "medicamentos")
@AllArgsConstructor
@NoArgsConstructor
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_medicamento")
    private String nombreMedicamento;
    @Column(name = "nombre_laboratorio")
    private String nombreLaboratorio;
    @Column(name = "principio_activo")
    private String principioActivo;
    @Column(name = "registro_sanitario")
    private Long registroSanitario;
    private Long concentracion;
    @Column(name = "forma_farmaceutica")
    private String formaFarmaceutica;
    @Column(name = "laboratorio_id")
    private Long laboratorioId;
}
