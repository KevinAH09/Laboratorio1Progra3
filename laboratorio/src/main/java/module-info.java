module org.una.laboratorio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens org.una.laboratorio to javafx.fxml;
    exports org.una.laboratorio;
    requires javafx.graphicsEmpty;
    requires lombok;
}
