module org.una.laboratorio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.google.gson;
    requires lombok;
    requires java.ws.rs;
    opens org.una.laboratorio to javafx.fxml;
    exports org.una.laboratorio;
    
    
}
