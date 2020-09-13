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

public class NotaDTO {
    private Long id;
    private boolean estado;
    private boolean tipo;
    private String titulo;
    private String contenido;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private TramiteRegistradoDTO tramitesRegistrados;

    public NotaDTO(Long id, boolean estado, boolean tipo, String titulo, String contenido, Date fechaRegistro, Date fechaModificacion, TramiteRegistradoDTO tramitesRegistrados) {
        this.id = id;
        this.estado = estado;
        this.tipo = tipo;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaRegistro = fechaRegistro;
        this.fechaModificacion = fechaModificacion;
        this.tramitesRegistrados = tramitesRegistrados;
    }

    public NotaDTO() {
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

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
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

    public TramiteRegistradoDTO getTramitesRegistrados() {
        return tramitesRegistrados;
    }

    public void setTramitesRegistrados(TramiteRegistradoDTO tramitesRegistrados) {
        this.tramitesRegistrados = tramitesRegistrados;
    }
    
}
