package com.deezghostingfy.controlefxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;

import java.io.IOException;

import com.deezghostingfy.App;
import com.deezghostingfy.dados.Video;
import com.deezghostingfy.pesquisa.Pesquisa;


public class ControladoraTelaVideo {
    private static Video videoAtual;

    @FXML
    private Button adicionarPlaylistBotao;

    @FXML
    private Button botaoCurtir;

    @FXML
    private Button tocarBotao;

    @FXML
    private Button voltarBotao;

    @FXML
    private ChoiceBox<?> playlistCaixaSelecao;

    @FXML
    private WebView videoView;

    public static void EncontraVideoAtual(String titulo) {
        for (var video: Pesquisa.Resultado()) 
            if (video.getTitulo().equals(titulo))
                videoAtual = video;
    }

    @FXML
    void tocar(ActionEvent event) {
        WebEngine engine = videoView.getEngine();
        engine.load(videoAtual.getLink());
    }

    @FXML
    void adicionarPlaylist(ActionEvent event) {

    }

    @FXML
    void curtir(ActionEvent event) {

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

