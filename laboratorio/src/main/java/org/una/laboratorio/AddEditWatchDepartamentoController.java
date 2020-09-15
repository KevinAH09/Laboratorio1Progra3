/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
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
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.Mensaje;
import org.una.laboratorio.dto.DepartamentoDTO;
import org.una.laboratorio.controller.DepartamentoController;
import org.una.laboratorio.dto.PermisoOtorgadoDTO;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class AddEditWatchDepartamentoController extends Controller implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<String> comboEsatdo;
    @FXML
    private Label lblFechaCreacion;
    @FXML
    private Label lblFechaModificacion;

    DepartamentoDTO departamentoDTO;
    @FXML
    private Button btnGuardar;

    List<PermisoOtorgadoDTO> ListPerOtor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListPerOtor = (List<PermisoOtorgadoDTO>) AppContext.getInstance().get("permisosOTG");
        comboEsatdo.setItems(FXCollections.observableArrayList("Activo", "Desactivo"));
        if (AppContext.getInstance().get("DepaObject") != null) {
            if (!ListPerOtor.stream().anyMatch(x -> x.getPermisoId().getCodigo().equals("DEP2"))) {
                txtNombre.setDisable(true);
            }else if(!ListPerOtor.stream().anyMatch(x -> x.getPermisoId().getCodigo().equals("DEP3"))){
                comboEsatdo.setDisable(true);
            }
            departamentoDTO = (DepartamentoDTO) AppContext.getInstance().get("DepaObject");
            txtId.setText(departamentoDTO.getId().toString());
            txtNombre.setText(departamentoDTO.getNombre());
            lblFechaCreacion.setText(departamentoDTO.getFechaRegistro().toString());
            lblFechaModificacion.setText(departamentoDTO.getFechaModificacion().toString());
            if (departamentoDTO.isEstado()) {
                comboEsatdo.setValue("Activo");
            } else {
                comboEsatdo.setValue("Desactivo");
            }

        } else {
            comboEsatdo.setValue("Activo");
            comboEsatdo.setDisable(true);
            departamentoDTO = new DepartamentoDTO();
            txtId.setText("Nuevo");
            txtNombre.setText("");
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
                if (!txtNombre.getText().isEmpty() && !comboEsatdo.getValue().isEmpty()) {
                    departamentoDTO.setNombre(txtNombre.getText());
                    System.out.println(comboEsatdo.getValue());
                    if (comboEsatdo.getValue().equals("Activo")) {
                        departamentoDTO.setEstado(true);
                    } else {
                        departamentoDTO.setEstado(false);
                    }

                    departamentoDTO.setFechaModificacion(new Date());
                    departamentoDTO.setFechaRegistro(new Date());
                    if (DepartamentoController.getInstance().add(departamentoDTO) == 201) {
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar departamento", ((Stage) txtId.getScene().getWindow()), "Se guardo correctamente");
                        ((Stage) txtId.getScene().getWindow()).close();
                    } else {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar departamento", ((Stage) txtId.getScene().getWindow()), "No se guardo correctamente");
                    }
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear departamento", ((Stage) txtId.getScene().getWindow()), "Rellene los campos necesarios");
                }

            } else {
                if (!txtNombre.getText().isEmpty() && !comboEsatdo.getValue().isEmpty()) {
                    departamentoDTO.setNombre(txtNombre.getText());
                    if (comboEsatdo.getValue().equals("Activo")) {
                        departamentoDTO.setEstado(true);
                    } else {
                        departamentoDTO.setEstado(false);
                    }
                    departamentoDTO.setFechaModificacion(new Date());
                    if (DepartamentoController.getInstance().Update(departamentoDTO) == 200) {
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar departamento", ((Stage) txtId.getScene().getWindow()), "Se guardo correctamente");
                        ((Stage) txtId.getScene().getWindow()).close();
                    } else {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar departamento", ((Stage) txtId.getScene().getWindow()), "No se guardo correctamente");
                    }
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al modificar departamentos", ((Stage) txtId.getScene().getWindow()), "Rellene los campos necesarios");
                }
            }

        } catch (ExecutionException ex) {
            Logger.getLogger(AddEditWatchDepartamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddEditWatchDepartamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
