package com.deezghostingfy.dados.Conexao;

import java.util.ArrayList;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.deezghostingfy.dados.Playlist;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class Connection {
    public MongoClientURI connectionString; 
    public MongoClient mongoClient;
    public CodecRegistry pojoCodecRegistry;
    public MongoDatabase database;
    public MongoCollection<Playlist>  collection;

    public Connection() {
 

        connectionString = new MongoClientURI("mongodb://localhost:27017");
        mongoClient = new MongoClient(connectionString);
        pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        database = mongoClient.getDatabase("mongodbjava").withCodecRegistry(pojoCodecRegistry);  
        collection = database.getCollection("javaprogram", Playlist.class);      

    }

    public void adicionaPlaylistBancoDeDados(Playlist p) {  
        collection.insertOne(p);
    }

    public FindIterable<Playlist> verificaBancoDeDados() {
        return collection.find();
    }

    public void atualizaBancoDados(Playlist p) {
        collection.updateOne(Filters.eq("nome", "musicas"), Updates.set("videos", p.acessarLista()));
    }
}
