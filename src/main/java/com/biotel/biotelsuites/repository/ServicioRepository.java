
package com.biotel.biotelsuites.repository;

import com.biotel.biotelsuites.entity.Servicios;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioRepository extends JpaRepository<Servicios, Long>{
    Optional<Servicios> findBynombreServicio(String np);
    boolean existsBynombreServicio(String np);
}
