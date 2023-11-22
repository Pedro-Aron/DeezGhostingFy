package com.deezghostingfy.controlefxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;
import com.deezghostingfy.pesquisa.Pesquisa;
import com.deezghostingfy.dados.Video;

public class ControladoraTelaPesquisa implements Initializable {
    @FXML
    private TextField pesquisaTextField;

    @FXML
    private Button pesquisarButton;

    @FXML
    private ListView<HBox> resultadoPesquisaListView;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        resultadoPesquisaListView.getItems().clear();
    }

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

        HBox[] listaHBox = geraListaHBox(Pesquisa.ExtraiResultado());
        resultadoPesquisaListView.getItems().clear();
        resultadoPesquisaListView.getItems().addAll(listaHBox);
    }

    private static HBox[] geraListaHBox(ArrayList<Video> videos) {
        HBox[] listaHBox = new HBox[videos.size()];


        for (int i = 0; i < videos.size(); i++) {
            HBox caixaVideo = new HBox(5);
            caixaVideo.setPadding(new Insets(18, 5, 18, 5));

            ImageView thumbnail = new ImageView(videos.get(i).getThumb());
            thumbnail.setFitHeight(99);
            thumbnail.setFitWidth(176);

            caixaVideo.getChildren().addAll(thumbnail);
            listaHBox[i] = caixaVideo;
        }

        return listaHBox;
    }

}

