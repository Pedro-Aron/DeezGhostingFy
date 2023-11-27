package com.deezghostingfy.controlefxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;

import java.util.ResourceBundle;
import java.net.URL;
import java.util.ArrayList;
import java.io.IOException;

import com.deezghostingfy.dados.Playlist;
import com.deezghostingfy.dados.Sessao;
import com.deezghostingfy.dados.Video;
import com.deezghostingfy.App;


public class ControladoraTelaRemocao implements Initializable {
    private static Playlist playlistAtual;

    @FXML
    private Button retornarTelaButton;

    @FXML
    private ListView<HBox> videosPlaylistListView;

    private HBox selecionado;

    @Override public void initialize(URL arg0, ResourceBundle arg1) {
        HBox[] listaHBox = geraListaHbox(playlistAtual);
        videosPlaylistListView.getItems().addAll(listaHBox);

        videosPlaylistListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {

            @Override public void changed(ObservableValue<? extends HBox> arg0, HBox arg1, HBox arg2) {
                selecionado = videosPlaylistListView.getSelectionModel().getSelectedItem();
                String musicaDeletada = ((Label) selecionado.getChildren().get(1)).getText();
                Sessao.removerMusicaPlaylist(playlistAtual.getNome(), musicaDeletada);

                HBox[] novaLista = geraListaHbox(playlistAtual);
                videosPlaylistListView.getItems().clear();
                videosPlaylistListView.getItems().addAll(novaLista);
            }
        });
    }

    @FXML
    void retornarGerenciamento(ActionEvent event) {
        App.redimensiona(750, 450);

        try {
            App.setRoot("telaPlaylistsFXML");
        } catch (IOException ex) {
            System.out.println("erro voltando da remocao");
            System.out.println(ex.getMessage());
        }
    }

    public static void EncontraPlaylistAtual(String playlist) {
        for (var pl: Sessao.acessarPlaylists()) 
            if (pl.getNome().toLowerCase().contains(playlist.toLowerCase())) 
                playlistAtual = pl;
    }

    protected static HBox[] geraListaHbox(Playlist playlistAtual) {
        ArrayList<Video> videos = playlistAtual.acessarLista();
        HBox[] listaHBox = new HBox[videos.size()];


        for (int i = 0; i < videos.size(); i++) {
            HBox caixaVideo = new HBox(5);
            caixaVideo.setPadding(new Insets(18, 5, 18, 5));
            caixaVideo.setPrefHeight(135);
            caixaVideo.setPrefWidth(411);

            ImageView thumbnail = new ImageView(videos.get(i).getLinkThumb());
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
