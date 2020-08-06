
package com.biotel.biotelsuites.service;

import com.biotel.biotelsuites.entity.Rol;
import com.biotel.biotelsuites.enums.RolNombre;
import com.biotel.biotelsuites.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }
}