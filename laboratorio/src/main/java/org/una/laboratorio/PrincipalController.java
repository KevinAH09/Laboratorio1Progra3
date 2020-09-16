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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import org.una.laboratorio.controller.DepartamentoController;

import org.una.laboratorio.dto.PermisoOtorgadoDTO;
import org.una.laboratorio.dto.UsuarioDTO;
import org.una.laboratorio.utils.AppContext;
import org.una.laboratorio.utils.FlowController;

public class PrincipalController extends Controller implements Initializable {

    @FXML
    private VBox vboxPrincipal;
    @FXML
    private Label lblHora;
    @FXML
    private TreeView<String> treeAcciones;

    public List<PermisoOtorgadoDTO> ListPerOtor = new ArrayList<>();
    boolean TreeUsu = true;
    boolean TreeDep = true;
    boolean TreeTra = true;

    @FXML
    private AnchorPane ancgor;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (AppContext.getInstance().get("permisosOTG") != null) {
            ListPerOtor = (List<PermisoOtorgadoDTO>) AppContext.getInstance().get("permisosOTG");
            Node imgroot = new ImageView(new Image("org/una/laboratorio/icons/menu.png"));
            Node imgInformacion = new ImageView(new Image("org/una/laboratorio/icons/informacion.png"));
            Node imgAdmin = new ImageView(new Image("org/una/laboratorio/icons/lengueta.png"));

            TreeItem<String> root = new TreeItem<>("Funciones de " + (((UsuarioDTO) AppContext.getInstance().get("usuarioLog")).getNombreCompleto()));
            root.setGraphic(imgroot);
            treeAcciones.setRoot(root);

            TreeItem<String> itemInformacion = new TreeItem<>("Informacion");
            itemInformacion.setGraphic(imgInformacion);
            root.getChildren().add(itemInformacion);
            TreeItem<String> itemAdministracion = new TreeItem<>("Administracion");
            itemAdministracion.setGraphic(imgAdmin);
            root.getChildren().add(itemAdministracion);

            for (int i = 0; i < ListPerOtor.size(); i++) {
                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("TRA") && TreeUsu) {
                    TreeItem<String> item = new TreeItem<>("Tipos de Trámites");
                    itemInformacion.getChildren().add(item);
                    TreeUsu = false;
                }
                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("DEP") && TreeDep) {
                    TreeItem<String> item = new TreeItem<>("Departamentos");
                    itemInformacion.getChildren().add(item);
                    treeAcciones.getSelectionModel().select(item);
                    TreeDep = false;
                }
                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("TRD") && TreeTra) {
                    TreeItem<String> item = new TreeItem<>("Diseño de Trámites");
                    itemInformacion.getChildren().add(item);
                    treeAcciones.getSelectionModel().select(item);
                    TreeTra = false;
                }

            }

            TreeUsu = true;
            TreeDep = true;
            TreeTra = true;

            for (int i = 0; i < ListPerOtor.size(); i++) {
                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("PER") && TreeUsu) {
                    TreeItem<String> item = new TreeItem<>("Permisos");
                    itemAdministracion.getChildren().add(item);
                    TreeUsu = false;
                }
                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("USU") && TreeDep) {
                    TreeItem<String> item = new TreeItem<>("Usuarios");
                    itemAdministracion.getChildren().add(item);
                    treeAcciones.getSelectionModel().select(item);
                    TreeDep = false;
                }
                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("PAR") && TreeTra) {
                    TreeItem<String> item = new TreeItem<>("Parametros");
                    itemAdministracion.getChildren().add(item);
                    treeAcciones.getSelectionModel().select(item);
                    TreeTra = false;
                }

            }

        } else {
            TreeItem<String> root = new TreeItem<>((((UsuarioDTO) AppContext.getInstance().get("usuarioLog")).getNombreCompleto()) + " no posee permisos");
            treeAcciones.setRoot(root);
        }

        treeAcciones.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (mouseEvent.getClickCount() == 2) {
                    TreeItem<String> item = (TreeItem<String>) treeAcciones.getSelectionModel()
                            .getSelectedItem();
                    try {
                        if (item.getValue().equals("Usuarios")) {
                            cambiarUsuario("Informacion");

                        } else if (item.getValue().equals("Departamentos")) {
                            cambiarDepartamento("Departamentos");

                        } else if (item.getValue().equals("Diseño de Trámites")) {
                            cambiarDiseñoTramites("Tramites");

                        } else if (item.getValue().equals("Permisos")) {
                            cambiarPermisos();

                        } else if (item.getValue().equals("Parametros")) {
                            cambiarParametros();

                        } else if (item.getValue().equals("Tipos de Trámites")) {
                            cambiarTramites();
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

    private void actionMantenimiento(ActionEvent event) throws InterruptedException, ExecutionException, IOException {
        vboxPrincipal.getChildren().clear();
        Parent root = FXMLLoader.load(App.class.getResource("Mantenimiento.fxml"));
        vboxPrincipal.getChildren().add(root);
//       System.out.println(((List<DepartamentoDTO>)DepartamentoController.getInstance().getEstado("1")));
    }

    void cambiarDepartamento(String pantalla) throws IOException {
        vboxPrincipal.getChildren().clear();
        Parent root = FXMLLoader.load(App.class
                .getResource("DepartamentoView.fxml"));
        vboxPrincipal.getChildren()
                .add(root);
    }

    void cambiarUsuario(String pantalla) throws IOException {
        vboxPrincipal.getChildren().clear();
        Parent root = FXMLLoader.load(App.class
                .getResource("InformacionUsuarios.fxml"));
        vboxPrincipal.getChildren()
                .add(root);
    }

    void cambiarDiseñoTramites(String pantalla) throws IOException {
        vboxPrincipal.getChildren().clear();
        Parent root = FXMLLoader.load(App.class
                .getResource("DisenoTramites.fxml"));
        vboxPrincipal.getChildren()
                .add(root);
    }

    void cambiarPermisos() throws IOException {
        vboxPrincipal.getChildren().clear();
        Parent root = FXMLLoader.load(App.class
                .getResource("PermisosView.fxml"));
        vboxPrincipal.getChildren()
                .add(root);
    }

    void cambiarTramites() throws IOException {
        vboxPrincipal.getChildren().clear();
        Parent root = FXMLLoader.load(App.class
                .getResource("TipoTramitesView.fxml"));
        vboxPrincipal.getChildren()
                .add(root);
    }

    void cambiarParametros() throws IOException {
        vboxPrincipal.getChildren().clear();
        Parent root = FXMLLoader.load(App.class
                .getResource("ParametrosView.fxml"));
        vboxPrincipal.getChildren()
                .add(root);
    }

    @FXML
    private void actionTamano(MouseEvent event) {
        ancgor.setMaxWidth((double) AppContext.getInstance().get("whit"));
        ancgor.setMinWidth((double) AppContext.getInstance().get("whit"));
        ancgor.setMaxHeight((double) AppContext.getInstance().get("heig"));
        ancgor.setMinHeight((double) AppContext.getInstance().get("heig"));
    }

    @FXML
    private void actionCerrarSesion(ActionEvent event) {
        FlowController.getInstance().goMain();
        FlowController.eliminar("Login");
        FlowController.getInstance().goView("Login");
    }

}
