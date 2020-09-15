/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.una.laboratorio.controller.VariacionController;
import org.una.laboratorio.dto.VariacionDTO;
import org.una.laboratorio.utils.Mensaje;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.una.laboratorio.controller.RequisitoController;
import org.una.laboratorio.controller.TramiteTipoController;
import org.una.laboratorio.dto.RequisitoDTO;
import org.una.laboratorio.dto.TramiteTipoDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.FlowController;

/**
 * FXML Controller class
 *
 * @author colo7
 */
public class DisenoTramitesController extends Controller implements Initializable {

    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableColumn<VariacionDTO, String> id;
    @FXML
    private TableColumn<VariacionDTO, String> variacion;
    @FXML
    private TableColumn<VariacionDTO, String> estado;
    @FXML
    private TableColumn<VariacionDTO, String> grupo;
    @FXML
    private TableView<VariacionDTO> tableview;
    public List<VariacionDTO> variacionList;
    public List<VariacionDTO> variacionList2 = new ArrayList();
    public List<RequisitoDTO> requisitosList;
    public List<TramiteTipoDTO> tramiteList;
    String tra;
    @FXML
    private Button btnGuardarVar;
    @FXML
    private TableView<RequisitoDTO> tableViewReq;
    @FXML
    private TableColumn<RequisitoDTO, String> idReq;
    @FXML
    private TableColumn<RequisitoDTO, String> desReq;
    @FXML
    private TableColumn<RequisitoDTO, String> estReq;
    @FXML
    private Button btnCancelarReq;
    @FXML
    private Button btnGuardarReq;
    @FXML
    private Button btnRequisito;
    @FXML
    private Button btnVar;
    @FXML
    private TreeView<String> treeVar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getId().toString()));
        variacion.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDescripcion()));
        estado.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
        grupo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getGrupo().toString()));
