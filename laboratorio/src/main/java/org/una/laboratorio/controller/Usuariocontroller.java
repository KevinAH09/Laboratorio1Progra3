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
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.ConnectionUtil;
import org.una.laboratorio.utils.Request;
import org.una.laboratorio.utils.Respuesta;

/**
 *
 * @author colo7
 */
public class Usuariocontroller {

    private final String urlstring = "http://localhost:8099/usuarios/login";

    public Usuariocontroller() {
    }

    public List<UsuarioDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return ConnectionUtil.ListFromConnection(urlstring, UsuarioDTO.class);
    }
//
//    public Respuesta Login(String cedula, String password) {

//            Map<String, Object> parametros = new HashMap<>();
//            parametros.put("id", cedula);
//            Request request = new Request(urlstring, "/{id}", parametros);
//            request.get();
//            
//            AuthenticationRequest authenticationRequest = new AuthenticationRequest(cedula, password);
//            Request request = new Request(urlstring);
//            request.post(authenticationRequest);
//            if (request.isError()) {
//                System.out.println("Errrror");
//                return new Respuesta(false, request.getError(), "Error");
//            }
//            return new Respuesta(true, "usuario", null);
//    }

    public void add() throws InterruptedException, ExecutionException, IOException {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("admiuyn", "Una2020");
        ConnectionUtil.ObjectToConnection(urlstring, authenticationRequest);
    }

    public static Usuariocontroller getInstance() {
        return UsuariocontrollerHolder.INSTANCE;
    }

    private static class UsuariocontrollerHolder {

        private static final Usuariocontroller INSTANCE = new Usuariocontroller();
    }
}
