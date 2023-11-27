module com.deezghostingfy {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires mongo.java.driver;

    opens com.deezghostingfy.controlefxml to javafx.fxml;
    exports com.deezghostingfy;

    opens com.deezghostingfy.dados to mongo.java.driver;
    exports com.deezghostingfy.dados;
}
