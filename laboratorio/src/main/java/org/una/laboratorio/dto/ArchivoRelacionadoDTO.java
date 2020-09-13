/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.dto;

import java.sql.Date;

/**
 *
 * @author colo7
 */
public class ArchivoRelacionadoDTO {
    private Long id;
    private TramiteRegistradoDTO tramiteRegistradoId;
    private String nombre;
    private boolean estado;
    private String rutaArchivo;
    private Date fechaRegistro;
    private String etiqueta;
    private Date fechaModificacion;

    public ArchivoRelacionadoDTO(Long id, TramiteRegistradoDTO tramiteRegistradoId, String nombre, boolean estado, String rutaArchivo, Date fechaRegistro, String etiqueta, Date fechaModificacion) {
        this.id = id;
        this.tramiteRegistradoId = tramiteRegistradoId;
        this.nombre = nombre;
        this.estado = estado;
        this.rutaArchivo = rutaArchivo;
        this.fechaRegistro = fechaRegistro;
        this.etiqueta = etiqueta;
        this.fechaModificacion = fechaModificacion;
    }

    public ArchivoRelacionadoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TramiteRegistradoDTO getTramiteRegistradoId() {
        return tramiteRegistradoId;
    }

    public void setTramiteRegistradoId(TramiteRegistradoDTO tramiteRegistradoId) {
        this.tramiteRegistradoId = tramiteRegistradoId;
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

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    
}
