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
 * @author cfugu
 */

public class TransaccionDTO {

    private Long id;
    private String objeto;
    private String informacion;
    private PermisoOtorgadoDTO permisoOtorgadoId;
    private Date fechaRegistro;

    public TransaccionDTO(Long id, String objeto, String informacion, PermisoOtorgadoDTO permisoOtorgadoId, Date fechaRegistro) {
        this.id = id;
        this.objeto = objeto;
        this.informacion = informacion;
        this.permisoOtorgadoId = permisoOtorgadoId;
        this.fechaRegistro = fechaRegistro;
    }

    public TransaccionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public PermisoOtorgadoDTO getPermisoOtorgadoId() {
        return permisoOtorgadoId;
    }

    public void setPermisoOtorgadoId(PermisoOtorgadoDTO permisoOtorgadoId) {
        this.permisoOtorgadoId = permisoOtorgadoId;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

}
