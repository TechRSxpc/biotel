package com.biotel.biotelsuites.service;

import com.biotel.biotelsuites.entity.Servicios;
import com.biotel.biotelsuites.repository.ServicioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiciosService {
    
    @Autowired
    ServicioRepository servicioRepository;
    
    public List<Servicios> obtenerTodos(){
        List<Servicios> lista = servicioRepository.findAll();
        return lista;
    }
    
    public Optional<Servicios> obtenerPorId(Long id) {
        return servicioRepository.findById(id);
    }
    
    public Optional<Servicios> obtenerPorNombre(String np){
        return servicioRepository.findBynombreServicio(np);
    }
    
    public void guardar(Servicios servicios){
        servicioRepository.save(servicios);
    }
    
    public void borrar(Long id){
        servicioRepository.deleteById(id);
    }
    
    public boolean existeporNombre(String np){
        return servicioRepository.existsBynombreServicio(np);
    }
    
    public boolean existeporId(Long id){
        return servicioRepository.existsById(id);
    }

}
