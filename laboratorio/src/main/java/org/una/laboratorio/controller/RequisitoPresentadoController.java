/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;
import java.util.List;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.RequisitoPresentadoDTO;
import org.una.laboratorio.service.RequisitoPresentadoService;
/**
 *
 * @author Bosco
 */
public class RequisitoPresentadoController {
    private final String urlstring = "http://localhost:8099/requisitos_presentados/";

    public RequisitoPresentadoController() {
    }

    public List<RequisitoPresentadoDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return RequisitoPresentadoService.ListFromConnection(urlstring, RequisitoPresentadoDTO.class);
    }

    public void add(RequisitoPresentadoDTO object) throws InterruptedException, ExecutionException, IOException {
        RequisitoPresentadoService.ObjectToConnection(urlstring, object);
    }

  
    public static RequisitoPresentadoController getInstance() {
        return RequisitoPresentadoControllerHolder.INSTANCE;
    }

    private static class RequisitoPresentadoControllerHolder {

        private static final RequisitoPresentadoController INSTANCE = new RequisitoPresentadoController();
    }
}
