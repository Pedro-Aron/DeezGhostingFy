package com.deezghostingfy.controlefxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;

import com.deezghostingfy.App;
import com.deezghostingfy.dados.Video;
import com.deezghostingfy.pesquisa.Pesquisa;
import com.deezghostingfy.dados.Sessao;


public class ControladoraTelaVideo implements Initializable {
    private static Video videoAtual;

    @FXML
    private Button adicionarPlaylistBotao;

    @FXML
    private Button botaoCurtir;

    @FXML
    private Button atualizarBotao;

    @FXML
    private Button voltarBotao;

    @FXML
    private ChoiceBox<String> playlistCaixaSelecao;

    @FXML
    private WebView videoView;

    @Override public void initialize(URL arg0, ResourceBundle arg1) {
        if (Sessao.acessarPlaylists().size() > 0)
            playlistCaixaSelecao.getItems().addAll(Sessao.listaDeNomes());

        WebEngine engine = videoView.getEngine();
        engine.load(videoAtual.getLink());
    }

    public static void EncontraVideoAtual(String titulo) {
        for (var video: Pesquisa.Resultado()) 
            if (video.getTitulo().equals(titulo))
                videoAtual = video;
    }

    @FXML
    void atualizar(ActionEvent event) {
        playlistCaixaSelecao.getItems().clear();

        if (Sessao.acessarPlaylists().size() > 0)
            playlistCaixaSelecao.getItems().addAll(Sessao.listaDeNomes());
    }

    @FXML
    void adicionarPlaylist(ActionEvent event) {
        String playlist = playlistCaixaSelecao.getValue();
        Sessao.adicionarMusica(playlist, videoAtual);
    }

    @FXML
    void curtir(ActionEvent event) {
        Sessao.adicionarMusicaCurtida(videoAtual);
    }

    @FXML
    void voltar(ActionEvent event) {
        videoView.getEngine().load(null);
        try {
            App.redimensiona(750, 450);
            App.setRoot("telaPesquisaFXML");
        } catch (IOException e) {
            System.out.println("erro video");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

}

