package org.una.laboratorio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import org.una.laboratorio.controller.DepartamentoController;

import org.una.laboratorio.dto.PermisoOtorgadoDTO;

import org.una.laboratorio.controller.PermisoOtorgadoController;
import org.una.laboratorio.dto.DepartamentoDTO;
import org.una.laboratorio.dto.PermisoOtorgadoDTO;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.service.DepartementoService;
import org.una.laboratorio.service.PermisoOtorgadoService;
import org.una.laboratorio.utils.FlowController;

public class PrincipalController extends Controller implements Initializable {

    @FXML
    private VBox vboxPrincipal;
    @FXML
    private Label lblNombreUSU;
    @FXML
    private Label lblHora;
    @FXML
    private TreeView<String> treeAcciones;
    
    public List<PermisoOtorgadoDTO> ListPerOtor = new ArrayList<>();
    boolean TreeUsu = true;
    boolean TreeDep = true;
    boolean TreeTra = true;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lblNombreUSU.setText(((UsuarioDTO) AppContext.getInstance().get("usuarioLog")).getNombreCompleto());
        if (AppContext.getInstance().get("permisosOTG") != null) {
            ListPerOtor = (List<PermisoOtorgadoDTO>) AppContext.getInstance().get("permisosOTG");
            
            TreeItem<String> root = new TreeItem<>(lblNombreUSU.getText());
            treeAcciones.setRoot(root);

            TreeItem<String> itemAdministracion = new TreeItem<>("Administracion");
            root.getChildren().add(itemAdministracion);
            TreeItem<String> itemInformacion = new TreeItem<>("Informacion");
            root.getChildren().add(itemInformacion);
//USU5
//USU6
//USU7
//DEP1
//DEP2
//DEP3
//DEP4
//TRA1
//TRA2
//TRA3
//TRA4
//TRA5
//TRA6
//TRD1
//TRU1

            for (int i = 0; i < ListPerOtor.size(); i++) {
                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("USU") && TreeUsu) {
                    TreeItem<String> item = new TreeItem<>("Usuarios");
                    itemInformacion.getChildren().add(item);
                    TreeUsu = false;
                } else if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("DEP") && TreeDep) {
                    TreeItem<String> item = new TreeItem<>("Departamentos");
                    itemInformacion.getChildren().add(item);
                    treeAcciones.getSelectionModel().select(item);
                    TreeDep = false;
                } else if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("TRA") && TreeTra) {
                    TreeItem<String> item = new TreeItem<>("Tramites");
                    itemInformacion.getChildren().add(item);
                    treeAcciones.getSelectionModel().select(item);
                    TreeTra = false;
                }

            }
            for (int i = 0; i < ListPerOtor.size(); i++) {
                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("USU") && TreeUsu) {
                    TreeItem<String> item = new TreeItem<>("Usuarios");
                    itemAdministracion.getChildren().add(item);
                    TreeUsu = false;
                } else if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("DEP") && TreeDep) {
                    TreeItem<String> item = new TreeItem<>("Departamentos");
                    itemAdministracion.getChildren().add(item);
                    treeAcciones.getSelectionModel().select(item);
                    TreeDep = false;
                } else if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("TRA") && TreeTra) {
                    TreeItem<String> item = new TreeItem<>("Tramites");
                    itemAdministracion.getChildren().add(item);
                    treeAcciones.getSelectionModel().select(item);
                    TreeTra = false;
                }

            }

        }

        treeAcciones.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (mouseEvent.getClickCount() == 2) {
                    TreeItem<String> item = (TreeItem<String>) treeAcciones.getSelectionModel()
                            .getSelectedItem();
                    try {
                        if (item.getValue().equals("Usuarios")) {
                            AppContext.getInstance().set("objetoTabla", "Usuarios");
                            cambiarPantalla("Usuarios");

                        } else if (item.getValue().equals("Departamentos")) {
                            AppContext.getInstance().set("objetoTabla", "Departamentos");
                            cambiarPantalla("Departamentos");

                        } else if (item.getValue().equals("Tremites")) {
                            AppContext.getInstance().set("objetoTabla", "Tremites");
                            cambiarPantalla("Tramites");

                        }
                    } catch (IOException ex) {
                        Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void actionMantenimiento(ActionEvent event) throws InterruptedException, ExecutionException, IOException   {
        vboxPrincipal.getChildren().clear();
        Parent root = FXMLLoader.load(App.class.getResource("Mantenimiento.fxml"));
        vboxPrincipal.getChildren().add(root);
//       System.out.println(((List<DepartamentoDTO>)DepartamentoController.getInstance().getEstado("1")));
    }
    
      void cambiarPantalla(String pantalla) throws IOException {
        vboxPrincipal.getChildren().clear();
        Parent root = FXMLLoader.load(App.class
                .getResource("Mantenimiento.fxml"));
        vboxPrincipal.getChildren()
                .add(root);
    }

}
