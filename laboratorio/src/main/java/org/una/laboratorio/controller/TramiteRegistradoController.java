/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio.controller;
import java.util.List;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.una.laboratorio.dto.TramiteRegistradoDTO;
import org.una.laboratorio.service.TramiteRegistradoService;
/**
 *
 * @author Bosco
 */
public class TramiteRegistradoController {
    private final String urlstring = "http://localhost:8099/tramites_registrados/";

    public TramiteRegistradoController() {
    }

    public List<TramiteRegistradoDTO> getAll() throws InterruptedException, ExecutionException, IOException {
        return TramiteRegistradoService.ListFromConnection(urlstring, TramiteRegistradoDTO.class);
    }

    public void add(TramiteRegistradoDTO object) throws InterruptedException, ExecutionException, IOException {
       TramiteRegistradoService.ObjectToConnection(urlstring, object);
    }

  
    public static TramiteRegistradoController getInstance() {
        return TramiteRegistradoControllerHolder.INSTANCE;
    }

    private static class TramiteRegistradoControllerHolder {

        private static final TramiteRegistradoController INSTANCE = new TramiteRegistradoController();
    }
}
