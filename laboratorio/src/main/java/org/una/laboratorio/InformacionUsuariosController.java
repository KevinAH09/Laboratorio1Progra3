/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.una.laboratorio.controller.PermisoOtorgadoController;
import org.una.laboratorio.controller.Usuariocontroller;
import org.una.laboratorio.dto.PermisoDTO;
import org.una.laboratorio.dto.PermisoOtorgadoDTO;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.FlowController;

/**
 * FXML Controller class
 *
 * @author cfugu
 */
public class InformacionUsuariosController extends Controller implements Initializable {

    @FXML
    private Button btnInformacion;
    @FXML
    private Button btnAutorizaciones;
    @FXML
    private Button btnContra;
    @FXML
    private HBox Hbox;
    @FXML
    private Button btnGuardar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cambiarUsuario("informacion");
        } catch (IOException ex) {
            Logger.getLogger(InformacionUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    void cambiarUsuario(String pantalla) throws IOException {
        Hbox.getChildren().clear();
        Parent root = FXMLLoader.load(App.class
                .getResource("Informacion.fxml"));
        Hbox.getChildren()
                .add(root);
    }
    @FXML
    private void actionInformacion(ActionEvent event) throws IOException {
        Hbox.getChildren().clear();
        Parent root = FXMLLoader.load(App.class
                .getResource("Informacion.fxml"));
        Hbox.getChildren()
                .add(root);
    }

    @FXML
    private void actionAutorizaciones(ActionEvent event) throws IOException {
        if(AppContext.getInstance().get("selec")!=null)
        {
            Hbox.getChildren().clear();
        Parent root = FXMLLoader.load(App.class
                .getResource("Autorizaciones.fxml"));
        Hbox.getChildren()
                .add(root);
        }
        
    }

    @FXML
    private void actionContra(ActionEvent event) throws IOException {
        if(AppContext.getInstance().get("selec")!=null)
        {
            Hbox.getChildren().clear();
        Parent root = FXMLLoader.load(App.class
                .getResource("Contrasena.fxml"));
        Hbox.getChildren()
                .add(root);
        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void guardar(ActionEvent event) throws InterruptedException, ExecutionException, IOException {
        PermisoOtorgadoDTO DTO = new PermisoOtorgadoDTO();
        List<PermisoDTO> lis= (List<PermisoDTO>) AppContext.getInstance().get("paraGuardar");
        UsuarioDTO o=(UsuarioDTO) AppContext.getInstance().get("selec");
         if (lis != null) {
            try {
                DTO.setPermisoId(lis.get(0));
                DTO.setUsuarios((UsuarioDTO) AppContext.getInstance().get("selec"));
                DTO.setFechaRegistro(new Date());
                PermisoOtorgadoController.getInstance().add(DTO);
            } catch (ExecutionException ex) {
                Logger.getLogger(InformacionUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(InformacionUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
    
}
