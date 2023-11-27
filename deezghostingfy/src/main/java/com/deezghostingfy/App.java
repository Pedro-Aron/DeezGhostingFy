package com.deezghostingfy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.deezghostingfy.dados.Playlist;
import com.deezghostingfy.dados.Sessao;
import com.deezghostingfy.dados.Video;
import com.deezghostingfy.dados.Conexao.Connection;
import com.deezghostingfy.pesquisa.Pesquisa;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * JavaFX App
 */
public class App extends Application {


    private static Scene scene;
    private static Stage estagio;

    @Override public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("telaPesquisaFXML"), 750, 450);
        stage.setScene(scene);
        stage.show();
        estagio = stage;
        //furto do stage para possibilitar redimensionamento da janela via código
    }

    public static void redimensiona(double l, double a) {
        estagio.setHeight(a);
        estagio.setWidth(l);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException {
        
        Connection conector = new Connection();

        if (conector.verificaBancoDeDados().iterator().hasNext()) {
            for (Playlist a : conector.verificaBancoDeDados()) {
                if (a.getNome().equals("Músicas Curtidas"))
                    Sessao.defineMusicasCurtidas(a);
                else
                    Sessao.addPlaylist(a);
            }        
        } else {
            System.out.println("banco de dados vazio");
        }

        launch();
    }

}