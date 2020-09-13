/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.dto;

import java.util.Date;
import org.una.laboratorio.dto.DepartamentoDTO;

/**
 *
 * @author Bosco
 */
public class UsuarioDTO {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nombreCompleto;
    private String cedula;
    private boolean estado;
    private String passwordEncriptado;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private DepartamentoDTO departamento;
    private boolean esJefe;

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }

    public boolean isEsJefe() {
        return esJefe;
    }

    public void setEsJefe(boolean esJefe) {
        this.esJefe = esJefe;
    }

    public String getPasswordEncriptado() {
        return passwordEncriptado;
    }

    public void setPasswordEncriptado(String passwordEncriptado) {
        this.passwordEncriptado = passwordEncriptado;
    }
    
    

}
