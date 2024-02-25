package com.microservices.admin.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.admin.models.RoleEntity;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRolename(Number name);

    @Query("SELECT re FROM RoleEntity re WHERE re.name = ?1")
    Optional<RoleEntity> getName(Number name);
    
}
