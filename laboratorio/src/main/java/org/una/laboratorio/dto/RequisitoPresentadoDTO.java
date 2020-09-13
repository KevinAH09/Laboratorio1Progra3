package org.una.laboratorio.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author colo7
 */


public class RequisitoPresentadoDTO {
    private Long id;
    private Date fechaRegistro;
    private TramiteRegistradoDTO tramiteRegistradoId;
    private RequisitoDTO requisitoId;

    public RequisitoPresentadoDTO(Long id, Date fechaRegistro, TramiteRegistradoDTO tramiteRegistradoId, RequisitoDTO requisitoId) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.tramiteRegistradoId = tramiteRegistradoId;
        this.requisitoId = requisitoId;
    }

    public RequisitoPresentadoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public TramiteRegistradoDTO getTramiteRegistradoId() {
        return tramiteRegistradoId;
    }

    public void setTramiteRegistradoId(TramiteRegistradoDTO tramiteRegistradoId) {
        this.tramiteRegistradoId = tramiteRegistradoId;
    }

    public RequisitoDTO getRequisitoId() {
        return requisitoId;
    }

    public void setRequisitoId(RequisitoDTO requisitoId) {
        this.requisitoId = requisitoId;
    }
    
}
