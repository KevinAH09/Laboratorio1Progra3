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
import org.una.laboratorio.utils.ConnectionUtilUsuarios;

/**
 *
 * @author colo7
 */
public class Usuariocontroller {

    private final String urlstring = "http://localhost:8099/usuarios/login";

    public Usuariocontroller() {
    }

    public List<UsuarioDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return ConnectionUtilUsuarios.ListFromConnection(urlstring, UsuarioDTO.class);
    }

    public void add(UsuarioDTO object) throws InterruptedException, ExecutionException, IOException {
        ConnectionUtilUsuarios.ObjectToConnection(urlstring, object);
    }
    public void Login(String cedula,String password) throws InterruptedException, ExecutionException, IOException {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(cedula, password);
        if(ConnectionUtilUsuarios.ObjectLogin(urlstring, authenticationRequest)!=null){
            System.out.println("org.una.laboratorio.controller.Usuariocontroller.Login()" + "sadddddddddddddddddddddddddddddd");
        }
        
    }
    public static Usuariocontroller getInstance() {
        return UsuariocontrollerHolder.INSTANCE;
    }

    private static class UsuariocontrollerHolder {

        private static final Usuariocontroller INSTANCE = new Usuariocontroller();
    }
}
