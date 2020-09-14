/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.laboratorio;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.una.laboratorio.dto.PermisoDTO;
import org.una.laboratorio.dto.PermisoOtorgadoDTO;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.AppContext;

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
    @FXML
    private Label labelCedula;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<PermisoDTO, String> colunmCuadrito;
    @FXML
    private TableColumn<PermisoDTO, String> colunmId;
    @FXML
    private TableColumn<PermisoDTO, String> colunmCodigo;
    @FXML
    private TableColumn colunmNombre;
    List<PermisoOtorgadoDTO> permisosOtorgados;
    UsuarioDTO usuarioDTO;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableView = new TableView<>();
        permisosOtorgados = (List<PermisoOtorgadoDTO>) AppContext.getInstance().get("permisosOTG");
        usuarioDTO=(UsuarioDTO) AppContext.getInstance().get("usuarioLog");
        LLenarTableView();
    }    
    private void LLenarTableView()
    {
        colunmId = new TableColumn("ID");
        colunmId.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getId().toString()));
        colunmCodigo = new TableColumn("Codigo");
        colunmCodigo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getCodigo()));
         colunmNombre = new TableColumn("Usuario");
        colunmNombre.setCellValueFactory((param) -> new SimpleStringProperty(usuarioDTO.getNombreCompleto()));
        
        tableView.getColumns().addAll(colunmId, colunmCodigo, colunmNombre);
//        try {
//            usuarioList = Usuariocontroller.getInstance().getAll();
//            if (usuarioList != null && !usuarioList.isEmpty()) {
//                tableView.setItems(FXCollections.observableArrayList(usuarioList));
//                Hbox.getChildren().clear();
//                Hbox.getChildren().add(tableviewUsuario);
//            } else {
//                new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
//            }
//        } catch (Exception e) {
//            new Mensaje().showModal(Alert.AlertType.ERROR, "Error de Usuario", null, "estoy verificando en mantenimineto");
//        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
