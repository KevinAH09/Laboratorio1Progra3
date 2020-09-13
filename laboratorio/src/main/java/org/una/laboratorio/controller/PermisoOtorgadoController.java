/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.util.List;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.PermisoOtorgadoDTO;
import org.una.laboratorio.service.PermisoOtorgadoService;

/**
 *
 * @author Bosco
 */
public class PermisoOtorgadoController {

    private final String urlstring = "http://localhost:8099/permisos_Otorgados/";

    public PermisoOtorgadoController() {
    }

    public List<PermisoOtorgadoDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return PermisoOtorgadoService.ListFromConnection(urlstring, PermisoOtorgadoDTO.class);
    }

    public void add(PermisoOtorgadoDTO object) throws InterruptedException, ExecutionException, IOException {
        PermisoOtorgadoService.ObjectToConnection(urlstring, object);
    }

    public Object getId(String id) throws InterruptedException, ExecutionException, IOException {
        return PermisoOtorgadoService.FromConnectionID(urlstring, id, PermisoOtorgadoDTO.class);
    }
    public Object getUsuario(String usuarioId) throws InterruptedException, ExecutionException, IOException {
        return PermisoOtorgadoService.FromConnectionUsuario(urlstring+"usuario/", usuarioId, PermisoOtorgadoDTO.class);
    }

    public void Update(PermisoOtorgadoDTO dep) throws InterruptedException, ExecutionException, IOException {
        PermisoOtorgadoService.UpdateObjectToConnection(urlstring, dep.getId().toString(), dep);
    }

    public static PermisoOtorgadoController getInstance() {
        return PermisoOtorgadoControllerHolder.INSTANCE;
    }

    private static class PermisoOtorgadoControllerHolder {

        private static final PermisoOtorgadoController INSTANCE = new PermisoOtorgadoController();
    }
}
