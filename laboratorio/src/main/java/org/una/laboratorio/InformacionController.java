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
import java.util.stream.Collectors;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.una.laboratorio.controller.Usuariocontroller;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.FlowController;
import org.una.laboratorio.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class InformacionController extends Controller implements Initializable {

    private TableView<UsuarioDTO> tableviewUsuario;
    public List<UsuarioDTO> usuarioList;
    String objeto;
    @FXML
    private HBox Hbox;
    @FXML
    private TextField texFieldFiltro;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableviewUsuario = new TableView<>();
        //objeto = (String) AppContext.getInstance().get("objetoTabla");
        
        actionusuarioClick();
        llenarUsuario();
    }

    private void actionusuarioClick() {
        tableviewUsuario.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && tableviewUsuario.selectionModelProperty().get().getSelectedItem() != null) {
                    UsuarioDTO usuario = (UsuarioDTO) tableviewUsuario.selectionModelProperty().get().getSelectedItem();
                    AppContext.getInstance().set("selec", usuario);
                    tableviewUsuario.selectionModelProperty().get().clearSelection();
                    FlowController.getInstance().goViewInWindowModal("AddEditWatchUsuario", ((Stage) tableviewUsuario.getScene().getWindow()), false);
                  
                }
                else if(mouseEvent.getClickCount() == 1 && tableviewUsuario.selectionModelProperty().get().getSelectedItem() != null)
                {
                    UsuarioDTO usuario = (UsuarioDTO) tableviewUsuario.selectionModelProperty().get().getSelectedItem();
                    AppContext.getInstance().set("selec", usuario);
                    //FlowController.getInstance().goViewInWindowModal("AddEditWatchUsuario", ((Stage) btnBuscar.getScene().getWindow()), false);
                }
            }
        });
    }
    
    private void llenarUsuario() {
        TableColumn<UsuarioDTO, String> colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNombreCompleto()));
        TableColumn<UsuarioDTO, String> colCedula = new TableColumn("Cedula");
        colCedula.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getCedula()));
        TableColumn<UsuarioDTO, String> colFechaRe = new TableColumn("Fecha Registro");
        colFechaRe.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
        TableColumn<UsuarioDTO, String> colFechaMo = new TableColumn("Fecha Modificacion");
        colFechaMo.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaModificacion()));
        tableviewUsuario.setMaxSize(Hbox.getPrefWidth(), Hbox.getPrefHeight());
        tableviewUsuario.setMinSize(Hbox.getPrefWidth(), Hbox.getPrefHeight());
        tableviewUsuario.getColumns().addAll(colNombre, colCedula, colFechaRe, colFechaMo);
        try {
            usuarioList = Usuariocontroller.getInstance().getAll();
            AppContext.getInstance().set("hola", usuarioList);
            if (usuarioList != null && !usuarioList.isEmpty()) {
                tableviewUsuario.setItems(FXCollections.observableArrayList(usuarioList));
                Hbox.getChildren().clear();
                Hbox.getChildren().add(tableviewUsuario);
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
        }
    }

    @FXML
    private void actionFilter(ActionEvent event) {
        if (texFieldFiltro != null) {
            tableviewUsuario.setItems(FXCollections.observableArrayList(usuarioList.stream().filter(x -> x.getNombreCompleto().toUpperCase().startsWith(texFieldFiltro.getText().toUpperCase())).collect(Collectors.toList())));
        } else {
            tableviewUsuario.setItems(FXCollections.observableArrayList(usuarioList));
        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
