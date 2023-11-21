module com.deezghostingfy {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.deezghostingfy.controlefxml to javafx.fxml;
    exports com.deezghostingfy;
}
