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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.una.laboratorio.controller.ParametroGeneralController;
import org.una.laboratorio.controller.PermisoOtorgadoController;
import org.una.laboratorio.dto.ParametroGeneralDTO;
import org.una.laboratorio.dto.PermisoOtorgadoDTO;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.Mensaje;

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
    String cantidad;
    String especial;
    String cade;
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
        cade = txtUsuariogf.getText();
        llenarlistas();
    }    
    
    public void llenarlistas()
    {
        String nombre="Contraseña_Minimo_Caracteres";
        
        try {
            parametro = (List<ParametroGeneralDTO>) ParametroGeneralController.getInstance().getAll();
            for (ParametroGeneralDTO p : parametro) {
                if(p.getNombre().equals(nombre))
                {
                    cantidad=p.getValor();
                }
            }
        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(ContrasenaController.class.getName()).log(Level.SEVERE, null, ex);
        }
       try {
            para = (List<ParametroGeneralDTO>) ParametroGeneralController.getInstance().getAll();
            for (ParametroGeneralDTO p : para) {
                if(p.getNombre().equals("Caracteres especiales"))
                {
                    especial=p.getValor();
                }
            }
        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(ContrasenaController.class.getName()).log(Level.SEVERE, null, ex);
        }
       txtreglauno.setText("1) La contraseña tiene que tener minimo "+cantidad+" caracteres y sin espacios.");
       txtreglados.setText("2) La contraseña tiene que tener al menos un cararter especial como estos "+especial+".");
       txtreglatres.setText("3) La contraseña no mas de 4 letras de su nombre o de apellido");
    }
     public boolean validarContrasena(){
         
         int cont=0;
         for (int i = 0; i < listaUsuario.getNombreCompleto().length(); i++) {
             for (int j = 0; j < txtPassMostrado.getText().length(); j++) {
                 if(listaUsuario.getNombreCompleto().charAt(i)==txtPassMostrado.getText().charAt(j))
                 {
                     cont++;
                     break;
                 }
             }
         }
         boolean ban;
         boolean ban2;
         boolean ban3;
        String pattern1 = "(?=.*["+especial+"])(?=\\S+$).{1,}";
                //"(?=.*[a-z])(?=.*["+especial+"])";
        String pattern2 = "(?=\\S+$).{"+cantidad+",}";
        
       // 
        
            String pass1= txtPassMostrado.getText();
            String pass2= txtPassMostrado.getText();
            //Matcher mather = pattern.matcher(pass);
            if (pass1.matches(pattern1) == true) {
                txtreglados.setDisable(false);
                ban = true;
            } else {
                ban = false;
                txtreglados.setDisable(true);
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al validar la contraseña", ((Stage) txtreglados.getScene().getWindow()), "No cumple con la regla #2");
            }
            if (pass2.matches(pattern2) == true) {
                txtreglauno.setDisable(false);
                ban2 = true;
            } else {
                
            txtreglauno.setDisable(true);
                ban2 = false;
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al validar la contraseña", ((Stage) txtreglauno.getScene().getWindow()), "No cumple con la regla #1");
            }
            if(cont>4)
            {
              ban3=false;
              
            txtreglatres.setDisable(true);
              new Mensaje().showModal(Alert.AlertType.ERROR, "Error al validar la contraseña", ((Stage) txtreglauno.getScene().getWindow()), "No cumple con la regla #3");
            }
            else
            {
                txtreglatres.setDisable(false);
                ban3=true;
            }
            if((ban&&ban2&&ban3)==true)
            {
                ban=true;
            }else
            {
                ban=false;
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
        if(validarContrasena())
        {
            txtreglauno.setDisable(false);
            txtreglados.setDisable(false);
            txtreglatres.setDisable(false);
            AppContext.getInstance().set("guardarContra", txtPassMostrado.getText());
            AppContext.getInstance().set("guardarC", "si");
        }
        
    }
    
}
