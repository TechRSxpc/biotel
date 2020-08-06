
package com.biotel.biotelsuites.repository;

import com.biotel.biotelsuites.entity.Rol;
import com.biotel.biotelsuites.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}