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
public class MantenimientoController extends Controller implements Initializable {
    @FXML
    private HBox txtTitulo;
    @FXML
    private TextField texFieldFiltro;
    @FXML
    private Button btnFilter;
    @FXML
    private Button btnFilterCancel;
    private TableView<UsuarioDTO> tableviewUsuario;
    private TableView<DepartamentoDTO> tableviewDepartamento;
    private TableView<UsuarioDTO> tableviewVariaciones;
    private TableView<TramiteTipoDTO> tableviewTramiteTipo;
    private TableView<UsuarioDTO> tableviewRegistro;
    private TableView<UsuarioDTO> tableviewParametro;
    public List<UsuarioDTO> usuarioList;
    public List<DepartamentoDTO> departamentoList;
    public List<TramiteTipoDTO> tramiteTipoList;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private HBox HboxTable ;
    /**
     * Initializes the controller class.
     */
    String objeto;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableviewTramiteTipo=new TableView<>();
        tableviewDepartamento=new TableView<>();
        tableviewUsuario = new TableView<>();
         objeto=(String) AppContext.getInstance().get("objetoTabla");
         if(objeto.equals("Usuarios"))
         {
             actionusuarioClick();
             llenarUsuario();
             
         }
         else if(objeto.equals("Departamentos"))
         {
             actionDepartamentoClick();
             llenarDepartamento();
         }
         else if(objeto.equals("Tremites"))
         {
             actionTipoTramiteClick();
             llenarTramiteTipo();
         }
         
         
    }    

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void actionCancelFilter(ActionEvent event) {
    }

    @FXML
    private void actionFilter(ActionEvent event) {
        //if (txtClave.isDisable() && txtUsuario.isDisable()) {
        if(objeto.equals("Usuarios"))
        {
            if (texFieldFiltro != null) {
                tableviewUsuario.setItems(FXCollections.observableArrayList(usuarioList.stream().filter(x -> x.getNombreCompleto().toUpperCase().startsWith(texFieldFiltro.getText().toUpperCase())).collect(Collectors.toList())));
            } else {
                tableviewUsuario.setItems(FXCollections.observableArrayList(usuarioList));
            }
        }
        else if(objeto.equals("Departamentos"))
        {
            if (texFieldFiltro != null) {
                tableviewDepartamento.setItems(FXCollections.observableArrayList(departamentoList.stream().filter(x -> x.getNombre().toUpperCase().startsWith(texFieldFiltro.getText().toUpperCase())).collect(Collectors.toList())));
            } else {
                tableviewDepartamento.setItems(FXCollections.observableArrayList(departamentoList));
            }
        }
         else if(objeto.equals("Tremites"))
        {
            if (texFieldFiltro != null) {
                tableviewTramiteTipo.setItems(FXCollections.observableArrayList(tramiteTipoList.stream().filter(x -> x.getDescripcion().toUpperCase().startsWith(texFieldFiltro.getText().toUpperCase())).collect(Collectors.toList())));
            } else {
                tableviewTramiteTipo.setItems(FXCollections.observableArrayList(tramiteTipoList));
            }
        }
       //}
    }

    @FXML
    private void actionCancel(ActionEvent event) {
    }

    @FXML
    private void actionSave(ActionEvent event) {
    }
    private void actionTipoTramiteClick()
    {
        tableviewTramiteTipo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                 if (mouseEvent.getClickCount() == 2&&tableviewTramiteTipo.selectionModelProperty().get().getSelectedItem() != null) {
                     TramiteTipoDTO depa=(TramiteTipoDTO) tableviewTramiteTipo.selectionModelProperty().get().getSelectedItem();
                     AppContext.getInstance().set("selec", depa);
                     System.out.println(".handle()");
                     tableviewTramiteTipo.selectionModelProperty().get().clearSelection();
                 }
              
            }
        });
    }
    private void actionDepartamentoClick()
    {
        tableviewDepartamento.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                 if (mouseEvent.getClickCount() == 2&&tableviewDepartamento.selectionModelProperty().get().getSelectedItem() != null) {
                     DepartamentoDTO depa=(DepartamentoDTO) tableviewDepartamento.selectionModelProperty().get().getSelectedItem();
                     AppContext.getInstance().set("selec", depa);
                     System.out.println(".handle()");
                     tableviewDepartamento.selectionModelProperty().get().clearSelection();
                 }
              
            }
        });
    }
    private void actionusuarioClick() {
        tableviewUsuario.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                 if (mouseEvent.getClickCount() == 2&&tableviewUsuario.selectionModelProperty().get().getSelectedItem() != null) {
                     UsuarioDTO usuario=(UsuarioDTO) tableviewUsuario.selectionModelProperty().get().getSelectedItem();
                     AppContext.getInstance().set("selec", usuario);
                     System.out.println(".handle()");
                     tableviewUsuario.selectionModelProperty().get().clearSelection();
                 }
              
            }
        });
    }
    
    private void llenarUsuario()
    {
        TableColumn<UsuarioDTO,String> colNombre = new TableColumn("Nombre");
         colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNombreCompleto()));
          TableColumn<UsuarioDTO,String> colCedula = new TableColumn("Cedula");
          colCedula.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getCedula()));
          TableColumn <UsuarioDTO,String>colFechaRe = new TableColumn("Fecha Registro");
          colFechaRe.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
          TableColumn <UsuarioDTO,String>colFechaMo = new TableColumn("Fecha Modificacion");
          colFechaMo.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaModificacion()));
          tableviewUsuario.setPrefSize(HboxTable.getPrefWidth(), HboxTable.getPrefHeight());
          tableviewUsuario.getColumns().addAll(colNombre ,colCedula ,colFechaRe,colFechaMo);
        try {
            usuarioList=Usuariocontroller.getInstance().getAll();
            if (usuarioList != null && !usuarioList.isEmpty()) {
                tableviewUsuario.setItems(FXCollections.observableArrayList(usuarioList));
                HboxTable.getChildren().clear();
                HboxTable.getChildren().add(tableviewUsuario);
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
        }
    }
    
    private void llenarDepartamento()
    {
        TableColumn<DepartamentoDTO,String> colNombre = new TableColumn("Nombre");
         colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNombre()));
          TableColumn<DepartamentoDTO,String> colCedula = new TableColumn("Estado");
          colCedula.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
          TableColumn <DepartamentoDTO,String>colFechaRe = new TableColumn("Fecha Registro");
          colFechaRe.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
          TableColumn <DepartamentoDTO,String>colFechaMo = new TableColumn("Fecha Modificacion");
          colFechaMo.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaModificacion()));
          tableviewDepartamento.setPrefSize(HboxTable.getPrefWidth(), HboxTable.getPrefHeight());
          tableviewDepartamento.getColumns().addAll(colNombre ,colCedula ,colFechaRe,colFechaMo);
          
        try {
            departamentoList=DepartamentoController.getInstance().getAll();
            if (departamentoList != null && !departamentoList.isEmpty()) {
                tableviewDepartamento.setItems(FXCollections.observableArrayList(departamentoList));
                HboxTable.getChildren().clear();
                HboxTable.getChildren().add(tableviewDepartamento);
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
        }
    }
    
    private void llenarTramiteTipo()
    {
        TableColumn<TramiteTipoDTO,String> colNombre = new TableColumn("Descripcion");
         colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDescripcion()));
          TableColumn<TramiteTipoDTO,String> colCedula = new TableColumn("Estado");
          colCedula.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().isEstado()));
          TableColumn <TramiteTipoDTO,String>colFechaRe = new TableColumn("Fecha Registro");
          colFechaRe.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
          TableColumn <TramiteTipoDTO,String>colFechaMo = new TableColumn("Fecha Modificacion");
          colFechaMo.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaModificacion()));
          tableviewTramiteTipo.setPrefSize(HboxTable.getPrefWidth(), HboxTable.getPrefHeight());
          tableviewTramiteTipo.getColumns().addAll(colNombre ,colCedula ,colFechaRe,colFechaMo);
          
        try {
            tramiteTipoList=TramiteTipoController.getInstance().getAll();
            if (tramiteTipoList != null && !tramiteTipoList.isEmpty()) {
                tableviewTramiteTipo.setItems(FXCollections.observableArrayList(tramiteTipoList));
                HboxTable.getChildren().clear();
                HboxTable.getChildren().add(tableviewTramiteTipo);
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
        }
    }
}
