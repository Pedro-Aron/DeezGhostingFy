package com.deezghostingfy.dados;

import java.util.ArrayList;
import java.util.Iterator;

public class Playlist {
    private String nome;
    private String capa;
    private ArrayList<Video> videos;

    public Playlist(String nome) {
        this.nome = nome;
        this.capa = null;
    }

    protected void defineCapa() {
        this.capa = videos.get(0).getThumb();
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
}
