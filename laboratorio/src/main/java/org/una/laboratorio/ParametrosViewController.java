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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.una.laboratorio.controller.ParametroGeneralController;
import org.una.laboratorio.dto.ParametroGeneralDTO;
import org.una.laboratorio.controller.TramiteTipoController;
import org.una.laboratorio.utils.Mensaje;


/**
 *
 * @author colo7
 */
public class ParametrosViewController implements Initializable{

    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableView<ParametroGeneralDTO> tableview;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        actionParametroGeneralClick();
        llenarParametroGeneral();
        
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void actionParametroGeneralClick() {
        tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && tableview.selectionModelProperty().get().getSelectedItem() != null) {
                    ParametroGeneralDTO depa = (ParametroGeneralDTO) tableview.selectionModelProperty().get().getSelectedItem();
                    tableview.selectionModelProperty().get().clearSelection();
                }

            }
        });
    }

    

    

    private void llenarParametroGeneral() {
        TableColumn<ParametroGeneralDTO, String> colCedula = new TableColumn("ID");
        colCedula.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getId()));
        TableColumn<ParametroGeneralDTO, String> colNombre = new TableColumn("Descripcion");
        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDescripcion()));
        TableColumn<ParametroGeneralDTO, String> colestado = new TableColumn("Estado");
        colestado.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
        TableColumn<ParametroGeneralDTO, String> colFechaRe = new TableColumn("Fecha Registro");
        colFechaRe.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
        TableColumn<ParametroGeneralDTO, String> colFechaMo = new TableColumn("Fecha Modificacion");
        colFechaMo.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaModificacion()));
        tableview.getColumns().addAll(colCedula,colNombre,colestado, colFechaRe, colFechaMo);

        try {
            List<ParametroGeneralDTO> tramiteList = ParametroGeneralController.getInstance().getAll();
            if (tramiteList != null && !tramiteList.isEmpty()) {
                tableview.setItems(FXCollections.observableArrayList(tramiteList));
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
        }
    }
}
