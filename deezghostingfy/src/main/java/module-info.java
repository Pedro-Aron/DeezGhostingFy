module com.deezghostingfy {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.deezghostingfy to javafx.fxml;
    exports com.deezghostingfy;
}
