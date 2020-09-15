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
import org.una.laboratorio.controller.TramiteTipoController;
import org.una.laboratorio.controller.VariacionController;
import org.una.laboratorio.dto.DepartamentoDTO;
import org.una.laboratorio.dto.TramiteTipoDTO;
import org.una.laboratorio.dto.VariacionDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.FlowController;
import org.una.laboratorio.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class AddEditWatchVariacionController extends Controller implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private ComboBox<String> comboEsatdo;
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
    VariacionDTO variacionDTO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        variacionDTO = new VariacionDTO();
        comboEsatdo.setItems(FXCollections.observableArrayList("Activo", "Desactivo"));
        if (AppContext.getInstance().get("VarObject") != null) {
            variacionDTO = (VariacionDTO) AppContext.getInstance().get("VarObject");
            txtId.setText(variacionDTO.getId().toString());
            txtDescr.setText(variacionDTO.getDescripcion());
            txtGrupo.setText(variacionDTO.getGrupo().toString());
            AppContext.getInstance().set("BuscadoTra", variacionDTO.getTramiteTipo());
            txtTramite.setText(variacionDTO.getTramiteTipo().getDescripcion());
            lblFechaCreacion.setText(variacionDTO.getFechaRegistro().toString());

            if (variacionDTO.isEstado()) {
                comboEsatdo.setValue("Activo");
            } else {
                comboEsatdo.setValue("Inactivo");
            }

        } else {
            variacionDTO = new VariacionDTO();
            txtId.setText("Nuevo");
            txtDescr.setText("");
            txtGrupo.setText("");
            txtTramite.setText("");
            lblFechaCreacion.setText(new Date().toString());

        }
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void buscarTramite(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("BusquedaTipoTramite", ((Stage) txtId.getScene().getWindow()), Boolean.FALSE);
        if (AppContext.getInstance().get("BuscadoTra") != null) {
            txtTramite.setText(((TramiteTipoDTO) AppContext.getInstance().get("BuscadoTra")).getDescripcion());
            variacionDTO.setTramiteTipo((TramiteTipoDTO) AppContext.getInstance().get("BuscadoTra"));
        }
    }

    @FXML
    private void actionCancelar(ActionEvent event) {
    }

    @FXML
    private void actionguardar(ActionEvent event) throws InterruptedException {
        try {
            if (txtId.getText().equals("Nuevo")) {
                
                if (!txtDescr.getText().isEmpty() && !comboEsatdo.getValue().isEmpty() && !txtGrupo.getText().isEmpty() && !txtTramite.getText().isEmpty()) {
                    variacionDTO.setDescripcion(txtDescr.getText());
                    if (comboEsatdo.getValue().equals("Activo")) {
                        variacionDTO.setEstado(true);
                    } else {
                        variacionDTO.setEstado(false);
                    }
                    variacionDTO.setTramiteTipo((TramiteTipoDTO) AppContext.getInstance().get("BuscadoTra"));
                    variacionDTO.setFechaRegistro(new Date());
                    variacionDTO.setGrupo(Long.parseLong(txtGrupo.getText()));
                    if (VariacionController.getInstance().add(variacionDTO) == 201) {
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar Tramite", ((Stage) txtId.getScene().getWindow()), "Se guardo correctamente");
                        ((Stage) txtId.getScene().getWindow()).close();
                    } else {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar tramite", ((Stage) txtId.getScene().getWindow()), "No se guardo correctamente");
                    }
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar tramite", ((Stage) txtId.getScene().getWindow()), "Rellene los campos necesarios");
                }

            } else {
                if (!txtDescr.getText().isEmpty() && !comboEsatdo.getValue().isEmpty() && !txtGrupo.getText().isEmpty() && !txtTramite.getText().isEmpty()) {
                    variacionDTO.setDescripcion(txtDescr.getText());
                    if (comboEsatdo.getValue().equals("Activo")) {
                        variacionDTO.setEstado(true);
                    } else {
                        variacionDTO.setEstado(false);
                    }
                    variacionDTO.setTramiteTipo((TramiteTipoDTO) AppContext.getInstance().get("BuscadoTra"));
                    variacionDTO.setFechaRegistro(new Date());
                    variacionDTO.setGrupo(Long.parseLong(txtGrupo.getText()));
                    if (VariacionController.getInstance().Update(variacionDTO) == 201) {
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar Tramite", ((Stage) txtId.getScene().getWindow()), "Se guardo correctamente");
                        ((Stage) txtId.getScene().getWindow()).close();
                    } else{
                       new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear tramite", ((Stage) txtId.getScene().getWindow()), "No se guardo correctamente"); 
                    }
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al modificar tramites", ((Stage) txtId.getScene().getWindow()), "Rellene los campos necesarios");
                }
            }

        } catch (ExecutionException ex) {
            Logger.getLogger(AddEditWatchDepartamentoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddEditWatchDepartamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
