/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.util.List;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.TransaccionDTO;
import org.una.laboratorio.service.TransaccionService;

/**
 *
 * @author Bosco
 */
public class TransaccionController {

    private final String urlstring = "http://localhost:8099/transacciones/";

    public TransaccionController() {
    }

    public List<TransaccionDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return TransaccionService.ListFromConnection(urlstring, TransaccionDTO.class);
    }

    public void add(TransaccionDTO object) throws InterruptedException, ExecutionException, IOException {
        TransaccionService.ObjectToConnection(urlstring, object);
    }

    public Object getId(String id) throws InterruptedException, ExecutionException, IOException {
        return TransaccionService.FromConnectionID(urlstring, id, TransaccionDTO.class);
    }

    public void Update(TransaccionDTO dep) throws InterruptedException, ExecutionException, IOException {
        TransaccionService.UpdateObjectToConnection(urlstring, dep.getId().toString(), dep);
    }

    public static TransaccionController getInstance() {
        return TransaccionControllerHolder.INSTANCE;
    }

    private static class TransaccionControllerHolder {

        private static final TransaccionController INSTANCE = new TransaccionController();
    }
}
