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
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.HBox;
import org.una.laboratorio.controller.PermisoController;
import org.una.laboratorio.controller.PermisoOtorgadoController;
import org.una.laboratorio.dto.PermisoDTO;
import org.una.laboratorio.dto.PermisoOtorgadoDTO;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class AutorizacionesController extends Controller implements Initializable {

    @FXML
    private TextField texFielFiltrar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private Label labelUsuario;
    private TableView<PermisoDTO> tableView;
    List<PermisoOtorgadoDTO> permisosOtorgados;
    List<PermisoDTO> permisos;
    UsuarioDTO listaUsuario;
    @FXML
    private HBox Hbox;
    @FXML
    private Label labelCedula;
    List<UsuarioDTO> U;
    ObservableList<PermisoDTO> items;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableView = new TableView<>();
        listaUsuario= (UsuarioDTO) AppContext.getInstance().get("selec");
        
        try {
            permisosOtorgados = (List<PermisoOtorgadoDTO>) PermisoOtorgadoController.getInstance().getUsuario(listaUsuario.getId().toString());
           
            //usuarioDTO=(UsuarioDTO) AppContext.getInstance().get("usuarioLog");
        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(AutorizacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        LLenarTableView();
    }    
    private void LLenarTableView()
    {
       CheckBox check = new CheckBox();
       labelCedula.setText(listaUsuario.getCedula());
       labelUsuario.setText(listaUsuario.getNombreCompleto());
       
       TableColumn<PermisoDTO, CheckBox> colunmcuadro = new TableColumn("Select");
       colunmcuadro.setCellValueFactory((param) -> new SimpleObjectProperty(new CheckBox()));
       
       TableColumn<PermisoDTO, String> colunmId = new TableColumn("ID");
        colunmId.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getId().toString()));
        TableColumn<PermisoDTO, String> colunmCodigo = new TableColumn("Codigo");
        colunmCodigo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getCodigo()));
        TableColumn<PermisoDTO, String> colunmNombre = new TableColumn("Nombre");
        colunmNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDescripcion()));
        tableView.setMaxSize(Hbox.getPrefWidth(), Hbox.getPrefHeight());
        tableView.setMinSize(Hbox.getPrefWidth(), Hbox.getPrefHeight());
        tableView.getColumns().addAll(colunmcuadro,colunmId, colunmCodigo ,colunmNombre);
        try {
            
            permisos = PermisoController.getInstance().getAll();
            if (permisos != null && !permisos.isEmpty()) {
               
               
                
                 items =tableView.getItems();
                for (PermisoDTO per: permisos) {
                    for (PermisoOtorgadoDTO permisosOtorgado1 : permisosOtorgados) {
                        if(per.getId().toString().equals(permisosOtorgado1.getPermisoId().toString()))
                        {
                            CheckBox c=colunmcuadro.getCellData(1);
                            c.setSelected(true);
                        }
                        
                    }
                }
                CheckBox c=colunmcuadro.getCellData(1);
//                c.setSelected(true);
                System.out.println(c.isSelected());
                tableView.setItems(FXCollections.observableArrayList(permisos));
                Hbox.getChildren().clear();
                Hbox.getChildren().add(tableView);
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Permisos", null, "El usuario no tiene permisos ");
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Permisos", null, "estoy verificando en mantenimineto");
        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void actionBuscar(ActionEvent event) {
//        if (texFielFiltrar != null) {
//            tableView.setItems(FXCollections.observableArrayList(listaUsuario.stream().filter(x -> x.getNombreCompleto().toUpperCase().startsWith(texFieldFiltro.getText().toUpperCase())).collect(Collectors.toList())));
//        } else {
//            tableView.setItems(FXCollections.observableArrayList(permisos));
//        }
    }
}
