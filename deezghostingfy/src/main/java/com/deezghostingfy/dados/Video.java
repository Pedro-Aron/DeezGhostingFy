package com.deezghostingfy.dados;

public class Video {
    private String link;
    private String linkThumb;
    private String titulo;

    public Video(){}

    public Video(String id, String thumb, String titulo) {
        this.link = id;
        this.linkThumb = thumb;
        this.titulo = titulo;
    }

    public String getLink() {
        return this.link;
    }

    public String getLinkThumb() {
        return this.linkThumb;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setLinkThumb(String linkThumb) {
        this.linkThumb = linkThumb;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
}
