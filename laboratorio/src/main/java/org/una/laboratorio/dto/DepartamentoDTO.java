/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.dto;

import java.util.Date;
import java.util.List;
import org.una.laboratorio.dto.UsuarioDTO;


/**
 *
 * @author colo7
 */

public class DepartamentoDTO {

    private Long id;
    private String nombre;
    private boolean estado;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private List<UsuarioDTO> usuarios;

    public DepartamentoDTO() {
    }

    public DepartamentoDTO(Long id, String nombre, boolean estado, Date fechaRegistro, Date fechaModificacion, List<UsuarioDTO> usuarios) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.usuarios = usuarios;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }

}
