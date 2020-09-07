package org.una.laboratorio;

import org.una.laboratorio.util.FlowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage,null);
        stage.initStyle(StageStyle.DECORATED);
        FlowController.getInstance().goMain();
        FlowController.getInstance().goView("login");
    }


    public static void main(String[] args) {
        launch(args);
    }

}