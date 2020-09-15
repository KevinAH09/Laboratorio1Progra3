/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.util.List;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.DepartamentoDTO;
import org.una.laboratorio.dto.VariacionDTO;
import org.una.laboratorio.service.DepartementoService;
import org.una.laboratorio.service.VariacionService;

/**
 *
 * @author Bosco
 */
public class VariacionController {

    private final String urlstring = "http://localhost:8099/variaciones/";

    public VariacionController() {
    }

    public List<VariacionDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return VariacionService.ListFromConnection(urlstring, VariacionDTO.class);
    }

    public int add(VariacionDTO object) throws InterruptedException, ExecutionException, IOException {
        return VariacionService.ObjectToConnection(urlstring, object);
    }

    public Object getId(String id) throws InterruptedException, ExecutionException, IOException {
        return VariacionService.FromConnectionID(urlstring, id, VariacionDTO.class);
    }

    public int Update(VariacionDTO dep) throws InterruptedException, ExecutionException, IOException {
        return VariacionService.UpdateObjectToConnection(urlstring, dep.getId().toString(), dep);
    }

    public static VariacionController getInstance() {
        return VariacionControllerHolder.INSTANCE;
    }

    private static class VariacionControllerHolder {

        private static final VariacionController INSTANCE = new VariacionController();
    }

    public Object getEstado(String estado) throws InterruptedException, ExecutionException, IOException {
        return VariacionService.FromConnectionEstado(urlstring + "estado/", estado, VariacionDTO.class);
    }

    public Object getTramiteTipo(String tramiteTipoId) throws InterruptedException, ExecutionException, IOException {
        return VariacionService.FromConnectionTramiteTipo(urlstring + "tramiteTipo_id/", tramiteTipoId, VariacionDTO.class);
    }
}
