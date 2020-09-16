/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.dto;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author cfugu
 */

public class PermisoOtorgadoDTO {

    private Long id;
    private boolean estado;
    private Date fechaRegistro;
    private PermisoDTO permisoId;
    private UsuarioDTO usuario;

    public PermisoOtorgadoDTO(Long id, boolean estado, Date fechaRegistro, PermisoDTO permisoId, UsuarioDTO usuarios) {
        this.id = id;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.permisoId = permisoId;
        this.usuario = usuarios;
    }

    public PermisoOtorgadoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PermisoDTO getPermisoId() {
        return permisoId;
    }

    public void setPermisoId(PermisoDTO permisoId) {
        this.permisoId = permisoId;
    }

    public UsuarioDTO getUsuarios() {
        return usuario;
    }

    public void setUsuarios(UsuarioDTO usuarios) {
        this.usuario = usuarios;
    }
    
}
