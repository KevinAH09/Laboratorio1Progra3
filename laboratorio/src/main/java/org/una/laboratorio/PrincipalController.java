package org.una.laboratorio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import org.una.laboratorio.utils.FlowController;

public class PrincipalController extends Controller implements Initializable{

    @FXML
    private TreeView<?> treeView;
    @FXML
    private VBox vboxPrincipal;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void actionMantenimiento(ActionEvent event) throws IOException {
        vboxPrincipal.getChildren().clear();
        Parent root = FXMLLoader.load(App.class.getResource("Mantenimiento.fxml"));
        vboxPrincipal.getChildren().add(root);
    }

    
}
