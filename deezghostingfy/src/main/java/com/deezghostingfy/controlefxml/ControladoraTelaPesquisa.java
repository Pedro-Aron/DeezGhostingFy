package com.deezghostingfy.controlefxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControladoraTelaPesquisa implements Initializable {

    @FXML
    private TextField pesquisaTextField;

    @FXML
    private Button pesquisarButton;

    @FXML
    private ListView<?> resultadoPesquisaListView;

    @FXML
    void pesquisar(ActionEvent event) {

    }

}

