/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.una.laboratorio.dto.TramiteRegistradoDTO;

/**
 *
 * @author colo7
 */

public class ClienteDTO {
    private Long id; 
    private String nombreCompleto;   
    private String cedula; 
    private String telefono;
    private String direccion;
    private boolean estado; 
    private Date fechaRegistro; 
    private Date fechaModificacion; 
    private String passwordEncriptado;
    private List<TramiteRegistradoDTO> tramitesRegistrados = new ArrayList<>();

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nombreCompleto, String cedula, String telefono, String direccion, boolean estado, Date fechaRegistro, Date fechaModificacion, String passwordEncriptado) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.passwordEncriptado = passwordEncriptado;
    }

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getPasswordEncriptado() {
        return passwordEncriptado;
    }

    public void setPasswordEncriptado(String passwordEncriptado) {
        this.passwordEncriptado = passwordEncriptado;
    }

    public List<TramiteRegistradoDTO> getTramitesRegistrados() {
        return tramitesRegistrados;
    }

    public void setTramitesRegistrados(List<TramiteRegistradoDTO> tramitesRegistrados) {
        this.tramitesRegistrados = tramitesRegistrados;
    }
    
}
