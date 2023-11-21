package com.deezghostingfy.pesquisa;

import com.deezghostingfy.dados.Video;
import java.util.ArrayList;
import java.util.Scanner;

public class ExtraiPesquisa {
    public static ArrayList<Video> ExtraiResultado() {
        Scanner leitor = new Scanner("resultados.txt");
        ArrayList<Video> videos = new ArrayList<Video>(); 
        String[] novoVideo = new String[3];

        for (int i = 1; leitor.hasNextLine(); i++) {
            if (i % 3 == 0) {
                novoVideo[2] = leitor.nextLine();
                videos.add(new Video(novoVideo[2], novoVideo[1], novoVideo[0])); 
            } else 
                novoVideo[(i%3)-1] = leitor.nextLine();
        }

        return videos;
    }
}
