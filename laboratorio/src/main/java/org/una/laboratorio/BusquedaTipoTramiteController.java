/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import org.una.laboratorio.controller.TramiteTipoController;
import org.una.laboratorio.dto.TramiteTipoDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author cfugu
 */
public class BusquedaTipoTramiteController extends Controller implements Initializable {

    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableView<TramiteTipoDTO> tableview;
    @FXML
    private Button btnCancelar;
    public List<TramiteTipoDTO> tipoList;
    public List<TramiteTipoDTO> tipoList2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actionTramiteClick();
        llenarTramites();
    }

    @FXML
    private void buscar(ActionEvent event) {
        tableview.getItems().clear();
        try {
            tipoList2 = TramiteTipoController.getInstance().getAll();

            if (txtBuscar != null) {
                tipoList2 = tipoList2.stream().filter(x -> x.getDescripcion().toUpperCase().startsWith(txtBuscar.getText().toUpperCase())).collect(Collectors.toList());
                tableview.setItems(FXCollections.observableArrayList(tipoList2));

                actionTramiteClick();
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Variacion", null, "Lista Vacía");

            }
        } catch (Exception e) {

            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Variacion", null, "Hubo un error");
        }
    }

    @FXML
    private void borrar(ActionEvent event) {
        tableview.getItems().clear();
        llenarTramites();
    }

    @FXML
    private void cancel(ActionEvent event) {
        AppContext.getInstance().set("BuscadoTra", null);
        ((Stage) btnBuscar.getScene().getWindow()).close();
    }

    private void actionTramiteClick() {
        tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && tableview.selectionModelProperty().get().getSelectedItem() != null) {
                    TramiteTipoDTO tra = (TramiteTipoDTO) tableview.selectionModelProperty().get().getSelectedItem();
                    AppContext.getInstance().set("BuscadoTra", tra);
                    ((Stage) btnBuscar.getScene().getWindow()).close();
                }

            }
        });
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void llenarTramites() {
        TableColumn<TramiteTipoDTO, String> colNombre = new TableColumn("Descripcion");
        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDescripcion()));
        TableColumn<TramiteTipoDTO, String> colCedula = new TableColumn("Estado");
        colCedula.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
        TableColumn<TramiteTipoDTO, String> colFechaRe = new TableColumn("Fecha Registro");
        colFechaRe.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getDepartamento()));
        TableColumn<TramiteTipoDTO, String> colFechaCre = new TableColumn("Fecha Creacion");
        colFechaCre.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
        TableColumn<TramiteTipoDTO, String> colFechaMo = new TableColumn("Fecha Modificacion");
        colFechaMo.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
        tableview.getColumns().addAll(colNombre, colCedula, colFechaRe, colFechaCre, colFechaMo);

        try {
            tipoList = TramiteTipoController.getInstance().getAll();
            if (tipoList != null && !tipoList.isEmpty()) {
                tableview.setItems(FXCollections.observableArrayList(tipoList));
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de tramite", null, "La lista está nula o vacía");
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de tramite", null, "Hubo un error al obtener los datos a cargar");
        }
    }
}
