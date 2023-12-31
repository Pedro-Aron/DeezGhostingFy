package com.deezghostingfy.dados;

import java.util.ArrayList;
import java.util.Iterator;

public class Playlist {
    private String nome;
    private String capa;
    private ArrayList<Video> videos;

    public Playlist(){}

    public Playlist(String nome) {
        this.videos = new ArrayList<Video>();
        this.nome = nome;
        this.capa = null;
    }

    protected void defineCapa() {
        if (this.videos.size() > 0)
            this.capa = videos.get(0).getLinkThumb();
        else
            this.capa = null;
    }

    public String getCapa() {
        return this.capa;
    }

    public void addVideo(Video video) {
        this.videos.add(video);
    }

    public String getNome() {
        return this.nome;
    }

    public Video get(int i) {
        return this.videos.get(i);
    }

    public ArrayList<Video> acessarLista() {
        return this.videos;
    }

    protected boolean removerMusica(String musica) {
        Iterator<Video> iterador = this.acessarLista().iterator();
        boolean removeu = false;

        while (iterador.hasNext()) {
            if (iterador.next().getTitulo().toLowerCase().contains(musica.toLowerCase())) {
                iterador.remove();
                removeu = true;
                break;
            }
        }

        return removeu;
    }

    public int size() {
        return this.videos.size();
    }

    public int getIndex(String titulo) {
        for (int i = 0; i < videos.size(); i++) 
            if (videos.get(i).getTitulo().equals(titulo))
                return i; 

        return -1;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }


    
    
}
