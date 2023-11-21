package com.deezghostingfy.controlefxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import com.deezghostingfy.pesquisa.Pesquisa;

public class ControladoraTelaPesquisa {

    @FXML
    private TextField pesquisaTextField;

    @FXML
    private Button pesquisarButton;

    @FXML
    private ListView<?> resultadoPesquisaListView;

    @FXML
    void pesquisar(ActionEvent event) {
        String pesquisa = pesquisaTextField.getText();
        boolean sucessaoPesquisa = false;

        try {
            sucessaoPesquisa = Pesquisa.RealizaPesquisa(pesquisa);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Excecao na pesquisa");
        }

        if (sucessaoPesquisa = false)
            System.out.println("problema na pesquisa");
    }

}

