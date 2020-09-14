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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.una.laboratorio.controller.DepartamentoController;
import org.una.laboratorio.controller.PermisoOtorgadoController;
import org.una.laboratorio.controller.Usuariocontroller;
import org.una.laboratorio.dto.AuthenticationResponse;
import org.una.laboratorio.dto.PermisoOtorgadoDTO;
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
    private Button btnCancelar;
    @FXML
    private Button btnIngresar;
    @FXML
    private ImageView imgViewPassword;
    @FXML
    private PasswordField txtPassOculto;
    @FXML
    private TextField txtPassMostrado;

    private String pass;
    @FXML
    private ImageView imgNotPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtPassMostrado.setVisible(false);
        txtPassOculto.setText(txtPassMostrado.getText());
        txtPassOculto.setVisible(true);
        imgNotPassword.setVisible(false);
        imgViewPassword.setVisible(true);
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void actionIngresar(ActionEvent event) throws InterruptedException {

        try {
            if (txtPassMostrado.isVisible()) {
                pass = txtPassMostrado.getText();
            } else {
                pass = txtPassOculto.getText();
            }
            if (!txtPassMostrado.getText().isEmpty() && ((!txtPassMostrado.getText().isEmpty())) || (!txtPassOculto.getText().isEmpty())) {
                AuthenticationResponse ar = new AuthenticationResponse();
                ar = (AuthenticationResponse) Usuariocontroller.getInstance().Login(txtUsuario.getText(), pass);
                if (ar != null) {
                    AppContext.getInstance().set("token", ar.getJwt());
                    AppContext.getInstance().set("usuarioLog", ar.getUsuario());
                    List<PermisoOtorgadoDTO> list = (List<PermisoOtorgadoDTO>) PermisoOtorgadoController.getInstance().getUsuario(ar.getUsuario().getId().toString());
                    if (!list.isEmpty()) {
                        AppContext.getInstance().set("permisosOTG", list);
                    } else {
                        AppContext.getInstance().set("permisosOTG", null);
                    }

                    System.out.println(((UsuarioDTO) AppContext.getInstance().get("usuarioLog")).getNombreCompleto());
                    FlowController.getInstance().goView("Principal");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error de incio de Sesion", null, "La contraseña o cedula estan incorecctas");
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de incio de Sesion", null, "Por favor complete todos campos");
            }

        } catch (ExecutionException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        
//        
    }

    @FXML
    private void actionViewPass(MouseEvent event) {
        if (txtPassMostrado.isVisible()) {
            txtPassMostrado.setVisible(false);
            txtPassOculto.setText(txtPassMostrado.getText());
            txtPassOculto.setVisible(true);
            imgNotPassword.setVisible(false);
            imgViewPassword.setVisible(true);

        } else {
            txtPassOculto.setVisible(false);
            txtPassMostrado.setText(txtPassOculto.getText());
            txtPassMostrado.setVisible(true);
            imgNotPassword.setVisible(true);
            imgViewPassword.setVisible(false);
        }
    }

}
