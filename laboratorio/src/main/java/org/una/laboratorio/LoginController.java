/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.una.laboratorio.controller.DepartamentoController;
import org.una.laboratorio.controller.Usuariocontroller;
import org.una.laboratorio.dto.AuthenticationResponse;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.FlowController;
import org.una.laboratorio.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author colo7
 */
public class LoginController extends Controller implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtCancelar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnIngresar;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void actionIngresar(ActionEvent event) throws InterruptedException {
        
        
            
        try {
            AuthenticationResponse ar = new AuthenticationResponse();
            ar = (AuthenticationResponse)Usuariocontroller.getInstance().Login(txtUsuario.getText(), txtCancelar.getText());
            if(ar!=null){
                AppContext.getInstance().set("token", ar.getJwt());
                System.out.println(DepartamentoController.getInstance().getAll().get(0).getNombre());
               FlowController.getInstance().goView("Principal");
            }else{
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de incio de Sesion", null, "La contraseña o cedula estan incorecctas");
            }
        } catch (ExecutionException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
//        
//        
    }
    
}
