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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.una.laboratorio.controller.RequisitoController;
import org.una.laboratorio.dto.RequisitoDTO;
import org.una.laboratorio.dto.VariacionDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author cfugu
 */
public class AddEditWatchRequisitoController extends Controller implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private ComboBox<String> cmbEstado;
    @FXML
    private Label lblFecha;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtVariacion;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    RequisitoDTO reqDTO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reqDTO = new RequisitoDTO();
        cmbEstado.setItems(FXCollections.observableArrayList("Activo", "Inactivo"));
        if (AppContext.getInstance().get("ReqObject") != null) {
            reqDTO = (RequisitoDTO) AppContext.getInstance().get("ReqObject");
            txtId.setText(reqDTO.getId().toString());
            txtDescripcion.setText(reqDTO.getDescripcion());
            txtVariacion.setText(reqDTO.getVariacion().getDescripcion());
            lblFecha.setText(reqDTO.getFechaRegistro().toString());

            if (reqDTO.isEstado()) {
                cmbEstado.setValue("Activo");
            } else {
                cmbEstado.setValue("Inactivo");
            }

        } else {
            VariacionDTO vari = (VariacionDTO) AppContext.getInstance().get("variacion");
            reqDTO = new RequisitoDTO();
            txtId.setText("Nuevo");
            txtDescripcion.setText("");
            txtVariacion.setText(vari.getDescripcion());
            lblFecha.setText(new Date().toString());

        }
    }

    @FXML
    private void onactionCancelar(ActionEvent event) {
        ((Stage) txtId.getScene().getWindow()).close();
    }

    @FXML
    private void onactionGuardar(ActionEvent event) throws InterruptedException {
        try {
            if (txtId.getText().equals("Nuevo")) {

                if (!txtDescripcion.getText().isEmpty() && !cmbEstado.getValue().isEmpty() && !txtVariacion.getText().isEmpty()) {
                    reqDTO.setDescripcion(txtDescripcion.getText());
                    if (cmbEstado.getValue().equals("Activo")) {
                        reqDTO.setEstado(true);
                    } else {
                        reqDTO.setEstado(false);
                    }
                    reqDTO.setVariacion((VariacionDTO) AppContext.getInstance().get("variacion"));
                    reqDTO.setFechaRegistro(new Date());
                    if (RequisitoController.getInstance().add(reqDTO) == 201) {
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar Requisito", ((Stage) txtId.getScene().getWindow()), "Se guardó correctamente");
                        ((Stage) txtId.getScene().getWindow()).close();
                    } else {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar el Requisito", ((Stage) txtId.getScene().getWindow()), "No se guardó correctamente");
                    }
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar  el Requisito", ((Stage) txtId.getScene().getWindow()), "Rellene los campos necesarios");
                }

            } else {
                if (!txtDescripcion.getText().isEmpty() && !cmbEstado.getValue().isEmpty() && !txtVariacion.getText().isEmpty()) {
                    reqDTO.setDescripcion(txtDescripcion.getText());
                    if (cmbEstado.getValue().equals("Activo")) {
                        reqDTO.setEstado(true);
                    } else {
                        reqDTO.setEstado(false);
                    }
                    reqDTO.setVariacion((VariacionDTO) AppContext.getInstance().get("variacion"));
                    reqDTO.setFechaRegistro(new Date());

                    if (RequisitoController.getInstance().Update(reqDTO) == 200) {
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar Requisito", ((Stage) txtId.getScene().getWindow()), "Se guardó correctamente");
                        ((Stage) txtId.getScene().getWindow()).close();
                    } else {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear el Requisito", ((Stage) txtId.getScene().getWindow()), "No se guardó correctamente");
                    }
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al modificar el Requisito", ((Stage) txtId.getScene().getWindow()), "Rellene los campos necesarios");
                }
            }

        } catch (ExecutionException ex) {
            Logger.getLogger(AddEditWatchRequisitoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddEditWatchRequisitoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
