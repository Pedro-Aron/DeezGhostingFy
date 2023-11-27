package com.deezghostingfy.controlefxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;

import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.ArrayList;
import java.io.IOException;

import com.deezghostingfy.App;
import com.deezghostingfy.dados.Sessao;
import com.deezghostingfy.dados.Conexao.Connection;
import com.deezghostingfy.dados.Playlist;

public class ControladoraTelaPlaylists implements Initializable {

    @FXML
    private Button criarPlaylistButton;

    @FXML
    private TextField novaPlaylistTextField;

    @FXML
    private ListView<HBox> playlistsListView;

    @FXML
    private Button voltarPesquisaButton; 

    @FXML
    private Button deletarButton;

    @FXML
    private Button gerenciarBotao;

    @FXML
    private TextField gerenciarTextField;

    @FXML
    private TextField deletarTextField;

    private HBox selecionado;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (Sessao.acessarPlaylists().size() > 0) {
            HBox[] listaHBox = ControladoraTelaPlaylists.geraListaHbox();
            playlistsListView.getItems().addAll(listaHBox);
        }
        

        playlistsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {

            @Override public void changed(ObservableValue<? extends HBox> arg0, HBox arg1, HBox arg2) {
                selecionado = playlistsListView.getSelectionModel().getSelectedItem();
                Label tituloSelecionado = (Label) selecionado.getChildren().get(1);
                ControladoraTelaTocaPlaylist.EncontraPlaylistAtual(tituloSelecionado.getText());
                App.redimensiona(750, 450);

                try {
                    App.setRoot("telaTocaPlaylistFXML");
                } catch (IOException ex) {
                    System.out.println("erro abrindo tela de tocaar playlist");
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    @FXML
    void deletar(ActionEvent event) {
        String listaDeletada = deletarTextField.getText();
        Sessao.removerPlaylist(listaDeletada);

        HBox[] novaLista = geraListaHbox();
        playlistsListView.getItems().clear();
        playlistsListView.getItems().addAll(novaLista);
    }

    @FXML
    void criarPlaylist(ActionEvent event) {
        Sessao.addPlaylistMongo(new Playlist(novaPlaylistTextField.getText()));
    }

    @FXML
    void voltarPesquisa(ActionEvent event) {
        if (Sessao.acessarPlaylists().size() > 0) {
            HBox[] listaHBox = geraListaHbox();
            playlistsListView.getItems().clear();
            playlistsListView.getItems().addAll(listaHBox);
        }

        try {
            App.setRoot("telaPesquisaFXML");
        } catch (IOException e) {
            System.out.println("erro voltando da tela de playlists");
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void gerenciarPlaylist(ActionEvent event) {
        String playlist = gerenciarTextField.getText();
        ControladoraTelaRemocao.EncontraPlaylistAtual(playlist);

        App.redimensiona(469, 487);

        try {
            App.setRoot("telaRemocaoFXML");
        } catch (IOException ex) {
            System.out.println("erro abrindo tela de remocao");
            System.out.println(ex.getMessage());
        }
    }

    private static HBox[] geraListaHbox() {
        ArrayList<Playlist> playlists = new ArrayList<Playlist>();
        Sessao.defineCapaPlaylists();

        for (var playlist: Sessao.acessarPlaylists()) {
            if (playlist.getCapa() == null)
                continue;

           playlists.add(playlist); 
        }

        Playlist curtidas = Sessao.acessarMusicasCurtidas();
        if (curtidas.size() > 0)
            playlists.add(curtidas);

        HBox[] listaHBox = new HBox[playlists.size()];
        for (int i = 0; i < playlists.size(); i++) {
            HBox caixaPlaylist = new HBox(5);
            caixaPlaylist.setPadding(new Insets(18, 5, 18, 5));
            caixaPlaylist.setPrefHeight(135);
            caixaPlaylist.setPrefWidth(411);

            ImageView thumbnail = new ImageView(playlists.get(i).getCapa());
            thumbnail.setFitHeight(99);
            thumbnail.setFitWidth(176);

            Label titulo = new Label(playlists.get(i).getNome());
            titulo.setMinHeight(45);
            titulo.setMinWidth(200);
            titulo.setWrapText(true);

            caixaPlaylist.getChildren().addAll(thumbnail, titulo);
            HBox.setMargin(titulo, new Insets(27, 9, 27, 0));
            listaHBox[i] = caixaPlaylist;
        }

        return listaHBox;
    }

}
