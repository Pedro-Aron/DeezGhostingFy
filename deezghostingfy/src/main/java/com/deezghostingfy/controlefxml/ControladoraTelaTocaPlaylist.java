package com.deezghostingfy.controlefxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.scene.layout.HBox;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;

import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;

import com.deezghostingfy.dados.Playlist;
import com.deezghostingfy.dados.Sessao;
import com.deezghostingfy.App;

public class ControladoraTelaTocaPlaylist implements Initializable {
    private static Playlist playlistAtual;
    private static int musicaAtual;

    @FXML
    private Button anteriorButton;

    @FXML
    private Button curtirButton;

    @FXML
    private Button iniciarButton;

    @FXML
    private WebView musicaWebView;

    @FXML
    private ListView<HBox> playlistTocandoListView;

    @FXML
    private Button proximaButton;

    @FXML
    private Label tituloLabel;

    @FXML
    private Button voltarButton;

    private HBox selecionado;

    @Override public void initialize(URL arg0, ResourceBundle arg1) {
        HBox[] listaHBox = ControladoraTelaRemocao.geraListaHbox(playlistAtual);
        playlistTocandoListView.getItems().addAll(listaHBox);

        playlistTocandoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HBox>() {

            @Override public void changed(ObservableValue<? extends HBox> arg0, HBox arg1, HBox arg2) {
                selecionado = playlistTocandoListView.getSelectionModel().getSelectedItem();
                String titulo = ((Label) selecionado.getChildren().get(1)).getText();

                tituloLabel.setText(titulo);
                musicaAtual = playlistAtual.getIndex(titulo);
                WebEngine engine = musicaWebView.getEngine();
                engine.load(playlistAtual.get(musicaAtual).getLink());
            }
        });
    }

    @FXML
    void curtir(ActionEvent event) {
        Sessao.adicionarMusicaCurtida(playlistAtual.get(musicaAtual));
    }

    @FXML
    void iniciar(ActionEvent event) {
        WebEngine engine = musicaWebView.getEngine();
        engine.load(playlistAtual.get(musicaAtual).getLink());
        tituloLabel.setText(playlistAtual.get(musicaAtual).getTitulo());
    }

    @FXML
    void tocarAnterior(ActionEvent event) {
        musicaAtual--;
        WebEngine engine = musicaWebView.getEngine();
        engine.load(playlistAtual.get(musicaAtual).getLink());      
        tituloLabel.setText(playlistAtual.get(musicaAtual).getTitulo());
    }

    @FXML
    void tocarProxima(ActionEvent event) {
        musicaAtual++;
        WebEngine engine = musicaWebView.getEngine();
        engine.load(playlistAtual.get(musicaAtual).getLink());
        tituloLabel.setText(playlistAtual.get(musicaAtual).getTitulo());    
    }

    @FXML
    void voltar(ActionEvent event) {
        WebEngine engine = musicaWebView.getEngine();
        engine.load(null);

        App.redimensiona(750, 450);

        try {
            App.setRoot("telaPlaylistsFXML");
        } catch (IOException e) {
            System.out.println("Excecao voltando da tela de tocar playlists");
            System.out.println(e.getMessage());
        }
    }

    public static void EncontraPlaylistAtual(String playlist) {
        for (var pl: Sessao.acessarPlaylists()) 
            if (pl.getNome().toLowerCase().contains(playlist.toLowerCase())) {
                playlistAtual = pl;
                break;
            }

        musicaAtual = 0;
    }

}
