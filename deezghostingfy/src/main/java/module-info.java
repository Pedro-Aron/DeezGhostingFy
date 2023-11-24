module com.deezghostingfy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.deezghostingfy.controlefxml to javafx.fxml;
    exports com.deezghostingfy;
}
