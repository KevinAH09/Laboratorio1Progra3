/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.una.laboratorio.controller.ParametroGeneralController;
import org.una.laboratorio.dto.ParametroGeneralDTO;
import org.una.laboratorio.controller.TramiteTipoController;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.FlowController;
import org.una.laboratorio.utils.Mensaje;

/**
 *
 * @author colo7
 */
public class ParametrosViewController implements Initializable {

    @FXML
    private TableView<ParametroGeneralDTO> tableview;
    @FXML
    private Button btnAgregar;
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<String> cbEstado;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    List<ParametroGeneralDTO> tramiteList;

    @FXML
    private void buscar(ActionEvent event) {
        cbEstado.setItems(FXCollections.observableArrayList("Activo","Desactivo"));
        tableview.getItems().clear();
        List<ParametroGeneralDTO> lisAux = new ArrayList<>();
        System.out.println(txtId.getText());
        if (!txtId.getText().isEmpty()) {
            cbEstado.setValue(null);
            txtNombre.setText("");
            for (int i = 0; i < tramiteList.size(); i++) {
                if (txtId.getText().equals(String.valueOf(tramiteList.get(i).getId()))) {
                    lisAux.add(tramiteList.get(i));
                    tableview.setItems(FXCollections.observableArrayList(lisAux));
                }

            }

        } else if (txtNombre.getText() != null && cbEstado.getValue() != null) {
            boolean estado = false;
            if (cbEstado.getValue().equals("Activo")) {
                estado = true;
            }
            for (int i = 0; i < tramiteList.size(); i++) {
                if (tramiteList.get(i).getNombre().toUpperCase().startsWith(txtNombre.getText().toUpperCase()) && tramiteList.get(i).isEstado() == estado) {
                    lisAux.add(tramiteList.get(i));
                }
            }
            tableview.setItems(FXCollections.observableArrayList(lisAux));
            System.out.println("org.una.laboratorio.DepartamentoViewController.buscar()");
        } else if (cbEstado.getValue() != null) {
            boolean estado = false;
            if (cbEstado.getValue().equals("Activo")) {
                estado = true;
            }
            for (int i = 0; i < tramiteList.size(); i++) {
                if (tramiteList.get(i).isEstado() == estado) {
                    lisAux.add(tramiteList.get(i));
                }
            }
            tableview.setItems(FXCollections.observableArrayList(lisAux));
            System.out.println("org.una.laboratorio.DepartamentoViewController.buscar()");
        } else if (!txtNombre.getText().isEmpty()) {

            for (int i = 0; i < tramiteList.size(); i++) {
                if (tramiteList.get(i).getNombre().toUpperCase().startsWith(txtNombre.getText().toUpperCase())) {
                    lisAux.add(tramiteList.get(i));
                }
            }
            tableview.setItems(FXCollections.observableArrayList(lisAux));
            System.out.println("org.una.laboratorio.DepartamentoViewController.buscar()");
        } else {
            llenarTable();
        }
    }

    @FXML
    private void borrar(ActionEvent event) {
        txtNombre.setText("");
        txtId.setText("");
        cbEstado.setValue(null);
        cbEstado.setPromptText("Estado");
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
                    ParametroGeneralDTO para = (ParametroGeneralDTO) tableview.selectionModelProperty().get().getSelectedItem();
                    AppContext.getInstance().set("ParaObject", para);
                    FlowController.getInstance().goViewInWindowModal("AddEditWatchParametro", ((Stage) btnBuscar.getScene().getWindow()), false);
                    tableview.selectionModelProperty().get().clearSelection();
                }

            }
        });
    }

    private void llenarParametroGeneral() {
        TableColumn<ParametroGeneralDTO, String> colCedula = new TableColumn("ID");
        colCedula.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getId()));
        TableColumn<ParametroGeneralDTO, String> colNombbre= new TableColumn("Nombre");
        colNombbre.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getNombre()));
        TableColumn<ParametroGeneralDTO, String> colNombre = new TableColumn("Descripcion");
        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDescripcion()));
        TableColumn<ParametroGeneralDTO, String> colValor = new TableColumn("Valor");
        colValor.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getValor()));
        TableColumn<ParametroGeneralDTO, String> colestado = new TableColumn("Estado");
        colestado.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
        TableColumn<ParametroGeneralDTO, String> colFechaRe = new TableColumn("Fecha Registro");
        colFechaRe.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
        TableColumn<ParametroGeneralDTO, String> colFechaMo = new TableColumn("Fecha Modificacion");
        colFechaMo.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaModificacion()));
        tableview.getColumns().addAll(colCedula,colNombbre, colNombre, colValor, colestado, colFechaRe, colFechaMo);

        try {
            llenarTable();
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error Parametros", ((Stage) btnBuscar.getScene().getWindow()), "Ocurrio un error al cunsultar parametros");
        }
    }

    void llenarTable() {
        try {
            tramiteList = ParametroGeneralController.getInstance().getAll();
            if (tramiteList != null && !tramiteList.isEmpty()) {
                tableview.setItems(FXCollections.observableArrayList(tramiteList));
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Parametros Vacios", ((Stage) btnBuscar.getScene().getWindow()), "No existen parametros");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ParametrosViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(ParametrosViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParametrosViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void add(ActionEvent event) {
        AppContext.getInstance().set("ParaObject", null);
        FlowController.getInstance().goViewInWindowModal("AddEditWatchParametro", ((Stage) btnBuscar.getScene().getWindow()), Boolean.FALSE);
        llenarTable();

    }

    @FXML
    private void actionClearID(KeyEvent event) {
        txtId.setText("");
    }

    @FXML
    private void actionClearID(ActionEvent event) {
        txtId.setText("");
    }

    @FXML
    private void presID(KeyEvent event) {
        txtNombre.setText("");
        cbEstado.setValue(null);
        cbEstado.setPromptText("Estado");
    }
}
