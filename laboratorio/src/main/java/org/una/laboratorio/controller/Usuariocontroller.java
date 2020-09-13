/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.AuthenticationRequest;
import org.una.laboratorio.dto.AuthenticationResponse;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.service.UsuarioService;

/**
 *
 * @author colo7
 */
public class Usuariocontroller {

    private final String urlstring = "http://localhost:8099/usuarios/";

    public Usuariocontroller() {
    }

    public List<UsuarioDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return UsuarioService.ListFromConnection(urlstring, UsuarioDTO.class);
    }

    public void add(UsuarioDTO object) throws InterruptedException, ExecutionException, IOException {
        UsuarioService.ObjectToConnection(urlstring, object);
    }

    public Object Login(String cedula, String password) throws InterruptedException, ExecutionException, IOException {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(cedula, password);
        Object o = UsuarioService.ObjectLogin(urlstring+"login", authenticationRequest);
        return o;

    }
    public Object getId(String id)throws InterruptedException, ExecutionException, IOException {
        return UsuarioService.FromConnectionID(urlstring, id,UsuarioDTO.class);
    }
    public Object getCedula(String cedula)throws InterruptedException, ExecutionException, IOException {
        return UsuarioService.FromConnectionCedula(urlstring+"cedula/", cedula,UsuarioDTO.class);
    }
     public void Update(UsuarioDTO usu)throws InterruptedException, ExecutionException, IOException {
        UsuarioService.UpdateObjectToConnection(urlstring, usu.getId().toString(),usu);
    }
    public static Usuariocontroller getInstance() {
        return UsuariocontrollerHolder.INSTANCE;
    }

    private static class UsuariocontrollerHolder {

        private static final Usuariocontroller INSTANCE = new Usuariocontroller();
    }
}
