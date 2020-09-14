/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.dto;

import java.util.Date;

/**
 *
 * @author Bosco
 */
public class VariacionDTO {
    private Long id;
    private String descripcion;
    private Long grupo;
    private boolean estado;
    private Date fechaRegistro;
    private TramiteTipoDTO tramiteTipo;

    public VariacionDTO(Long id, String descripcion, Long grupo, boolean estado, Date fechaRegistro, TramiteTipoDTO tramiteTipo) {
        this.id = id;
        this.descripcion = descripcion;
        this.grupo = grupo;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.tramiteTipo = tramiteTipo;
    }

    public VariacionDTO() {
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

    public Long getGrupo() {
        return grupo;
    }

    public void setGrupo(Long grupo) {
        this.grupo = grupo;
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

    public TramiteTipoDTO getTramiteTipo() {
        return tramiteTipo;
    }

    public void setTramiteTipo(TramiteTipoDTO tramiteTipo) {
        this.tramiteTipo = tramiteTipo;
    }
    
}
