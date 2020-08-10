
package com.biotel.biotelsuites.controller;

import com.biotel.biotelsuites.DTO.Mensaje;
import com.biotel.biotelsuites.entity.Servicios;
import com.biotel.biotelsuites.service.ServiciosService;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/Servicios")
public class ServiciosController {

    @Autowired
    ServiciosService serviciosService;

    @GetMapping("/lista")
    public ResponseEntity<List<Servicios>> getLista() {
        List<Servicios> lista = serviciosService.obtenerTodos();
        return new ResponseEntity<List<Servicios>>(lista, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Servicios> getOne(@PathVariable Long id) {
        if (!serviciosService.existeporId(id))
            return new ResponseEntity(new Mensaje("no existe este servicio"), HttpStatus.NOT_FOUND);
        Servicios servicios = serviciosService.obtenerPorId(id).get();
        return new ResponseEntity<Servicios>(servicios, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> create(@RequestBody Servicios servicios) {
        if (StringUtils.isBlank(servicios.getNombreServicio()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(servicios.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        if ((Double) servicios.getPrecio() == null || servicios.getPrecio() == 0)
            return new ResponseEntity(new Mensaje("Precio Obligatorio"), HttpStatus.BAD_REQUEST);
        if ((Integer) servicios.getStock() == null || servicios.getStock() == 0)
            return new ResponseEntity(new Mensaje("Disponibilidad obligatoria"), HttpStatus.BAD_REQUEST);
        if (serviciosService.existeporNombre(servicios.getNombreServicio()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        serviciosService.guardar(servicios);
        return new ResponseEntity(new Mensaje("servicios guardado"), HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@RequestBody Servicios servicios, @PathVariable("id") Long id) {
        if (!serviciosService.existeporId(id))
            return new ResponseEntity(new Mensaje("no existe ese producto"), HttpStatus.NOT_FOUND);
        if (StringUtils.isBlank(servicios.getNombreServicio()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(servicios.getDescripcion()))
            return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        if ((Double) servicios.getPrecio() == null || servicios.getPrecio() == 0)
            return new ResponseEntity(new Mensaje("Precio Obligatorio"), HttpStatus.BAD_REQUEST);
        if ((Integer) servicios.getStock() == null || servicios.getStock() == 0)
            return new ResponseEntity(new Mensaje("Disponibilidad obligatoria"), HttpStatus.BAD_REQUEST);
        if (serviciosService.existeporNombre(servicios.getNombreServicio())
                && serviciosService.obtenerPorNombre(servicios.getNombreServicio()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Servicios serUpdate = serviciosService.obtenerPorId(id).get();
        serUpdate.setNombreServicio(servicios.getNombreServicio());
        serUpdate.setDescripcion(servicios.getDescripcion());
        serUpdate.setPrecio(servicios.getPrecio());
        serUpdate.setStock(servicios.getStock());
        serviciosService.guardar(serUpdate);
        return new ResponseEntity(new Mensaje("servicio actualizado"), HttpStatus.CREATED);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!serviciosService.existeporId(id))
            return new ResponseEntity(new Mensaje("no existe ese servicio"), HttpStatus.NOT_FOUND);
        serviciosService.borrar(id);
        return new ResponseEntity(new Mensaje("servicio eliminado"), HttpStatus.OK);
    }

    @PostMapping("/nuevo/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
        
        Servicios servicios = serviciosService.obtenerPorId(id).get();
        
        if(!archivo.isEmpty()) {
            String NombreArchivo = archivo.getOriginalFilename();
            Path rutaArchivo = Paths.get("image").resolve(NombreArchivo).toAbsolutePath();

            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            servicios.setFoto(NombreArchivo);

            serviciosService.guardar(servicios);
        }
        return new ResponseEntity(new Mensaje("servicio guardado"), HttpStatus.CREATED);
    }
}