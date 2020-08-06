
package com.biotel.biotelsuites.entity;

import java.io.InputStream;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "servicios")
public class Servicios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank
    @Column (unique = true)
    private String nombreServicio;
    
    @NotBlank
    private InputStream foto;
    
    @NotBlank
    private String descripcion;
    
    @NotBlank
    private String categoria;
    
    @NotNull
    private double precio;
    
    @NotNull
    private int stock;
    
    public Servicios() {
    }

    public Servicios(long id, String nombreServicio, InputStream foto, String descripcion, double precio, int stock) {
        this.id = id;
        this.nombreServicio = nombreServicio;
        this.foto = foto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
