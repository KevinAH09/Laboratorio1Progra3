/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableView<DepartamentoDTO> tableview;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtNombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actionDepartamentoClick();
        llenarDepartamento();
    }

    @Override
    public void initialize() {

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
        TableColumn<DepartamentoDTO, String> colID = new TableColumn("ID");
        colID.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getId()));
        TableColumn<DepartamentoDTO, String> colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNombre()));
        TableColumn<DepartamentoDTO, Boolean> colCedula = new TableColumn("Estado");
        colCedula.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
        TableColumn<DepartamentoDTO, String> colFechaRe = new TableColumn("Fecha Registro");
        colFechaRe.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
        TableColumn<DepartamentoDTO, String> colFechaMo = new TableColumn("Fecha Modificacion");
        colFechaMo.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaModificacion()));
        tableview.getColumns().addAll(colID, colNombre, colCedula, colFechaRe, colFechaMo);

        llenarTable();
        System.out.println(colCedula.getCellData(0));
    }

    @FXML
    private void buscar(ActionEvent event) {
        tableview.getItems().clear();

        if (!txtNombre.getText().isEmpty()) {
            try {
                Object o;
                if (txtNombre.getText().toUpperCase().equals("ACTIVO")) {
                    o = DepartamentoController.getInstance().getEstado("1");
                    tableview.setItems(FXCollections.observableArrayList((List<DepartamentoDTO>) o));
                } else if (txtNombre.getText().toUpperCase().equals("DESACTIVO")) {
                    o = DepartamentoController.getInstance().getEstado("0");
                    tableview.setItems(FXCollections.observableArrayList((List<DepartamentoDTO>) o));
                } else {
                    o = null;
                    o = DepartamentoController.getInstance().getId(txtNombre.getText());
                    if (o != null) {
                        departamentoList.clear();
                        departamentoList.add((DepartamentoDTO) o);
                        tableview.setItems(FXCollections.observableArrayList(departamentoList));
                    } else {

                        o = DepartamentoController.getInstance().getNombre(txtNombre.getText());
                        if (o != null) {
                            tableview.setItems(FXCollections.observableArrayList((List<DepartamentoDTO>) o));
                        } else {
                            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al filtrar", ((Stage) btnBuscar.getScene().getWindow()), "No se encontraron departamentos");
                        }
                    }

                }

            } catch (InterruptedException ex) {
                Logger.getLogger(DepartamentoViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(DepartamentoViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DepartamentoViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            llenarTable();
        }

    }

    @FXML
    private void borrar(ActionEvent event) {
        txtNombre.setText("");
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
            Logger.getLogger(DepartamentoViewController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(DepartamentoViewController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DepartamentoViewController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void actionClearID(KeyEvent event) {
//        txtId.setText("");
    }

}