//        llenarVariacion();
        llenarRequisitos();

    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void buscar(ActionEvent event) {
        tableview.getItems().clear();

//        TableColumn<VariacionDTO, String> id = new TableColumn("ID");
//        id.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getId().toString()));
//        TableColumn<VariacionDTO, String> variacion = new TableColumn("Descripcion");
//        variacion.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDescripcion()));
//        TableColumn<VariacionDTO, String> estado = new TableColumn("Estado");
//        estado.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
//        TableColumn<VariacionDTO, String> grupo = new TableColumn("Grupo");
//        grupo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getGrupo().toString()));
//        tableview.getColumns().addAll(id, variacion, estado, grupo);
        try {
            variacionList = VariacionController.getInstance().getAll();
            tramiteList = TramiteTipoController.getInstance().getAll();
            System.out.println(tramiteList.get(0).getDescripcion());
            tra = "";
            if (txtBuscar != null) {
                tramiteList = tramiteList.stream().filter(x -> x.getDescripcion().toUpperCase().startsWith(txtBuscar.getText().toUpperCase())).collect(Collectors.toList());
                Long val = tramiteList.get(0).getId();
                tra = tramiteList.get(0).getDescripcion().toString();
                tableview.setStyle("-fx-selection-bar: red; -fx-selection-bar-non-focused: salmon;");
                tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                tableview.getSelectionModel().setCellSelectionEnabled(true);
                tableview.setItems(FXCollections.observableArrayList(variacionList.stream().filter(y -> y.getTramiteTipo().getId() == val).collect(Collectors.toList())));
//                llenarTree();
                actionVariacionClick();
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Variacion", null, "Lista Vacía");
//                llenarTree();
            }
        } catch (Exception e) {
//            llenarTree();
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Variacion", null, "Hubo un error");
        }
    }

    @FXML
    private void borrar(ActionEvent event) {
    }

    private void llenarVariacion() {

        id.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getId().toString()));
        variacion.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDescripcion()));
        estado.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
        grupo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getGrupo().toString()));

        try {
            variacionList = VariacionController.getInstance().getAll();
            if (variacionList != null && !variacionList.isEmpty()) {
                tableview.setStyle("-fx-selection-bar: red; -fx-selection-bar-non-focused: salmon;");
                tableview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                tableview.getSelectionModel().setCellSelectionEnabled(true);
//                tableview.setStyle("-fx-background-color: steelblue; -fx-text-background-color: red;");
                tableview.setItems(FXCollections.observableArrayList(variacionList));
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Variacion", null, "Lista Vacía");
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Variacion", null, "Hubo un error");
        }
    }

    private void llenarRequisitos() {

        idReq.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getId().toString()));
        desReq.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDescripcion()));
        estReq.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));

        try {
            requisitosList = RequisitoController.getInstance().getAll();
            if (requisitosList != null && !requisitosList.isEmpty()) {
                tableViewReq.setItems(FXCollections.observableArrayList(requisitosList));
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Requisitos", null, "Lista Vacía");
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Requisitos", null, "Hubo un error");
        }
    }

    private void llenarTree() {
        TreeItem<String> root = new TreeItem<>(tra);
        treeVar.setRoot(root);
        TreeItem<String> inicio = new TreeItem<>("Variaciones");
        root.getChildren().add(inicio);
        for (int i = 0; i < variacionList2.size(); i++) {
            TreeItem<String> item = new TreeItem<>(variacionList2.get(i).getDescripcion());
            inicio.getChildren().add(item);
            treeVar.getSelectionModel().select(item);
        }
        treeVar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (mouseEvent.getClickCount() == 2) {
                    TreeItem<String> item = (TreeItem<String>) treeVar.getSelectionModel()
                            .getSelectedItem();
                    System.out.println(item.getValue());
//                    try {
//                        if (item.getValue().equals("Usuarios")) {
//                            cambiarUsuario("Informacion");
//
//                        } else if (item.getValue().equals("Departamentos")) {
//                            cambiarDepartamento("Departamentos");
//
//                        } else if (item.getValue().equals("Diseño de Trámites")) {
//                            cambiarDiseñoTramites("Tramites");
//
//                        } else if (item.getValue().equals("Permisos")) {
//                            cambiarPermisos();
//
//                        } else if (item.getValue().equals("Parametros")) {
//                            cambiarParametros();
//
//                        } else if (item.getValue().equals("Tipos de Trámites")) {
//                            cambiarTramites();
//                        }
//
//                    } catch (IOException ex) {
//                        Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                }

            }
        });
    }

    private void actionVariacionClick() {
        tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && tableview.selectionModelProperty().get().getSelectedItem() != null) {
                    VariacionDTO var = (VariacionDTO) tableview.selectionModelProperty().get().getSelectedItem();
                    AppContext.getInstance().set("VarObject", var);
                    FlowController.getInstance().goViewInWindowModal("AddEditWatchVariacion", ((Stage) btnVar.getScene().getWindow()), false);
                    tableview.selectionModelProperty().get().clearSelection();
                } else {
                    if (mouseEvent.getClickCount() == 1 && tableview.selectionModelProperty().get().getSelectedItem() != null) {

                        VariacionDTO depa = (VariacionDTO) tableview.selectionModelProperty().get().getSelectedItem();
                        if (variacionList2.contains(depa)) {
                            System.out.println("Eliminado");
                            variacionList2.remove(depa);
                        } else {
                            System.out.println("Agregado");
                            variacionList2.add(depa);
                        }
                        System.out.println(variacionList2.size());
                        tableview.selectionModelProperty().get().clearSelection();
                    }
                }
            }
        });
    }

    @FXML
    private void saveVar(ActionEvent event) {
    }

    @FXML
    private void cancelReq(ActionEvent event) {
    }

    @FXML
    private void saveReq(ActionEvent event) {
    }

    @FXML
    private void crearRequisito(ActionEvent event) {
    }

    @FXML
    private void crearVar(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("AddEditWatchVariacion", ((Stage) btnVar.getScene().getWindow()), false);
    }

    @FXML
    private void actionRequisitos(Event event) {
        llenarTree();
    }

}
