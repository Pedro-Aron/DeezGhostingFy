package com.deezghostingfy.pesquisa;

import com.deezghostingfy.dados.Video;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Pesquisa {
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

        leitor.close();

        return videos;
    }
}
