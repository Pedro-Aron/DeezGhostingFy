package com.deezghostingfy.dados;

public class Video {
    private String id;
    private String linkThumb;
    private String titulo;

    public Video(){}

    public Video(String id, String thumb, String titulo) {
        this.id = id;
        this.linkThumb = thumb;
        this.titulo = titulo;
    }

    public String getLink() {
        return "https://www.youtube.com/embed/"+this.id+"?autoplay=1";
    }

    public String getThumb() {
        return this.linkThumb;
    }

    public String getTitulo() {
        return this.titulo;
    }
}
