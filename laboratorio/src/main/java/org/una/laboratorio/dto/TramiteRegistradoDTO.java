/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author colo7
 */

public class TramiteRegistradoDTO {
    private Long id;
    private TramiteTipoDTO tramitesTiposId;
    private ClienteDTO clienteId;
    private List<NotaDTO> notas = new ArrayList<>();
    private List<ArchivoRelacionadoDTO> archivosRelacionados = new ArrayList<>();
    private List<RequisitoPresentadoDTO> requisitosPresentados = new ArrayList<>();

    public TramiteRegistradoDTO(Long id, TramiteTipoDTO tramitesTiposId, ClienteDTO clienteId) {
        this.id = id;
        this.tramitesTiposId = tramitesTiposId;
        this.clienteId = clienteId;
    }

    public TramiteRegistradoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TramiteTipoDTO getTramitesTiposId() {
        return tramitesTiposId;
    }

    public void setTramitesTiposId(TramiteTipoDTO tramitesTiposId) {
        this.tramitesTiposId = tramitesTiposId;
    }

    public ClienteDTO getClienteId() {
        return clienteId;
    }

    public void setClienteId(ClienteDTO clienteId) {
        this.clienteId = clienteId;
    }

    public List<NotaDTO> getNotas() {
        return notas;
    }

    public void setNotas(List<NotaDTO> notas) {
        this.notas = notas;
    }

    public List<ArchivoRelacionadoDTO> getArchivosRelacionados() {
        return archivosRelacionados;
    }

    public void setArchivosRelacionados(List<ArchivoRelacionadoDTO> archivosRelacionados) {
        this.archivosRelacionados = archivosRelacionados;
    }

    public List<RequisitoPresentadoDTO> getRequisitosPresentados() {
        return requisitosPresentados;
    }

    public void setRequisitosPresentados(List<RequisitoPresentadoDTO> requisitosPresentados) {
        this.requisitosPresentados = requisitosPresentados;
    }
    
}
