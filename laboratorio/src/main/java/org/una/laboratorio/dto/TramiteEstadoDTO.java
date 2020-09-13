/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author colo7
 */

public class TramiteEstadoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String estadosSucesores;

    public TramiteEstadoDTO(Long id, String nombre, String descripcion, String estadosSucesores) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estadosSucesores = estadosSucesores;
    }

    public TramiteEstadoDTO() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadosSucesores() {
        return estadosSucesores;
    }

    public void setEstadosSucesores(String estadosSucesores) {
        this.estadosSucesores = estadosSucesores;
    }
    
}
