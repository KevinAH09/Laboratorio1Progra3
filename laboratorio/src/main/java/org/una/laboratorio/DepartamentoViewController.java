/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.stage.Stage;
import org.una.laboratorio.controller.DepartamentoController;
import org.una.laboratorio.dto.DepartamentoDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.Mensaje;
import org.una.laboratorio.utils.FlowController;

/**
 * FXML Controller class
 *
 * @author colo7
 */
public class DepartamentoViewController extends Controller implements Initializable {

    public List<DepartamentoDTO> departamentoList;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableView<DepartamentoDTO> tableview;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actionDepartamentoClick();
        llenarDepartamento();
    }

    @Override
    public void initialize() {

    }

    private void actionFilter(ActionEvent event) {
        if (txtBuscar != null) {
            tableview.setItems(FXCollections.observableArrayList(departamentoList.stream().filter(x -> x.getNombre().toUpperCase().startsWith(txtBuscar.getText().toUpperCase())).collect(Collectors.toList())));
        } else {
            tableview.setItems(FXCollections.observableArrayList(departamentoList));
        }
    }

    private void actionDepartamentoClick() {
        tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && tableview.selectionModelProperty().get().getSelectedItem() != null) {
                    DepartamentoDTO depa = (DepartamentoDTO) tableview.selectionModelProperty().get().getSelectedItem();
                    AppContext.getInstance().set("DepaObject", depa);
                    FlowController.getInstance().goViewInWindowModal("AddEditWatchDepartamento", ((Stage) btnBuscar.getScene().getWindow()), false);
                    tableview.selectionModelProperty().get().clearSelection();
                }

            }
        });
    }

    private void llenarDepartamento() {
        TableColumn<DepartamentoDTO, String> colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNombre()));
        TableColumn<DepartamentoDTO, String> colCedula = new TableColumn("Estado");
        colCedula.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
        TableColumn<DepartamentoDTO, String> colFechaRe = new TableColumn("Fecha Registro");
        colFechaRe.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
        TableColumn<DepartamentoDTO, String> colFechaMo = new TableColumn("Fecha Modificacion");
        colFechaMo.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaModificacion()));
        tableview.getColumns().addAll(colNombre, colCedula, colFechaRe, colFechaMo);
        llenarTable();

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
        AppContext.getInstance().set("DepaObject", null);
        FlowController.getInstance().goViewInWindowModal("AddEditWatchDepartamento", ((Stage) btnBuscar.getScene().getWindow()), Boolean.FALSE);
        llenarTable();

    }

    void llenarTable() {
        try {
            departamentoList = DepartamentoController.getInstance().getAll();
            if (departamentoList != null && !departamentoList.isEmpty()) {
                tableview.setItems(FXCollections.observableArrayList(departamentoList));
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de departamentos", ((Stage) btnBuscar.getScene().getWindow()), "No existen departamentos");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(DepartamentoViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(DepartamentoViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DepartamentoViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
