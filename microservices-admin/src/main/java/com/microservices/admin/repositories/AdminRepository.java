package com.microservices.admin.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.admin.models.AdminEntity;

@Repository
public interface AdminRepository extends CrudRepository<AdminRepository, Long> {

    Optional<AdminEntity> findByAdminname(String adminname);

    @Query("SELECT ad FROM AdminEntity ad WHERE ad.adminname = ?1")
    Optional<AdminEntity> getName(String adminname);
    
}
