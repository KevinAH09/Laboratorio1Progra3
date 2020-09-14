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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.Mensaje;
import org.una.laboratorio.dto.ParametroGeneralDTO;
import org.una.laboratorio.controller.DepartamentoController;
import org.una.laboratorio.controller.ParametroGeneralController;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class AddEditWatchParametroController extends Controller implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<String> comboEstado;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtValor;
    @FXML
    private Label lblFechaCreacion;
    @FXML
    private Label lblFechaModificacion;

    ParametroGeneralDTO parametroGeneralDTO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parametroGeneralDTO = new ParametroGeneralDTO();
        comboEstado.setItems(FXCollections.observableArrayList("Activo", "Desactivo"));
        if (AppContext.getInstance().get("ParaObject") != null) {
            parametroGeneralDTO = (ParametroGeneralDTO) AppContext.getInstance().get("ParaObject");
            txtId.setText(parametroGeneralDTO.getId().toString());
            txtNombre.setText(parametroGeneralDTO.getNombre());
            txtValor.setText(parametroGeneralDTO.getValor());
            txtDescripcion.setText(parametroGeneralDTO.getDescripcion());
            lblFechaCreacion.setText(parametroGeneralDTO.getFechaRegistro().toString());
            lblFechaModificacion.setText(parametroGeneralDTO.getFechaModificacion().toString());
            if (parametroGeneralDTO.isEstado()) {
                comboEstado.setValue("Activo");
            } else {
                comboEstado.setValue("Desactivo");
            }

        } else {
            parametroGeneralDTO = new ParametroGeneralDTO();
            txtId.setText("Nuevo");
            txtNombre.setText("");
            txtValor.setText("");
            txtDescripcion.setText("");
            lblFechaCreacion.setText(new Date().toString());
            lblFechaModificacion.setText(new Date().toString());
        }
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void actionCancelar(ActionEvent event) {
    }

    @FXML
    private void actionGuardar(ActionEvent event) throws InterruptedException {
        try {
            if (txtId.getText().equals("Nuevo")) {
                System.out.println("org.una.laboratorio.AddEditWatchDepartamentoController.actionguardar()");
                if (!txtNombre.getText().isEmpty() && !comboEstado.getValue().isEmpty() && !txtValor.getText().isEmpty()) {
                    parametroGeneralDTO.setNombre(txtNombre.getText());
                    if (comboEstado.getValue().equals("Activo")) {
                        parametroGeneralDTO.setEstado(true);
                    } else {
                        parametroGeneralDTO.setEstado(false);
                    }
                    parametroGeneralDTO.setDescripcion(txtDescripcion.getText());
                    parametroGeneralDTO.setValor(txtValor.getText());
                    parametroGeneralDTO.setFechaModificacion(new Date());
                    parametroGeneralDTO.setFechaRegistro(new Date());
                    ParametroGeneralController.getInstance().add(parametroGeneralDTO);
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear departamentos", null, "Rellene los campos necesarios");
                }

            } else {
                if (!txtNombre.getText().isEmpty() && !comboEstado.getValue().isEmpty() && !txtValor.getText().isEmpty()) {
                    parametroGeneralDTO.setNombre(txtNombre.getText());
                    if (comboEstado.getValue().equals("Activo")) {
                        parametroGeneralDTO.setEstado(true);
                    } else {
                        parametroGeneralDTO.setEstado(false);
                    }
                    parametroGeneralDTO.setDescripcion(txtDescripcion.getText());
                    parametroGeneralDTO.setValor(txtValor.getText());
                    parametroGeneralDTO.setFechaModificacion(new Date());
                    ParametroGeneralController.getInstance().Update(parametroGeneralDTO);
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al modificar departamentos", null, "Rellene los campos necesarios");
                }
            }

        } catch (ExecutionException ex) {
            Logger.getLogger(AddEditWatchParametroController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddEditWatchParametroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
