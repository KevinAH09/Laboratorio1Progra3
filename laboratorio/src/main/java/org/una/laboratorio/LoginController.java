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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.una.laboratorio.controller.Usuariocontroller;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.FlowController;

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
            Usuariocontroller.getInstance().add();

//            List<UsuarioDTO> usudTOs = new ArrayList<>();
//            usudTOs = (List<UsuarioDTO>) Usuariocontroller.getInstance().getAll();
//            for (int i = 0; i < usudTOs.size(); i++) {
//                System.out.println(usudTOs.get(i).getCedula());
//            }
        } catch (ExecutionException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        
        FlowController.getInstance().goView("Principal");
    }
    
}
