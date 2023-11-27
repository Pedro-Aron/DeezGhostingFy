package com.deezghostingfy.dados;

import java.util.ArrayList;
import java.util.Iterator;

import com.deezghostingfy.dados.Conexao.Connection;

public class Sessao {
    private static ArrayList<Playlist> playlists = new ArrayList<Playlist>();
    private static MusicasCurtidas musicasCurtidas = new MusicasCurtidas();

    public static ArrayList<Playlist> acessarPlaylists() {
        return playlists;
    }

    public static Playlist acessarPlaylist(int i) {
        return playlists.get(i);
    }

    public static void addPlaylist(String nome) {
        Playlist p = new Playlist(nome);
        playlists.add(p);
    }

    public static void addPlaylist(Playlist p) {
        playlists.add(p);
    }   

    public static void addPlaylistMongo(Playlist p) {
        Connection con = new Connection();
        con.adicionaPlaylistBancoDeDados(p);

        playlists.add(p);
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

    public static void adicionarMusicaCurtida(Video curtido) {
        musicasCurtidas.addVideo(curtido);
    }

    public static String[] listaDeNomes() {
        String[] lista = new String[playlists.size()];

        for (int i = 0; i < playlists.size(); i++) 
            lista[i] = playlists.get(i).getNome();

        return lista;
    }

    public static void adicionarMusica(String playlist, Video musica) {
        for (var pl: playlists) 
            if (pl.getNome().equals(playlist)) {
                pl.addVideo(musica);
                break;
            }
    }

    public static Playlist getPlaylist(String nome) {
        for(Playlist p : playlists) {
            if(p.getNome().equals(nome))
                return p;
                
        }
        return null;
    }
}
