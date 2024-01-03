package com.microservices.medicamento.persistence;

import com.microservices.medicamento.entities.Medicamento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoRepositorio extends CrudRepository<Medicamento, Long> {

    @Query("SELECT m FROM Medicamento m WHERE m.laboratorioId = :IdLaboratorio")
    List<Medicamento> findAllMedicamento(Long laboratorioId);

}
