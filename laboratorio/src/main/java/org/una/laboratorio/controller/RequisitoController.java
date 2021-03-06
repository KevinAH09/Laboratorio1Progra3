/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.util.List;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.RequisitoDTO;
import org.una.laboratorio.service.RequisitoService;

/**
 *
 * @author Bosco
 */
public class RequisitoController {

    private final String urlstring = "http://localhost:8099/requisitos/";

    public RequisitoController() {
    }

    public List<RequisitoDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return RequisitoService.ListFromConnection(urlstring, RequisitoDTO.class);
    }

    public int add(RequisitoDTO object) throws InterruptedException, ExecutionException, IOException {
        return RequisitoService.ObjectToConnection(urlstring, object);
    }

    public Object getId(String id) throws InterruptedException, ExecutionException, IOException {
        return RequisitoService.FromConnectionID(urlstring, id, RequisitoDTO.class);
    }

    public int Update(RequisitoDTO dep) throws InterruptedException, ExecutionException, IOException {
        return RequisitoService.UpdateObjectToConnection(urlstring, dep.getId().toString(), dep);
    }

    public static RequisitoController getInstance() {
        return RequesitoControllerHolder.INSTANCE;
    }

    private static class RequesitoControllerHolder {

        private static final RequisitoController INSTANCE = new RequisitoController();
    }

    public Object getEstado(String estado) throws InterruptedException, ExecutionException, IOException {
        return RequisitoService.FromConnectionEstado(urlstring + "estado/", estado, RequisitoDTO.class);
    }
}
