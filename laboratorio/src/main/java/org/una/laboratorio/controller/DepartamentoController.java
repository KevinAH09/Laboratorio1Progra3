/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.DepartamentoDTO;
import org.una.laboratorio.service.DepartementoService;

/**
 *
 * @author colo7
 */
public class DepartamentoController {
    private final String urlstring = "http://localhost:8099/departamentos/";

    public DepartamentoController() {
    }

    public List<DepartamentoDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return DepartementoService.ListFromConnection(urlstring, DepartamentoDTO.class);
    }

    public void add(DepartamentoDTO object) throws InterruptedException, ExecutionException, IOException {
        DepartementoService.ObjectToConnection(urlstring, object);
    }

  
    public static DepartamentoController getInstance() {
        return DepartamentoControllerHolder.INSTANCE;
    }

    private static class DepartamentoControllerHolder {

        private static final DepartamentoController INSTANCE = new DepartamentoController();
    }
}
