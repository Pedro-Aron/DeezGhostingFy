package com.deezghostingfy.pesquisa;

import com.deezghostingfy.dados.Video;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Pesquisa {
    private static ArrayList<Video> resultados;

    public static ArrayList<Video> Resultado() {
        return resultados;
    }

    public static boolean RealizaPesquisa(String termos) throws IOException {
        ProcessBuilder processoBuilderPython = new ProcessBuilder("python", "deezghostingfy/src/main/java/com/deezghostingfy/pesquisa/pesquisa.py", termos);
        processoBuilderPython.redirectErrorStream(true);
        Process processoPython = processoBuilderPython.start();
        List<String> output = new ArrayList<>();

        try (BufferedReader outputBf = new BufferedReader(new InputStreamReader(processoPython.getInputStream()))) {
            output = outputBf.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }

        if (output.size() == 0) {
            System.out.println("Falha na pesquisa");
            return false;
        }

        output.forEach(System.out::println);
        System.out.println("Sucesso");
        return true;
    }

    public static ArrayList<Video> ExtraiResultado() {
        BufferedReader leitor = null;

        try {
            leitor = new BufferedReader(new InputStreamReader(new FileInputStream("deezghostingfy/src/main/java/com/deezghostingfy/pesquisa/resultados.txt")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        ArrayList<Video> videos = new ArrayList<Video>(); 
        String[] novoVideo = new String[3];

        try {
            for (int i = 1; i < 46; i++) {

                if (i % 3 == 0) {
                    novoVideo[2] = leitor.readLine();
                    videos.add(new Video(novoVideo[2], novoVideo[1], novoVideo[0])); 
                } else 
                    novoVideo[(i%3)-1] = leitor.readLine();

            }

            leitor.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        resultados = videos;
        return videos;
    }
}
