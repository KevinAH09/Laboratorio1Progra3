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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.Mensaje;
import org.una.laboratorio.dto.ParametroGeneralDTO;
import org.una.laboratorio.controller.DepartamentoController;
import org.una.laboratorio.controller.ParametroGeneralController;
import org.una.laboratorio.dto.PermisoOtorgadoDTO;

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
    List<PermisoOtorgadoDTO> ListPerOtor;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListPerOtor = (List<PermisoOtorgadoDTO>) AppContext.getInstance().get("permisosOTG");
        parametroGeneralDTO = new ParametroGeneralDTO();
        comboEstado.setItems(FXCollections.observableArrayList("Activo", "Desactivo"));
        if (AppContext.getInstance().get("ParaObject") != null) {
            if (!ListPerOtor.stream().anyMatch(x -> x.getPermisoId().getCodigo().equals("PAR2"))) {
                txtNombre.setDisable(true);
                txtDescripcion.setDisable(true);
                txtValor.setDisable(true);
            }else if(!ListPerOtor.stream().anyMatch(x -> x.getPermisoId().getCodigo().equals("PAR3"))){
                comboEstado.setDisable(true);
            }
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
            comboEstado.setValue("Activo");
            comboEstado.setDisable(true);
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
        ((Stage) txtId.getScene().getWindow()).close();
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
                    
                    if(ParametroGeneralController.getInstance().add(parametroGeneralDTO)==201){
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar Parametro", ((Stage) txtId.getScene().getWindow()), "Se guardo correctamente");
                        ((Stage) txtId.getScene().getWindow()).close();
                    }else{
                       new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar Parametro", ((Stage) txtId.getScene().getWindow()), "No se guardo correctamente"); 
                    }
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear Parametro", ((Stage) txtId.getScene().getWindow()), "Rellene los campos necesarios");
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
                     if(ParametroGeneralController.getInstance().Update(parametroGeneralDTO)==200){
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar Parametro", ((Stage) txtId.getScene().getWindow()), "Se guardo correctamente");
                        ((Stage) txtId.getScene().getWindow()).close();
                    }else{
                       new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar Parametro", ((Stage) txtId.getScene().getWindow()), "No se guardo correctamente"); 
                    }
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al modificar Parametro", ((Stage) txtId.getScene().getWindow()), "Rellene los campos necesarios");
                }
            }

        } catch (ExecutionException ex) {
            Logger.getLogger(AddEditWatchParametroController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddEditWatchParametroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
