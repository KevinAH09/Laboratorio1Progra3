/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.ArchivoRelacionadoDTO;
import org.una.laboratorio.service.ArchivoRelacionadoService;

/**
 *
 * @author Bosco
 */
public class ArchivoRelacionadoController {

    private final String urlstring = "http://localhost:8099/archivos_relacionados/";

    public ArchivoRelacionadoController() {
    }

    public List<ArchivoRelacionadoDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return ArchivoRelacionadoService.ListFromConnection(urlstring, ArchivoRelacionadoDTO.class);
    }

    public void add(ArchivoRelacionadoDTO object) throws InterruptedException, ExecutionException, IOException {
        ArchivoRelacionadoService.ObjectToConnection(urlstring, object);
    }

    public Object getId(String id) throws InterruptedException, ExecutionException, IOException {
        return ArchivoRelacionadoService.FromConnectionID(urlstring, id, ArchivoRelacionadoDTO.class);
    }

    public void Update(ArchivoRelacionadoDTO dep) throws InterruptedException, ExecutionException, IOException {
        ArchivoRelacionadoService.UpdateObjectToConnection(urlstring, dep.getId().toString(), dep);
    }

    public static DepartamentoController getInstance() {
        return DepartamentoControllerHolder.INSTANCE;
    }

    private static class DepartamentoControllerHolder {

        private static final DepartamentoController INSTANCE = new DepartamentoController();
    }
}
