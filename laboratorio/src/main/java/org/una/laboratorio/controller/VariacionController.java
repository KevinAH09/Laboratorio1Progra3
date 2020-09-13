/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.util.List;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.VariacionDTO;
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

    public void add(VariacionDTO object) throws InterruptedException, ExecutionException, IOException {
        VariacionService.ObjectToConnection(urlstring, object);
    }

    public Object getId(String id) throws InterruptedException, ExecutionException, IOException {
        return VariacionService.FromConnectionID(urlstring, id, VariacionDTO.class);
    }

    public void Update(VariacionDTO dep) throws InterruptedException, ExecutionException, IOException {
        VariacionService.UpdateObjectToConnection(urlstring, dep.getId().toString(), dep);
    }

    public static VariacionController getInstance() {
        return VariacionControllerHolder.INSTANCE;
    }

    private static class VariacionControllerHolder {

        private static final VariacionController INSTANCE = new VariacionController();
    }
}
