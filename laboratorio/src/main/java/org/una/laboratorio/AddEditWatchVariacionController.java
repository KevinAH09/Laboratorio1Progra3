/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class AddEditWatchVariacionController extends Controller implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private ComboBox<?> comboEsatdo;
    @FXML
    private TextField txtGrupo;
    @FXML
    private Label lblFechaCreacion;
    @FXML
    private TextField txtDescr;
    @FXML
    private TextField txtTramite;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;

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
    private void buscarTramite(ActionEvent event) {
    }

    @FXML
    private void actionCancelar(ActionEvent event) {
    }

    @FXML
    private void actionguardar(ActionEvent event) {
    }
    
}
