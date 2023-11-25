package com.deezghostingfy.controlefxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
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

import com.deezghostingfy.App;
import com.deezghostingfy.pesquisa.Pesquisa;
import com.deezghostingfy.dados.Video;

public class ControladoraTelaPesquisa implements Initializable {
    @FXML
    private TextField pesquisaTextField;

    @FXML
    private Button playlistsButton;

    @FXML
    private Button pesquisarButton;

    @FXML
    private ListView<HBox> resultadoPesquisaListView;

    HBox selecionado;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        resultadoPesquisaListView.getItems().clear();

        resultadoPesquisaListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {

            @Override public void changed(ObservableValue<? extends HBox> arg0, HBox arg1, HBox arg2) {
                selecionado = resultadoPesquisaListView.getSelectionModel().getSelectedItem();
                Label tituloSelecionado = (Label)(selecionado.getChildren().get(1));
                ControladoraTelaVideo.EncontraVideoAtual(tituloSelecionado.getText());

                try {
                    App.redimensiona(330, 396);
                    App.setRoot("telaVideoFXML");
                } catch (IOException e) {
                    System.out.println("erro pesquisa");
                    System.out.println(e.getMessage());
                    System.out.println(e.getStackTrace());
                }
            }   

        });
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

    @FXML
    void acessarPlaylists(ActionEvent event) {
        try {
            App.setRoot("telaPlaylistsFXML");
        } catch (IOException ex) {
            System.out.println("erro acessando tela de playlists");
            System.out.println(ex.getMessage());
        }
    }

    private static HBox[] geraListaHBox(ArrayList<Video> videos) {
        HBox[] listaHBox = new HBox[videos.size()];


        for (int i = 0; i < videos.size(); i++) {
            HBox caixaVideo = new HBox(5);
            caixaVideo.setPadding(new Insets(18, 5, 18, 5));
            caixaVideo.setPrefHeight(135);
            caixaVideo.setPrefWidth(411);

            ImageView thumbnail = new ImageView(videos.get(i).getThumb());
            thumbnail.setFitHeight(99);
            thumbnail.setFitWidth(176);

            Label titulo = new Label(videos.get(i).getTitulo());
            titulo.setMinHeight(45);
            titulo.setMinWidth(200);
            titulo.setWrapText(true);

            caixaVideo.getChildren().addAll(thumbnail, titulo);
            HBox.setMargin(titulo, new Insets(27, 9, 27, 0));
            listaHBox[i] = caixaVideo;
        }

        return listaHBox;
    }

}

