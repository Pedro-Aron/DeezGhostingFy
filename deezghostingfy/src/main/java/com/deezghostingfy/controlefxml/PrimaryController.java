package com.deezghostingfy.controlefxml;

import java.io.IOException;

import com.deezghostingfy.App;

import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
