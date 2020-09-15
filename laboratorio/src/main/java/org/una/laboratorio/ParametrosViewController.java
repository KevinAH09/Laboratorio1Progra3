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
import org.una.laboratorio.dto.ParametroGeneralDTO;
import org.una.laboratorio.dto.PermisoOtorgadoDTO;
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
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;

    List<ParametroGeneralDTO> tramiteList;
    List<PermisoOtorgadoDTO> ListPerOtor;

    @FXML
    private void buscar(ActionEvent event) {

        tableview.getItems().clear();
        if (!txtNombre.getText().isEmpty()) {
            try {
                Object o;
                if (txtNombre.getText().toUpperCase().equals("ACTIVO")) {
                    o = ParametroGeneralController.getInstance().getEstado("1");
                    tableview.setItems(FXCollections.observableArrayList((List<ParametroGeneralDTO>) o));
                } else if (txtNombre.getText().toUpperCase().equals("DESACTIVO")) {
                    o = ParametroGeneralController.getInstance().getEstado("0");
                    tableview.setItems(FXCollections.observableArrayList((List<ParametroGeneralDTO>) o));
                } else {
                    o = ParametroGeneralController.getInstance().getId(txtNombre.getText());
                    if (o != null) {
                        List<ParametroGeneralDTO> list = new ArrayList<>();
                        list.add((ParametroGeneralDTO) o);
                        System.out.println(((ParametroGeneralDTO) o).getNombre());
                        tableview.setItems(FXCollections.observableArrayList(list));
                    } else {

                        o = ParametroGeneralController.getInstance().getNombre(txtNombre.getText());
                        if (o != null) {
                            tableview.setItems(FXCollections.observableArrayList((List<ParametroGeneralDTO>) o));
                        } else {
                            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al filtrar", ((Stage) btnBuscar.getScene().getWindow()), "No se encontraron parametros");
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ListPerOtor = (List<PermisoOtorgadoDTO>) AppContext.getInstance().get("permisosOTG");
        if (!ListPerOtor.stream().anyMatch(x -> x.getPermisoId().getCodigo().equals("PAR1"))) {
            btnAgregar.setVisible(false);
            btnAgregar.setDisable(true);
        }
        actionParametroGeneralClick();
        llenarParametroGeneral();

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void actionParametroGeneralClick() {
        if (ListPerOtor.stream().anyMatch(x -> x.getPermisoId().getCodigo().equals("PAR2")) || ListPerOtor.stream().anyMatch(x -> x.getPermisoId().getCodigo().equals("PAR3"))) {
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
    }

    private void llenarParametroGeneral() {
        TableColumn<ParametroGeneralDTO, String> colCedula = new TableColumn("ID");
        colCedula.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getId()));
        TableColumn<ParametroGeneralDTO, String> colNombbre = new TableColumn("Nombre");
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
        tableview.getColumns().addAll(colCedula, colNombbre, colNombre, colValor, colestado, colFechaRe, colFechaMo);

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

    }

}
