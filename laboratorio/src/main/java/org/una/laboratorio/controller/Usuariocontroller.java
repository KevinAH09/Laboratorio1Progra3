/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.ConnectionUtil;
/**
 *
 * @author colo7
 */
public class Usuariocontroller {
      private final String urlstring = "http://localhost:8099/usuarios/";
  
  public Usuariocontroller() {
  }
  
  public List<UsuarioDTO> getAll() throws InterruptedException, ExecutionException, IOException {
    return ConnectionUtil.ListFromConnection(urlstring, UsuarioDTO.class);
  }
  
  
  public void add(UsuarioDTO object) throws InterruptedException, ExecutionException, IOException {
    ConnectionUtil.ObjectToConnection(urlstring, object);
  }
  
  public static Usuariocontroller getInstance() {
    return UsuariocontrollerHolder.INSTANCE;
  }
  
  private static class UsuariocontrollerHolder {
    private static final Usuariocontroller INSTANCE = new Usuariocontroller();
  }
}
