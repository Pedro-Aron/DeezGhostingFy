package com.deezghostingfy.dados;

import com.deezghostingfy.pesquisa.ExtraiPesquisa;
import java.util.ArrayList;

public class Video {
    private String id;
    private String linkThumb;
    private String titulo;

    public Video(String id, String thumb, String titulo) {
        this.id = id;
        this.linkThumb = thumb;
        this.titulo = titulo;
    }

    public String getLink() {
        return "https://www.youtube.com/watch?v="+this.id;
    }

    public String getThumb() {
        return this.linkThumb;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public static Video[] GeraVetor() {
        ArrayList<Video> lista = ExtraiPesquisa.ExtraiResultado();
        Video[] videos = new Video[lista.size()];

        for (int i = 0; i < lista.size(); i++) 
            videos[i] = lista.get(i);

        return videos;
    }
}
