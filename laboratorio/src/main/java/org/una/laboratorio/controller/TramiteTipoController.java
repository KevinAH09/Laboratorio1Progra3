/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;
import java.util.List;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.TramiteTipoDTO;
import org.una.laboratorio.service.TramiteTipoService;
/**
 *
 * @author Bosco
 */
public class TramiteTipoController {
    private final String urlstring = "http://localhost:8099/tramites_Tipos/";

    public TramiteTipoController() {
    }

    public List<TramiteTipoDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return TramiteTipoService.ListFromConnection(urlstring, TramiteTipoDTO.class);
    }

    public void add(TramiteTipoDTO object) throws InterruptedException, ExecutionException, IOException {
        TramiteTipoService.ObjectToConnection(urlstring, object);
    }

  
    public static TramiteTipoController getInstance() {
        return TramiteTipoControllerHolder.INSTANCE;
    }

    private static class TramiteTipoControllerHolder {

        private static final TramiteTipoController INSTANCE = new TramiteTipoController();
    }
}
