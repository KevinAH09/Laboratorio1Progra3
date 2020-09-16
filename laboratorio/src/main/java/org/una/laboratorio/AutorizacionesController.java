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
import org.una.laboratorio.controller.Usuariocontroller;
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
    List<PermisoOtorgadoDTO> permisosOtorgadosfiltrar;
    List<PermisoDTO> permisos;
    UsuarioDTO listaUsuario;
    @FXML
    private HBox Hbox;
    @FXML
    private Label labelCedula;
    List<UsuarioDTO> U;
    List<UsuarioDTO> items;
    List<PerimisosCheBox> cheBoxs = new ArrayList<>();

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

        LLenarTableView();
        actionDepartamentoClick();
    }
    List<PermisoOtorgadoDTO> perElimi = new ArrayList<>();

    private void actionDepartamentoClick() {

        permisos.clear();
        for (PerimisosCheBox permisosOtorgado : cheBoxs) {
            permisosOtorgado.getBox().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (permisosOtorgado.getBox().isSelected()) {

                        //List<PerimisosCheBox> cheBoxs = new ArrayList<>();
                        //cheBoxs = tableView.getItems();
//                        for (PerimisosCheBox permisosOtorga : cheBoxs) {
//                            if (permisosOtorga.getBox().isSelected()) {
                        permisos.add(permisosOtorgado.getdTO());
                        AppContext.getInstance().set("paraGuardar", permisos);

//                            }
//
//                        }
                    } else {
                        if (permisos.stream().anyMatch(x -> x.getId() == permisosOtorgado.getdTO().getId())) {
                            permisos.remove(permisosOtorgado.getdTO());
                            AppContext.getInstance().set("paraGuardar", permisos);
                        }
                    }
                }
            });
        }

    }

    private void LLenarTableView() {

        PerimisosCheBox tra = new PerimisosCheBox();
        try {

            permisos = PermisoController.getInstance().getAll();
        } catch (InterruptedException | ExecutionException | IOException ex) {
            Logger.getLogger(AutorizacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (PermisoDTO permiso : permisos) {
            //for (PermisoOtorgadoDTO permisosOtorgado : permisosOtorgados) {
            //if (permiso.getId() == permisosOtorgado.getPermisoId().getId()) {
            CheckBox checkBox = new CheckBox();
            checkBox.setSelected(false);
            PerimisosCheBox cheBox = new PerimisosCheBox(checkBox, permiso);
            cheBoxs.add(cheBox);
            //}
            //}

        }

        for (PerimisosCheBox che : cheBoxs) {
            for (PermisoOtorgadoDTO permisosOtorgado : permisosOtorgados) {
                if (che.getdTO().getId() == permisosOtorgado.getPermisoId().getId()) {
                    che.getBox().setSelected(true);
                }
            }
        }

        labelCedula.setText(listaUsuario.getCedula());
        labelUsuario.setText(listaUsuario.getNombreCompleto());
        tableView.getColumns().clear();
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
    private void actionBuscar(ActionEvent event) throws InterruptedException, ExecutionException {

        if (texFielFiltrar != null) {

            try {
                items = (List<UsuarioDTO>) AppContext.getInstance().get("hola");
                for (UsuarioDTO UsuarioDTO : items) {
                    if (texFielFiltrar.getText().equals(UsuarioDTO.getCedula())) {
                        cheBoxs.clear();
                        permisos.clear();
                        U = (List<UsuarioDTO>) Usuariocontroller.getInstance().getCedula(texFielFiltrar.getText());

                        labelCedula.setText(U.get(0).getCedula());
                        labelUsuario.setText(U.get(0).getNombreCompleto());
                        permisosOtorgados = (List<PermisoOtorgadoDTO>) PermisoOtorgadoController.getInstance().getUsuario(U.get(0).getId().toString());
                        //tableView.getItems().clear();
                        Hbox.getChildren().clear();
                        LLenarTableView();
                        actionDepartamentoClick();
                    } else if (texFielFiltrar.getText().equals(UsuarioDTO.getNombreCompleto())) {
                        cheBoxs.clear();
                        permisos.clear();
                        U = (List<UsuarioDTO>) Usuariocontroller.getInstance().getCedula(UsuarioDTO.getCedula());

                        labelCedula.setText(U.get(0).getCedula());
                        labelUsuario.setText(U.get(0).getNombreCompleto());
                        permisosOtorgados = (List<PermisoOtorgadoDTO>) PermisoOtorgadoController.getInstance().getUsuario(U.get(0).getId().toString());
                        //tableView.getItems().clear();
                        Hbox.getChildren().clear();
                        LLenarTableView();
                        actionDepartamentoClick();
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(AutorizacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
