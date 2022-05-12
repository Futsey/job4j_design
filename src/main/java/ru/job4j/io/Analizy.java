package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analizy {
    List<String> data = new ArrayList<>();

    public boolean inactiveStatus(String[] array) {
        boolean rsl = false;
        if (array[0].equals("400") || array[0].equals("500")) {
            rsl = true;
        } else if (array[0].equals("200") || array[0].equals("300")) {
            rsl = false;
        }
        return rsl;
    }

    public static void save(List<String> data, String target) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            int count = 0;
            for (String el : data) {
                if (count < 1) {
                    out.print(el + ";");
                    count++;
                } else {
                    out.println(el + ";");
                    count = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] array = line.split(" ");
                    if (inactiveStatus(array) && count != 1) {
                        data.add(array[1]);
                        count++;
                        System.out.print(array[1] + ";");
                    } else if (!inactiveStatus(array) && count > 0) {
                        data.add(array[1]);
                        count = 0;
                        System.out.println(array[1] + ";");
                    }
                }
            }
            save(data, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("unavailable.csv", "unavailableLog");
    }
}
