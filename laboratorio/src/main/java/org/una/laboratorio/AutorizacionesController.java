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
import java.util.stream.Collectors;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.una.laboratorio.controller.PermisoController;
import org.una.laboratorio.controller.PermisoOtorgadoController;
import org.una.laboratorio.dto.PerimisosCheBox;
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
    private TableView<PerimisosCheBox> tableView;
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
        listaUsuario = (UsuarioDTO) AppContext.getInstance().get("selec");

        try {
            permisosOtorgados = (List<PermisoOtorgadoDTO>) PermisoOtorgadoController.getInstance().getUsuario(listaUsuario.getId().toString());

            //usuarioDTO=(UsuarioDTO) AppContext.getInstance().get("usuarioLog");
        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(AutorizacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        actionDepartamentoClick();
        LLenarTableView();
    }

    private void actionDepartamentoClick() {
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (tableView.selectionModelProperty().get().getSelectedItem() != null) {
                    PerimisosCheBox tra = (PerimisosCheBox) tableView.selectionModelProperty().get().getSelectedItem();
                    System.out.println(tra.getBox().isSelected());
                }

            }
        });
    }

    private void LLenarTableView() {

        try {
            permisos = PermisoController.getInstance().getAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(AutorizacionesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(AutorizacionesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AutorizacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<PerimisosCheBox> cheBoxs = new ArrayList<>();
        for (PermisoDTO permiso : permisos) {
            for (PermisoOtorgadoDTO permisosOtorgado : permisosOtorgados) {
                if (permiso.getId() == permisosOtorgado.getPermisoId().getId()) {
                    CheckBox checkBox = new CheckBox();
                    checkBox.setSelected(true);
                    PerimisosCheBox cheBox = new PerimisosCheBox(checkBox, permiso);
                    cheBoxs.add(cheBox);
                }
            }

        }

        labelCedula.setText(listaUsuario.getCedula());
        labelUsuario.setText(listaUsuario.getNombreCompleto());

        TableColumn<PerimisosCheBox, CheckBox> colunmcuadro = new TableColumn("Select");
        colunmcuadro.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getBox()));

        TableColumn<PerimisosCheBox, String> colunmId = new TableColumn("ID");
        colunmId.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getdTO().getId().toString()));

        TableColumn<PerimisosCheBox, String> colunmCodigo = new TableColumn("Codigo");
        colunmCodigo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getdTO().getCodigo()));
        TableColumn<PerimisosCheBox, String> colunmNombre = new TableColumn("Nombre");
        colunmNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getdTO().getDescripcion()));
        tableView.getColumns().addAll(colunmcuadro, colunmId, colunmCodigo, colunmNombre);
        try {
            tableView.setItems(FXCollections.observableArrayList(cheBoxs));
            
            Hbox.getChildren().clear();
            Hbox.getChildren().add(tableView);
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
        permisos.clear();
        List<PerimisosCheBox> cheBoxs = new ArrayList<>();
        cheBoxs=tableView.getItems();
        for (PerimisosCheBox permisosOtorga : cheBoxs) {
            if(permisosOtorga.getBox().isSelected()){
                permisos.add(permisosOtorga.getdTO());
                
            }
            
        }
        System.out.println(permisos);
//        if (texFielFiltrar != null) {
//            tableView.setItems(FXCollections.observableArrayList(listaUsuario.stream().filter(x -> x.getNombreCompleto().toUpperCase().startsWith(texFieldFiltro.getText().toUpperCase())).collect(Collectors.toList())));
//        } else {
//            tableView.setItems(FXCollections.observableArrayList(permisos));
//        }
    }
}
