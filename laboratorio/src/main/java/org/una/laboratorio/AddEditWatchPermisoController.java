/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.una.laboratorio.dto.PermisoDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.controller.PermisoController;
import org.una.laboratorio.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class AddEditWatchPermisoController extends Controller implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtCodigo;
    @FXML
    private ComboBox<String> comboEstado;
    @FXML
    private Label lblFechaCreacion;
    @FXML
    private Label lblFechaModificacion;
    @FXML
    private TextArea txtDescripcion;
    
    
    PermisoDTO permisoDTO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        permisoDTO = new PermisoDTO();
        comboEstado.setItems(FXCollections.observableArrayList("Activo", "Inactivo"));
        if (AppContext.getInstance().get("PerObject") != null) {
            permisoDTO = (PermisoDTO) AppContext.getInstance().get("PerObject");
            txtId.setText(permisoDTO.getId().toString());
            txtCodigo.setText(permisoDTO.getCodigo());
            txtDescripcion.setText(permisoDTO.getDescripcion());
            lblFechaCreacion.setText(permisoDTO.getFechaRegistro().toString());
            lblFechaModificacion.setText(permisoDTO.getFechaModificacion().toString());
            if (permisoDTO.isEstado()) {
                comboEstado.setValue("Activo");
            } else {
                comboEstado.setValue("Desactivo");
            }

        } else {
            comboEstado.setValue("Activo");
            comboEstado.setDisable(true);
            permisoDTO = new PermisoDTO();
            txtId.setText("Nuevo");
            txtCodigo.setText("");
            txtDescripcion.setText("");
            lblFechaCreacion.setText(new Date().toString());
            lblFechaModificacion.setText(new Date().toString());
        }
    }

    @FXML
    private void actionCancelar(ActionEvent event) {
        ((Stage) txtId.getScene().getWindow()).close();
    }

    @FXML
    private void actionguardar(ActionEvent event) throws InterruptedException {
        try {
            if (txtId.getText().equals("Nuevo")) {
                System.out.println("org.una.laboratorio.AddEditWatchDepartamentoController.actionguardar()");
                if (!txtDescripcion.getText().isEmpty() && !comboEstado.getValue().isEmpty() && !txtCodigo.getText().isEmpty()) {
                    if (comboEstado.getValue().equals("Activo")) {
                        permisoDTO.setEstado(true);
                    } else {
                        permisoDTO.setEstado(false);
                    }
                    permisoDTO.setDescripcion(txtDescripcion.getText());
                    permisoDTO.setCodigo(txtCodigo.getText());
                    permisoDTO.setFechaModificacion(new Date());
                    permisoDTO.setFechaRegistro(new Date());
                    if(PermisoController.getInstance().add(permisoDTO)==201){
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar Permiso", ((Stage) txtId.getScene().getWindow()), "Se guardo correctamente");
                        ((Stage) txtId.getScene().getWindow()).close();
                    }else{
                       new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar Permiso", ((Stage) txtId.getScene().getWindow()), "No se guardo correctamente"); 
                    }
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear Permiso", ((Stage) txtId.getScene().getWindow()), "Rellene los campos necesarios");
                }

            } else {
                if (!txtDescripcion.getText().isEmpty() && !comboEstado.getValue().isEmpty() && !txtCodigo.getText().isEmpty()) {
                    if (comboEstado.getValue().equals("Activo")) {
                        permisoDTO.setEstado(true);
                    } else {
                        permisoDTO.setEstado(false);
                    }
                    permisoDTO.setDescripcion(txtDescripcion.getText());
                    permisoDTO.setCodigo(txtCodigo.getText());
                    permisoDTO.setFechaModificacion(new Date());
                    if(PermisoController.getInstance().Update(permisoDTO)==200){
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar Permiso", ((Stage) txtId.getScene().getWindow()), "Se guardo correctamente");
                        ((Stage) txtId.getScene().getWindow()).close();
                    }else{
                       new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar Permiso", ((Stage) txtId.getScene().getWindow()), "No se guardo correctamente"); 
                    }
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar Permiso", ((Stage) txtId.getScene().getWindow()), "Rellene los campos necesarios");
                }
            }

        } catch (ExecutionException ex) {
            Logger.getLogger(AddEditWatchParametroController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddEditWatchParametroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
