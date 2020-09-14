/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.una.laboratorio.controller.VariacionController;
import org.una.laboratorio.dto.VariacionDTO;
import org.una.laboratorio.utils.Mensaje;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author colo7
 */
public class DisenoTramitesController extends Controller implements Initializable {

    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableColumn<VariacionDTO, String> id;
    @FXML
    private TableColumn<VariacionDTO, String> variacion;
    @FXML
    private TableColumn<VariacionDTO, String> estado;
    @FXML
    private TableColumn<VariacionDTO, String> grupo;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    @FXML
    private TableView<VariacionDTO> tableview;
    public List<VariacionDTO> variacionList;

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
    private void buscar(ActionEvent event) {
    }

    @FXML
    private void borrar(ActionEvent event) {
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void save(ActionEvent event) {
    }

    private void llenarVariacion() {

        id.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getId().toString()));
        variacion.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDescripcion()));
        estado.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
        grupo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getGrupo().toString()));

        try {
            variacionList = VariacionController.getInstance().getAll();
            if (variacionList != null && !variacionList.isEmpty()) {
                tableview.setItems(FXCollections.observableArrayList(variacionList));
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Variacion", null, "Lista Vacía");
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Variacion", null, "Hubo un error");
        }
    }
}
