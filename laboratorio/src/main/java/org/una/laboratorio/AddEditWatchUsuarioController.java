/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.AppContext;

/**
 * FXML Controller class
 *
 * @author cfugu
 */
public class AddEditWatchUsuarioController extends Controller implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtnombre;
    @FXML
    private TextField txtcedula;
    @FXML
    private ComboBox<String> cmbEstado;
    @FXML
    private TextField txtdepartamento;
    @FXML
    private ComboBox<String> cmbJefe;
    @FXML
    private Label fechaModi;
    @FXML
    private Label fechaRegis;
    UsuarioDTO usuario;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       if((UsuarioDTO) AppContext.getInstance().get("selec")!=null)
       {
           usuario=(UsuarioDTO) AppContext.getInstance().get("selec");
       txtId.setText(usuario.getId().toString());
       txtId.setDisable(true);
       txtcedula.setText(usuario.getCedula());
       txtnombre.setText(usuario.getNombreCompleto());
       cmbEstado.setItems(FXCollections.observableArrayList("Activo", "Inactivo"));
       cmbJefe.setItems(FXCollections.observableArrayList("Activo", "Inactivo"));
       if (usuario.isEstado()) {
                cmbEstado.setValue("Activo");
            } else {
                cmbEstado.setValue("Desactivo");
            }
       if (usuario.isEsJefe()) {
                cmbJefe.setValue("Activo");
            } else {
                cmbJefe.setValue("Desactivo");
            }
       fechaModi.setText(usuario.getFechaModificacion().toString());
       fechaRegis.setText(usuario.getFechaRegistro().toString());
       txtdepartamento.setText(usuario.getDepartamento().getNombre());
       }
          
    }    

    @Override
    public void initialize() {
        // new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
