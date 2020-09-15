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
import javafx.stage.Stage;
import org.una.laboratorio.controller.TramiteEstadoController;
import org.una.laboratorio.controller.TramiteTipoController;
import org.una.laboratorio.dto.DepartamentoDTO;
import org.una.laboratorio.dto.TramiteTipoDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.FlowController;
import org.una.laboratorio.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class AddEditWatchTipoTramiteController extends Controller implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private ComboBox<String> comboEsatdo;
    @FXML
    private Label lblFechaCreacion;
    @FXML
    private Label lblFechaModificacion;
    TramiteTipoDTO tramiteTipoDTO;
    @FXML
    private TextArea txtDescipcion;
    @FXML
    private TextField txtDeparta;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         comboEsatdo.setItems(FXCollections.observableArrayList("Activo","Desactivo"));
        if (AppContext.getInstance().get("TraObject") != null) {
            tramiteTipoDTO = (TramiteTipoDTO) AppContext.getInstance().get("TraObject");
            txtId.setText(tramiteTipoDTO.getId().toString());
            txtDescipcion.setText(tramiteTipoDTO.getDescripcion());
            AppContext.getInstance().set("BuscadoDepar", tramiteTipoDTO.getDepartamento());
            txtDeparta.setText(tramiteTipoDTO.getDepartamento().getNombre());
            lblFechaCreacion.setText(tramiteTipoDTO.getFechaRegistro().toString());
            lblFechaModificacion.setText(tramiteTipoDTO.getFechaModificacion().toString());
            if (tramiteTipoDTO.isEstado()) {
                comboEsatdo.setValue("Activo");
            } else {
                comboEsatdo.setValue("Desactivo");
            }

        } else {
            comboEsatdo.setValue("Activo");
            comboEsatdo.setDisable(true);
            tramiteTipoDTO = new TramiteTipoDTO();
            txtId.setText("Nuevo");
            txtDescipcion.setText("");
            txtDeparta.setText("");
            lblFechaCreacion.setText(new Date().toString());
            lblFechaModificacion.setText(new Date().toString());
        }
    }    

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void actionbuscarDep(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("TramitesBuscarDeparView", ((Stage) txtId.getScene().getWindow()), Boolean.FALSE);
        if(AppContext.getInstance().get("BuscadoDepar")!=null){
            txtDeparta.setText(((DepartamentoDTO) AppContext.getInstance().get("BuscadoDepar")).getNombre());
            tramiteTipoDTO.setDepartamento((DepartamentoDTO) AppContext.getInstance().get("BuscadoDepar"));
        }
    }

    @FXML
    private void actionCancelar(ActionEvent event) {
    }

    @FXML
    private void actionGuardar(ActionEvent event) throws InterruptedException {
         try {
            if (txtId.getText().equals("Nuevo")) {
                System.out.println("org.una.laboratorio.AddEditWatchDepartamentoController.actionguardar()");
                if (!txtDescipcion.getText().isEmpty() && !comboEsatdo.getValue().isEmpty() && !txtDeparta.getText().isEmpty()) {
                    tramiteTipoDTO.setDescripcion(txtDescipcion.getText());
                    if (comboEsatdo.getValue().equals("Activo")) {
                        tramiteTipoDTO.setEstado(true);
                    } else {
                        tramiteTipoDTO.setEstado(false);
                    }
                    tramiteTipoDTO.setDepartamento((DepartamentoDTO) AppContext.getInstance().get("BuscadoDepar"));
                    tramiteTipoDTO.setFechaModificacion(new Date());
                    tramiteTipoDTO.setFechaRegistro(new Date());
                    if(TramiteTipoController.getInstance().add(tramiteTipoDTO)==201){
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar Tramite", ((Stage) txtId.getScene().getWindow()), "Se guardo correctamente");
                        ((Stage) txtId.getScene().getWindow()).close();
                    }else{
                       new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar tramite", ((Stage) txtId.getScene().getWindow()), "No se guardo correctamente"); 
                    }
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar tramite", ((Stage) txtId.getScene().getWindow()), "Rellene los campos necesarios");
                }

            } else {
                if (!txtDescipcion.getText().isEmpty() && !comboEsatdo.getValue().isEmpty()  && !txtDeparta.getText().isEmpty()) {
                    tramiteTipoDTO.setDescripcion(txtDescipcion.getText());
                    if (comboEsatdo.getValue().equals("Activo")) {
                        tramiteTipoDTO.setEstado(true);
                    } else {
                        tramiteTipoDTO.setEstado(false);
                    }
                    tramiteTipoDTO.setDepartamento((DepartamentoDTO) AppContext.getInstance().get("BuscadoDepar"));
                    tramiteTipoDTO.setFechaModificacion(new Date());
                    if(TramiteTipoController.getInstance().Update(tramiteTipoDTO)==200){
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar Tramite", ((Stage) txtId.getScene().getWindow()), "Se guardo correctamente");
                        ((Stage) txtId.getScene().getWindow()).close();
                    }else{
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
