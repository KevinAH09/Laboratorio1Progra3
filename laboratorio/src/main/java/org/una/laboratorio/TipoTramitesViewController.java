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
import org.una.laboratorio.controller.TramiteTipoController;
import org.una.laboratorio.dto.TramiteTipoDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.FlowController;
import org.una.laboratorio.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author colo7
 */
public class TipoTramitesViewController extends Controller implements Initializable {

    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableView<TramiteTipoDTO> tableview;
    @FXML
    private Button btnGuardar;
    @FXML
    private ComboBox<String> cbEstado;
    @FXML
    private TextField txtId;

    List<TramiteTipoDTO> tramiteList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbEstado.setItems(FXCollections.observableArrayList("Activo", "Desactivo"));
        actionDepartamentoClick();
        llenarDepartamento();
    }

    private void actionDepartamentoClick() {
        tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && tableview.selectionModelProperty().get().getSelectedItem() != null) {
                    TramiteTipoDTO tra = (TramiteTipoDTO) tableview.selectionModelProperty().get().getSelectedItem();
                    AppContext.getInstance().set("TraObject", tra);
                    FlowController.getInstance().goViewInWindowModal("AddEditWatchTipoTramite", ((Stage) btnBuscar.getScene().getWindow()), false);
                    tableview.selectionModelProperty().get().clearSelection();
                }

            }
        });
    }

    private void llenarDepartamento() {
        TableColumn<TramiteTipoDTO, String> colid = new TableColumn("ID");
        colid.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getId().toString()));
        TableColumn<TramiteTipoDTO, String> colDepar = new TableColumn("Departamento");
        colDepar.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDepartamento().getNombre()));
        TableColumn<TramiteTipoDTO, String> colNombre = new TableColumn("Descripcion");
        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDescripcion()));
        TableColumn<TramiteTipoDTO, String> colCedula = new TableColumn("Estado");
        colCedula.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
        TableColumn<TramiteTipoDTO, String> colFechaRe = new TableColumn("Fecha Registro");
        colFechaRe.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
        TableColumn<TramiteTipoDTO, String> colFechaMo = new TableColumn("Fecha Modificacion");
        colFechaMo.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaModificacion()));
        tableview.getColumns().addAll(colid, colDepar, colNombre, colCedula, colFechaRe, colFechaMo);

        try {
            llenarTable();
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
        }
    }

    @FXML
    private void buscar(ActionEvent event) {
        tableview.getItems().clear();
        tramiteList.clear();
        List<TramiteTipoDTO> lisAux = new ArrayList<>();
        System.out.println(txtId.getText());

        try {
            if (!txtId.getText().isEmpty()) {
                Object o = TramiteTipoController.getInstance().getId(txtId.getText());
                if (o != null) {
                    tramiteList.add((TramiteTipoDTO)o);
                    tableview.setItems(FXCollections.observableArrayList(tramiteList));
                }else{
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al filtrar", ((Stage) btnBuscar.getScene().getWindow()), "No se encontraron tramites");
                }

            } else if (cbEstado.getValue() != null) {
                if (cbEstado.getValue().equals("Activo")) {
                    Object o = TramiteTipoController.getInstance().getEstado("1");
                    if (o != null) {
                        tableview.setItems(FXCollections.observableArrayList((List<TramiteTipoDTO>) o));
                    }else{
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Error al filtrar", ((Stage) btnBuscar.getScene().getWindow()), "No se encontraron tramites");
                    }
                } else {
                    Object o = TramiteTipoController.getInstance().getEstado("0");
                    if (o != null) {
                        tableview.setItems(FXCollections.observableArrayList((List<TramiteTipoDTO>) o));
                    }else{
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Error al filtrar", ((Stage) btnBuscar.getScene().getWindow()), "No se encontraron tramites");
                    }
                }
            } else {
                llenarTable();
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(TipoTramitesViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(TipoTramitesViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TipoTramitesViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void borrar(ActionEvent event) {
        txtId.setText("");
        cbEstado.setValue("");
        cbEstado.setPromptText("Estado");
    }

    @FXML
    private void save(ActionEvent event) {
        AppContext.getInstance().set("TraObject", null);
        FlowController.getInstance().goViewInWindowModal("AddEditWatchTipoTramite", ((Stage) btnBuscar.getScene().getWindow()), Boolean.FALSE);
        llenarTable();
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void actionClearID(ActionEvent event) {
        txtId.setText("");
    }

    @FXML
    private void presID(KeyEvent event) {
        cbEstado.setValue("");
        cbEstado.setPromptText("Estado");
    }

    void llenarTable() {
        try {
            tramiteList = TramiteTipoController.getInstance().getAll();
            if (tramiteList != null && !tramiteList.isEmpty()) {
                tableview.setItems(FXCollections.observableArrayList(tramiteList));
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TipoTramitesViewController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(TipoTramitesViewController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TipoTramitesViewController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
