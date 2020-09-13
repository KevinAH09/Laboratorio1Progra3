package org.una.laboratorio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.concurrent.ExecutionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import org.una.laboratorio.dto.PermisoOtorgadoDTO;
import org.una.laboratorio.controller.PermisoOtorgadoController;
import org.una.laboratorio.dto.PermisoOtorgadoDTO;
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
//        TreeItem<String> root = new TreeItem<>("Autores");
//        treeAcciones.setRoot(root);
//
//        autor.stream().forEach((x) -> {
//            TreeItem<String> item = new TreeItem<>(x.getNombre());
//            root.getChildren().add(item);
//            libros.stream().filter(t -> t.getAutor().equals(x.getNombre())).collect(Collectors.toList()).stream().forEach((l) -> {
//                TreeItem<String> item2 = new TreeItem<>(l.getNombre());
//                item.getChildren().add(item2);
//                personajes.stream().filter(y -> y.isIsAutor() == false).filter(y -> y.getLibro().equals(l.getNombre())).collect(Collectors.toList()).stream().forEach((g) -> {
//                    TreeItem<String> item3 = new TreeItem<>(g.getNombre());
//                    item2.getChildren().add(item3);
//                });
//
//            });
//
//        });
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
//       System.out.println(( PermisoOtorgadoController.getInstance().getUsuario("1").toString()));
    }

}
