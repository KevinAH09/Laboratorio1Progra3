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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.una.laboratorio.controller.DepartamentoController;
import org.una.laboratorio.controller.TramiteTipoController;
import org.una.laboratorio.controller.Usuariocontroller;
import org.una.laboratorio.dto.DepartamentoDTO;
import org.una.laboratorio.dto.TramiteTipoDTO;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author colo7
 */
public class DepartamentoViewController extends Controller implements Initializable {

//    private TextField texFieldFiltro;
    
//    private TableView<DepartamentoDTO> tableviewDepartamento;
//    private TableView<UsuarioDTO> tableviewVariaciones;
//    private TableView<TramiteTipoDTO> tableviewTramiteTipo;
//    private TableView<UsuarioDTO> tableviewRegistro;
//    private TableView<UsuarioDTO> tableviewParametro;
    
    public List<DepartamentoDTO> departamentoList;
//    public List<TramiteTipoDTO> tramiteTipoList;
//    private HBox HboxTable;
    /**
     * Initializes the controller class.
     */
//    String objeto;
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
        
//        objeto = (String) AppContext.getInstance().get("objetoTabla");
//        if (objeto.equals("Usuarios")) {
            

//        } else if (objeto.equals("Departamentos")) {
            actionDepartamentoClick();
            llenarDepartamento();
//        } else if (objeto.equals("Tremites")) {
//            actionTipoTramiteClick();
//            llenarTramiteTipo();
//        }

    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    private void actionFilter(ActionEvent event) {
        //if (txtClave.isDisable() && txtUsuario.isDisable()) {
//        if (objeto.equals("Usuarios")) {
//            if (texFieldFiltro != null) {
//                tableviewUsuario.setItems(FXCollections.observableArrayList(usuarioList.stream().filter(x -> x.getNombreCompleto().toUpperCase().startsWith(texFieldFiltro.getText().toUpperCase())).collect(Collectors.toList())));
//            } else {
//                tableviewUsuario.setItems(FXCollections.observableArrayList(usuarioList));
//            }
//        } else if (objeto.equals("Departamentos")) {
            if (txtBuscar != null) {
                tableview.setItems(FXCollections.observableArrayList(departamentoList.stream().filter(x -> x.getNombre().toUpperCase().startsWith(txtBuscar.getText().toUpperCase())).collect(Collectors.toList())));
            } else {
                tableview.setItems(FXCollections.observableArrayList(departamentoList));
            }
//        } else if (objeto.equals("Tremites")) {
//            if (texFieldFiltro != null) {
//                tableviewTramiteTipo.setItems(FXCollections.observableArrayList(tramiteTipoList.stream().filter(x -> x.getDescripcion().toUpperCase().startsWith(texFieldFiltro.getText().toUpperCase())).collect(Collectors.toList())));
//            } else {
//                tableviewTramiteTipo.setItems(FXCollections.observableArrayList(tramiteTipoList));
//            }
//        }
        //}
    }


//    private void actionTipoTramiteClick() {
//        tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                if (mouseEvent.getClickCount() == 2 && tableview.selectionModelProperty().get().getSelectedItem() != null) {
//                    TramiteTipoDTO depa = (TramiteTipoDTO) tableview.selectionModelProperty().get().getSelectedItem();
//                    AppContext.getInstance().set("selec", depa);
//                    System.out.println(".handle()");
//                    tableviewTramiteTipo.selectionModelProperty().get().clearSelection();
//                }
//
//            }
//        });
//    }

    private void actionDepartamentoClick() {
        tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && tableview.selectionModelProperty().get().getSelectedItem() != null) {
                    DepartamentoDTO depa = (DepartamentoDTO) tableview.selectionModelProperty().get().getSelectedItem();
                    AppContext.getInstance().set("selec", depa);
                    System.out.println(".handle()");
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

        try {
            departamentoList = DepartamentoController.getInstance().getAll();
            if (departamentoList != null && !departamentoList.isEmpty()) {
                tableview.setItems(FXCollections.observableArrayList(departamentoList));
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
        }
    }

//    private void llenarTramiteTipo() {
//        TableColumn<TramiteTipoDTO, String> colNombre = new TableColumn("Descripcion");
//        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDescripcion()));
//        TableColumn<TramiteTipoDTO, String> colCedula = new TableColumn("Estado");
//        colCedula.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
//        TableColumn<TramiteTipoDTO, String> colFechaRe = new TableColumn("Fecha Registro");
//        colFechaRe.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
//        TableColumn<TramiteTipoDTO, String> colFechaMo = new TableColumn("Fecha Modificacion");
//        colFechaMo.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaModificacion()));
//         tableviewTramiteTipo.setMaxSize(HboxTable.getPrefWidth(), HboxTable.getPrefHeight());
//        tableviewTramiteTipo.setMinSize(HboxTable.getPrefWidth(), HboxTable.getPrefHeight());
//        tableviewTramiteTipo.getColumns().addAll(colNombre, colCedula, colFechaRe, colFechaMo);
//
//        try {
//            tramiteTipoList = TramiteTipoController.getInstance().getAll();
//            if (tramiteTipoList != null && !tramiteTipoList.isEmpty()) {
//                tableviewTramiteTipo.setItems(FXCollections.observableArrayList(tramiteTipoList));
//                HboxTable.getChildren().clear();
//                HboxTable.getChildren().add(tableviewTramiteTipo);
//            } else {
//                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
//            }
//        } catch (Exception e) {
//            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
//        }
//    }

//    private void actionMovenTam(MouseEvent event) {
////        tableviewUsuario.setMaxSize(HboxTable.getPrefWidth(), HboxTable.getPrefHeight());
////        tableviewUsuario.setMinSize(HboxTable.getPrefWidth(), HboxTable.getPrefHeight());
//        tableviewTramiteTipo.setMaxSize(HboxTable.getPrefWidth(), HboxTable.getPrefHeight());
//        tableviewTramiteTipo.setMinSize(HboxTable.getPrefWidth(), HboxTable.getPrefHeight());
//        tableviewDepartamento.setMaxSize(HboxTable.getPrefWidth(), HboxTable.getPrefHeight());
//        tableviewDepartamento.setMinSize(HboxTable.getPrefWidth(), HboxTable.getPrefHeight());
//    }

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
}
