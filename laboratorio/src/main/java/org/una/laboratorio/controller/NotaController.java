/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.NotaDTO;
import org.una.laboratorio.service.NotaService;

/**
 *
 * @author Bosco
 */
public class NotaController {
    private final String urlstring = "http://localhost:8099/notas/";

    public NotaController() {
    }

    public List<NotaDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return NotaService.ListFromConnection(urlstring, NotaDTO.class);
    }

    public void add(NotaDTO object) throws InterruptedException, ExecutionException, IOException {
        NotaService.ObjectToConnection(urlstring, object);
    }

  
    public static NotaController getInstance() {
        return NotaControllerHolder.INSTANCE;
    }

    private static class NotaControllerHolder {

        private static final NotaController INSTANCE = new NotaController();
    }
}
