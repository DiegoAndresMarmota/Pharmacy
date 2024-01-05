package com.microservices.laboratorio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "laboratorios")
@AllArgsConstructor
@NoArgsConstructor
public class Laboratorio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_laboratorio")
    private String nombreLaboratorio;

    @Column(name = "rut_laboratorio")
    private Long rutLaboratorio;

    @Column(name = "registro_isp")
    private String registroISP;

    @Column(name = "direccion_laboratorio")
    private String direccion;

    @Column(name = "laboratorio_id")
    private Long laboratorioId;
}

