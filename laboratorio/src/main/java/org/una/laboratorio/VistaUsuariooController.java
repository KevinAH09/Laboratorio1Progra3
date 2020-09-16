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
import org.una.laboratorio.controller.Usuariocontroller;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class VistaUsuariooController extends Controller implements Initializable {

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
    @FXML
    private Button guardar;
    UsuarioDTO usuario;
    UsuarioDTO usaurioGuardar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuario = new UsuarioDTO();
        usaurioGuardar = new UsuarioDTO();
        cmbEstado.setItems(FXCollections.observableArrayList("Activo", "Inactivo"));
        cmbJefe.setItems(FXCollections.observableArrayList("Activo", "Inactivo"));
        if (AppContext.getInstance().get("selec") != null) {
            usuario = (UsuarioDTO) AppContext.getInstance().get("selec");
            txtId.setText(usuario.getId().toString());
            txtId.setDisable(true);
            txtcedula.setText(usuario.getCedula());
            txtnombre.setText(usuario.getNombreCompleto());

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

                fechaModi.setText(usuario.getFechaModificacion().toString());
                fechaRegis.setText(usuario.getFechaRegistro().toString());
            }
        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void cancelar(ActionEvent event) {
        ((Stage) guardar.getScene().getWindow()).close();
    }

    @FXML
    private void actionGuardar(ActionEvent event) {
        try {
            if (!txtcedula.getText().isEmpty() && !txtnombre.getText().isEmpty() && !cmbEstado.getValue().isEmpty()) {
                usaurioGuardar.setId(usuario.getId());
                usaurioGuardar.setCedula(txtcedula.getText());
                if (cmbEstado.getValue().equals("Activo")) {
                    usaurioGuardar.setEstado(true);
                } else {
                    usaurioGuardar.setEstado(false);
                }
                if (cmbJefe.getValue().equals("Activo")) {
                    usaurioGuardar.setEsJefe(true);
                } else {
                    usaurioGuardar.setEsJefe(false);
                }
                usaurioGuardar.setDepartamento(usuario.getDepartamento());
                usaurioGuardar.setFechaRegistro(new Date());
                usaurioGuardar.setFechaModificacion(new Date());
                usaurioGuardar.setNombreCompleto(txtnombre.getText());
                System.out.println("org.una.laboratorio.VistaUsuariooController.actionGuardar()" + Usuariocontroller.getInstance().Update(usaurioGuardar));
                if (Usuariocontroller.getInstance().Update(usaurioGuardar) == 200) {
                    new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar usuario", ((Stage) txtId.getScene().getWindow()), "Se guardó correctamente");
                    ((Stage) txtId.getScene().getWindow()).close();
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar el usuario", ((Stage) txtId.getScene().getWindow()), "No se");
                }
                
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar  el usuario", ((Stage) txtId.getScene().getWindow()), "Rellene los campos necesarios");
            }

        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(VistaUsuariooController.class.getName()).log(Level.SEVERE, null, ex+"dsfgd");
        }

    }

}
