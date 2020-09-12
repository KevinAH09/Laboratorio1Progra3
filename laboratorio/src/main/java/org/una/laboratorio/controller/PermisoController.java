/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.util.List;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.PermisoDTO;
import org.una.laboratorio.service.PermisoService;
/**
 *
 * @author Bosco
 */
public class PermisoController {
    private final String urlstring = "http://localhost:8099/permisos/";

    public PermisoController() {
    }

    public List<PermisoDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return PermisoService.ListFromConnection(urlstring, PermisoDTO.class);
    }

    public void add(PermisoDTO object) throws InterruptedException, ExecutionException, IOException {
        PermisoService.ObjectToConnection(urlstring, object);
    }

  
    public static PermisoController getInstance() {
        return PermisoControllerHolder.INSTANCE;
    }

    private static class PermisoControllerHolder {

        private static final PermisoController INSTANCE = new PermisoController();
    }
}
