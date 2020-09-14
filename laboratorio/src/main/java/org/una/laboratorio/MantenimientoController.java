/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.una.laboratorio.controller.Usuariocontroller;
import org.una.laboratorio.dto.UsuarioDTO;
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
    @FXML
    private TableView<UsuarioDTO> tableviewInformacion;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //UsuarioDTO usuario=(UsuarioDTO) AppContext.getInstance().get("objetoTabla");
         TableColumn<UsuarioDTO,String> colNombre = new TableColumn("Nombre");
         colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNombreCompleto()));
          TableColumn<UsuarioDTO,String> colCedula = new TableColumn("Cedula");
          colCedula.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getCedula()));
          TableColumn <UsuarioDTO,String>colFechaRe = new TableColumn("Fecha Registro");
          colFechaRe.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaRegistro()));
          TableColumn <UsuarioDTO,String>colFechaMo = new TableColumn("Fecha Modificacion");
          colFechaMo.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getFechaModificacion()));
          tableviewInformacion.getColumns().addAll(colNombre ,colCedula ,colFechaRe,colFechaMo);
        try {
            List<UsuarioDTO> usuarioList=Usuariocontroller.getInstance().getAll();
            if (usuarioList != null && !usuarioList.isEmpty()) {
                tableviewInformacion.setItems(FXCollections.observableArrayList(usuarioList));
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
            }
        } catch (Exception e) {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
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
    }

    @FXML
    private void actionCancel(ActionEvent event) {
    }

    @FXML
    private void actionSave(ActionEvent event) {
    }
    
}
