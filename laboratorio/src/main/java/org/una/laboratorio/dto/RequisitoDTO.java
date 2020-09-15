/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author colo7
 */


public class RequisitoDTO {
    private Long id;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;
    private VariacionDTO variacion;

    public RequisitoDTO(Long id, String descripcion, boolean estado, Date fechaRegistro, VariacionDTO variacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.variacion = variacion;
    }

//    private List<Usuario> usuarios = new ArrayList<>();


    public RequisitoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public VariacionDTO getVariacion() {
        return variacion;
    }

    public void setVariacion(VariacionDTO variacion) {
        this.variacion = variacion;
    }
    
    
}
