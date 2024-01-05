package com.microservices.laboratorio.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.microservices.laboratorio.entities.Laboratorio;


@Repository
public interface ILaboratorioRepositorio extends CrudRepository<Laboratorio, Long> {
}
