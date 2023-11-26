package com.deezghostingfy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.deezghostingfy.dados.Sessao;
import com.deezghostingfy.pesquisa.Pesquisa;

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
        launch();
    }

}