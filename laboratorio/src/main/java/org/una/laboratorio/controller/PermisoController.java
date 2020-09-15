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

    public int add(PermisoDTO object) throws InterruptedException, ExecutionException, IOException {
        return PermisoService.ObjectToConnection(urlstring, object);
    }

    public Object getId(String id) throws InterruptedException, ExecutionException, IOException {
        return PermisoService.FromConnectionID(urlstring, id, PermisoDTO.class);
    }

    public Object getEstado(String estado) throws InterruptedException, ExecutionException, IOException {
        return PermisoService.FromConnectionEstado(urlstring + "estado/", estado, PermisoDTO.class);
    }
    public Object getCodigo(String codigo) throws InterruptedException, ExecutionException, IOException {
        return PermisoService.FromConnectionCodigo(urlstring + "codigo/", codigo, PermisoDTO.class);
    }

    public int  Update(PermisoDTO dep) throws InterruptedException, ExecutionException, IOException {
        return PermisoService.UpdateObjectToConnection(urlstring, dep.getId().toString(), dep);
    }

    public static PermisoController getInstance() {
        return PermisoControllerHolder.INSTANCE;
    }

    private static class PermisoControllerHolder {

        private static final PermisoController INSTANCE = new PermisoController();
    }
}
