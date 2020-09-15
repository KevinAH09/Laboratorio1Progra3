/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.una.laboratorio.controller.ParametroGeneralController;
import org.una.laboratorio.controller.PermisoOtorgadoController;
import org.una.laboratorio.dto.ParametroGeneralDTO;
import org.una.laboratorio.dto.PermisoOtorgadoDTO;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.AppContext;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class ContrasenaController extends Controller implements Initializable {

    @FXML
    private TextField texfielBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private HBox txtUsuario;
    @FXML
    private Label txtreglauno;
    @FXML
    private Label txtreglados;
    @FXML
    private Label txtreglatres;
    public UsuarioDTO listaUsuario;
    @FXML
    private TextField txtUsuariogf;
    @FXML
    private PasswordField txtPassOculto;
    @FXML
    private TextField txtPassMostrado;
    @FXML
    private ImageView imgViewPassword;
    @FXML
    private ImageView imgNotPassword;
    @FXML
    private Button btnVerificar;
    private List<ParametroGeneralDTO> parametro;
    private List<ParametroGeneralDTO> para;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaUsuario = (UsuarioDTO) AppContext.getInstance().get("selec");
        txtUsuariogf.setText(listaUsuario.getNombreCompleto());
        txtPassMostrado.setVisible(false);
        txtPassOculto.setText(txtPassMostrado.getText());
        txtPassOculto.setVisible(true);
        imgNotPassword.setVisible(false);
        imgViewPassword.setVisible(true);
        txtreglauno.setDisable(true);
        txtreglados.setDisable(true);
        txtreglatres.setDisable(true);
        llenarlistas();
    }    
    
    public void llenarlistas()
    {
        String nombre="Contraseña_Minimo_Caracteres";
        try {
            parametro = (List<ParametroGeneralDTO>) ParametroGeneralController.getInstance().getId("1");
        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(ContrasenaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            para = (List<ParametroGeneralDTO>) ParametroGeneralController.getInstance().getId("2");
        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(ContrasenaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public boolean validarContrasena(){
        boolean ban;
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+{"+parametro.get(0).getValor()+",}$");
            String pass= txtPassMostrado.getText();
            Matcher mather = pattern.matcher(pass);
            if (mather.find() == true) {
                ban = true;
            } else {
                ban = false;
            }
            return ban;
    }
    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void actionBuscar(ActionEvent event) {
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

    @FXML
    private void actionVerificar(ActionEvent event) {
        if(!validarContrasena())
        {
            txtreglauno.setDisable(false);
            txtreglados.setDisable(false);
            txtreglatres.setDisable(false);
        }
        else
        {
            txtreglauno.setDisable(true);
            txtreglados.setDisable(true);
            txtreglatres.setDisable(true);
        }
        
    }
    
}
