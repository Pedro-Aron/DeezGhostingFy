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

import com.deezghostingfy.dados.Sessao;
import com.deezghostingfy.pesquisa.Pesquisa;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
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
        ArrayList<Teste> array = new ArrayList<>();
        Teste t1 = new Teste();
        t1.setId(1);
        t1.setDescricao("aaaaaa");
        t1.setPreco(213);
        array.add(t1);

        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(connectionString);
        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoDatabase database = mongoClient.getDatabase("mongodbjava").withCodecRegistry(pojoCodecRegistry);  

        //MongoClient mongoClient = new MongoClient( "localhost" , 27017 ); 
        //MongoDatabase db = mongoClient.getDatabase("mongodbjava");

		//Inserting sample record by creating collection and document.
		//MongoCollection<org.bson.Document>  collection= database.getCollection("javaprogram");
		//org.bson.Document doc =(org.bson.Document) new org.bson.Document("teste1", array);
        
        MongoCollection<Teste>  collection= database.getCollection("javaprogram", Teste.class);
		collection.drop(); 

        
        //doc.append("campo", "sexo");
		collection.insertOne(t1);
        //collection.insertOne(t1);
        //doc =(org.bson.Document) new org.bson.Document("name2","hello");
		//collection.insertOne(doc);
        //doc =(org.bson.Document) new org.bson.Document("name3","sexo");
        //collection.insertOne(doc);

        System.out.println("---------------------------------------");
        for(Teste a: collection.find()) {
            System.out.println(a.getDescricao());
        }
       

        //Atualizando um objeto
//        collection.updateOne(new Document("_id", 1), set("descricao", "Arroz Parbolizado"));

        //Deletando um objeto
//        collection.deleteOne(new Document("descricao", "  Parbolizado"));
        //launch();
    }

}