package com.deezghostingfy.dados;

import java.util.ArrayList;

public class Playlist {
    private String nome;
    private ArrayList<Video> videos;

    public Playlist(String nome) {
        this.nome = nome;
    }

    public void addVideo(Video video) {
        this.videos.add(video);
    }

    public Video get(int i) {
        return this.videos.get(i);
    }

    public ArrayList<Video> acessarLista() {
        return this.videos;
    }
}
