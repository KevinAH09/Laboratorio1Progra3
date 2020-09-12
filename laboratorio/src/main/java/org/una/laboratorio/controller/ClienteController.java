/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.ClienteDTO;
import org.una.laboratorio.service.ClienteService;

/**
 *
 * @author Bosco
 */
public class ClienteController {
    private final String urlstring = "http://localhost:8099/clientes/";

    public ClienteController() {
    }

    public List<ClienteDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return ClienteService.ListFromConnection(urlstring, ClienteDTO.class);
    }

    public void add(ClienteDTO object) throws InterruptedException, ExecutionException, IOException {
        ClienteService.ObjectToConnection(urlstring, object);
    }

  
    public static ClienteController getInstance() {
        return ClienteControllerHolder.INSTANCE;
    }

    private static class ClienteControllerHolder {

        private static final ClienteController INSTANCE = new ClienteController();
    }
}
