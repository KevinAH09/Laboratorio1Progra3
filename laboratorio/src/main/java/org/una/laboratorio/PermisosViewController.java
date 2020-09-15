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
import org.una.laboratorio.dto.PermisoOtorgadoDTO;
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

    List<PermisoDTO> lisPer;
    List<PermisoOtorgadoDTO> ListPerOtor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListPerOtor = (List<PermisoOtorgadoDTO>) AppContext.getInstance().get("permisosOTG");
        if (!ListPerOtor.stream().anyMatch(x -> x.getPermisoId().getCodigo().equals("PER1"))) {
            btnGuardar.setVisible(false);
            btnGuardar.setDisable(true);
        }
        actionPermisosClick();
        llenarTableView();
    }

    private void actionPermisosClick() {
        if (ListPerOtor.stream().anyMatch(x -> x.getPermisoId().getCodigo().equals("PER2")) || ListPerOtor.stream().anyMatch(x -> x.getPermisoId().getCodigo().equals("PER3"))) {
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
        if (!txtNombre.getText().isEmpty()) {
            try {
                Object o;
                if (txtNombre.getText().toUpperCase().equals("ACTIVO")) {
                    o = PermisoController.getInstance().getEstado("1");
                    tableview.setItems(FXCollections.observableArrayList((List<PermisoDTO>) o));
                } else if (txtNombre.getText().toUpperCase().equals("DESACTIVO")) {
                    o = PermisoController.getInstance().getEstado("0");
                    tableview.setItems(FXCollections.observableArrayList((List<PermisoDTO>) o));
                } else {
                    o = null;
                    o = PermisoController.getInstance().getId(txtNombre.getText());
                    if (o != null) {
                        lisPer.clear();
                        lisPer.add((PermisoDTO) o);
                        tableview.setItems(FXCollections.observableArrayList(lisPer));
                    } else {

                        o = PermisoController.getInstance().getCodigo(txtNombre.getText());
                        if (o != null) {
                            tableview.setItems(FXCollections.observableArrayList((List<PermisoDTO>) o));
                        } else {
                            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al filtrar", ((Stage) btnBuscar.getScene().getWindow()), "No se encontraron permisos");
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
        AppContext.getInstance().set("PerObject", null);
        FlowController.getInstance().goViewInWindowModal("AddEditWatchPermiso", ((Stage) btnBuscar.getScene().getWindow()), false);
        llenarTable();
    }

    @FXML
    private void actionClearID(KeyEvent event) {

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
