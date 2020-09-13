/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.util.List;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.TramiteEstadoDTO;
import org.una.laboratorio.service.TramiteEstadoService;

/**
 *
 * @author Bosco
 */
public class TramiteEstadoController {

    private final String urlstring = "http://localhost:8099/tramites_estados/";

    public TramiteEstadoController() {
    }

    public List<TramiteEstadoDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return TramiteEstadoService.ListFromConnection(urlstring, TramiteEstadoDTO.class);
    }

    public void add(TramiteEstadoDTO object) throws InterruptedException, ExecutionException, IOException {
        TramiteEstadoService.ObjectToConnection(urlstring, object);
    }

    public Object getId(String id) throws InterruptedException, ExecutionException, IOException {
        return TramiteEstadoService.FromConnectionID(urlstring, id, TramiteEstadoDTO.class);
    }

    public void Update(TramiteEstadoDTO dep) throws InterruptedException, ExecutionException, IOException {
        TramiteEstadoService.UpdateObjectToConnection(urlstring, dep.getId().toString(), dep);
    }

    public static TramiteEstadoController getInstance() {
        return TramiteEstadoControllerHolder.INSTANCE;
    }

    private static class TramiteEstadoControllerHolder {

        private static final TramiteEstadoController INSTANCE = new TramiteEstadoController();
    }
}
