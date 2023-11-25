package com.deezghostingfy.dados;

import java.util.ArrayList;
import java.util.Iterator;

public class Sessao {
    private static ArrayList<Playlist> playlists;
    private static MusicasCurtidas musicasCurtidas;

    public static ArrayList<Playlist> acessarPlaylists() {
        return playlists;
    }

    public static Playlist acessarPlaylist(int i) {
        return playlists.get(i);
    }

    public static void addPlaylist(String nome) {
        playlists.add(new Playlist(nome));
    }

    public static void defineCapaPlaylists() {
        for (var playlist: playlists) 
            playlist.defineCapa();
    }

    public static MusicasCurtidas acessarMusicasCurtidas() {
        return musicasCurtidas;
    }

    public static void setMusicasCurtidas(MusicasCurtidas mc) {
        musicasCurtidas = mc;
    }

    // Este metodo apenas remove musicas na sessão
    // é necessário fazer sua integração com o 
    // banco de dados, quando este for implementado
    public static boolean removerMusicaPlaylist(String playlist, String musica) {
        Playlist pl = null;

        for (var pL: playlists) 
            if (pL.getNome().toLowerCase().contains(playlist.toLowerCase())) 
                pl = pL;
        
        return pl.removerMusica(musica);
    }

    // mesmo sobre o metodo acime
    public static boolean removerMusicaCurtida(String musica) {
        return musicasCurtidas.removerMusica(musica);
    }

    public static void removerPlaylist(String playlist) {
        Iterator<Playlist> iterador = playlists.iterator();

        while (iterador.hasNext()) {
            if (iterador.next().getNome().toLowerCase().contains(playlist.toLowerCase()))
                iterador.remove();
        }
    }
}
