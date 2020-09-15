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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.una.laboratorio.controller.PermisoController;
import org.una.laboratorio.dto.PermisoDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.FlowController;
import org.una.laboratorio.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author colo7
 */
public class PermisosViewController extends Controller implements Initializable {

    @FXML
    private TableView<PermisoDTO> tableview;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtNombre;
    @FXML
    private ComboBox<String> cbEstado;
    @FXML
    private TextField txtId;

    List<PermisoDTO> lisPer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbEstado.setItems(FXCollections.observableArrayList("Activo","Desactivo"));
        actionPermisosClick();
        llenarTableView();
    }

    private void actionPermisosClick() {
        tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && tableview.selectionModelProperty().get().getSelectedItem() != null) {
                    PermisoDTO per = (PermisoDTO) tableview.selectionModelProperty().get().getSelectedItem();
                    AppContext.getInstance().set("PerObject", per);
                    FlowController.getInstance().goViewInWindowModal("AddEditWatchPermiso", ((Stage) btnBuscar.getScene().getWindow()), false);
                    tableview.selectionModelProperty().get().clearSelection();
                }

            }
        });
    }

    private void llenarTableView() {
        TableColumn<PermisoDTO, String> colID = new TableColumn("ID");
        colID.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getId()));
        TableColumn<PermisoDTO, String> colDescripcion = new TableColumn("Descripcion");
        colDescripcion.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getDescripcion()));
        TableColumn<PermisoDTO, String> colNombre = new TableColumn("Codigo");
        colNombre.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getCodigo()));
        TableColumn<PermisoDTO, String> colestado = new TableColumn("Estado");
        colestado.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
        TableColumn<PermisoDTO, String> colFechaRe = new TableColumn("Fecha Registro");
        colFechaRe.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
        TableColumn<PermisoDTO, String> colFechaMo = new TableColumn("Fecha Modificacion");
        colFechaMo.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaModificacion()));
        tableview.getColumns().addAll(colID, colDescripcion, colNombre, colestado, colFechaRe, colFechaMo);

        try {
            llenarTable();
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
        }
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void buscar(ActionEvent event) {
        tableview.getItems().clear();
        List<PermisoDTO> lisAux = new ArrayList<>();
        System.out.println(txtId.getText());
        if (!txtId.getText().isEmpty()) {
            cbEstado.setValue(null);
            txtNombre.setText("");
            for (int i = 0; i < lisPer.size(); i++) {
                if (txtId.getText().equals(String.valueOf(lisPer.get(i).getId()))) {
                    lisAux.add(lisPer.get(i));
                    tableview.setItems(FXCollections.observableArrayList(lisAux));
                }

            }

        } else if (txtNombre.getText() != null && cbEstado.getValue() != null) {
            boolean estado = false;
            if (cbEstado.getValue().equals("Activo")) {
                estado = true;
            }
            for (int i = 0; i < lisPer.size(); i++) {
                if (lisPer.get(i).getCodigo().toUpperCase().startsWith(txtNombre.getText().toUpperCase()) && lisPer.get(i).isEstado() == estado) {
                    lisAux.add(lisPer.get(i));
                }
            }
            tableview.setItems(FXCollections.observableArrayList(lisAux));
            System.out.println("org.una.laboratorio.DepartamentoViewController.buscar()");
        } else if (cbEstado.getValue() != null) {
            boolean estado = false;
            if (cbEstado.getValue().equals("Activo")) {
                estado = true;
            }
            for (int i = 0; i < lisPer.size(); i++) {
                if (lisPer.get(i).isEstado() == estado) {
                    lisAux.add(lisPer.get(i));
                }
            }
            tableview.setItems(FXCollections.observableArrayList(lisAux));
            System.out.println("org.una.laboratorio.DepartamentoViewController.buscar()");
        } else if (!txtNombre.getText().isEmpty()) {

            for (int i = 0; i < lisPer.size(); i++) {
                if (lisPer.get(i).getCodigo().toUpperCase().startsWith(txtNombre.getText().toUpperCase())) {
                    lisAux.add(lisPer.get(i));
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

    @FXML
    private void save(ActionEvent event) {
        AppContext.getInstance().set("PerObject", null);
        FlowController.getInstance().goViewInWindowModal("AddEditWatchPermiso", ((Stage) btnBuscar.getScene().getWindow()), false);
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

    void llenarTable() {
        try {
            lisPer = PermisoController.getInstance().getAll();
            if (lisPer != null && !lisPer.isEmpty()) {
                tableview.setItems(FXCollections.observableArrayList(lisPer));
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(PermisosViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(PermisosViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PermisosViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
