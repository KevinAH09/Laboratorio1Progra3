/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.ParametroGeneralDTO;
import org.una.laboratorio.service.ParametroGeneralService;

/**
 *
 * @author Bosco
 */
public class ParametroGeneralController {
    private final String urlstring = "http://localhost:8099/parametros_generales/";

    public ParametroGeneralController() {
    }

    public List<ParametroGeneralDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return ParametroGeneralService.ListFromConnection(urlstring, ParametroGeneralDTO.class);
    }

    public int add(ParametroGeneralDTO object) throws InterruptedException, ExecutionException, IOException {
        return ParametroGeneralService.ObjectToConnection(urlstring, object);
    }

  public Object getId(String id)throws InterruptedException, ExecutionException, IOException {
        return ParametroGeneralService.FromConnectionID(urlstring, id, ParametroGeneralDTO.class);
    }
  public Object getNombre(String nombre)throws InterruptedException, ExecutionException, IOException {
        return ParametroGeneralService.FromConnectionNombre(urlstring+"nombre/", nombre, ParametroGeneralDTO.class);
    }
  public Object getEstado(String nombre)throws InterruptedException, ExecutionException, IOException {
        return ParametroGeneralService.FromConnectionEstado(urlstring+"estado/", nombre, ParametroGeneralDTO.class);
    }
     public int Update(ParametroGeneralDTO dep)throws InterruptedException, ExecutionException, IOException {
        return ParametroGeneralService.UpdateObjectToConnection(urlstring, dep.getId().toString(),dep);
    }
    public static ParametroGeneralController getInstance() {
        return ParametroGeneralControllerHolder.INSTANCE;
    }

    private static class ParametroGeneralControllerHolder {

        private static final ParametroGeneralController INSTANCE = new ParametroGeneralController();
    }
}
