package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogFilter {

    String[] text = {
            "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:18 +0300] \"GET /items/ajax.html HTTP/1.1\" 404 1113",
            "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] \"GET /job4j.ru/profile HTTP/1.1\" 302 -",
            "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:23 +0300] \"GET /job4j.ru/profile/ HTTP/1.1\" 404 1110",
            "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:34 +0300] \"GET /job4j.ru/profileNew/ HTTP/1.1\" 404 -",
            "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:48 +0300] \"GET / HTTP/1.1\" 200 11488",
            "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] \"GET /404.png HTTP/1.1\" 200 5103",
            "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] \"GET /tomcat.css HTTP/1.1\" 200 5931",
            "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] \"GET /bg-nav.png HTTP/1.1\" 200 1404",
            "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] \"GET /bg-button.png HTTP/1.1\" 200 713",
            "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:49 +0300] \"GET /bg-middle.png HTTP/1.1\" 200 4048",
            "0:0:0:0:0:0:0:1 - - [19/Feb/2020:15:21:18 +0300] \"GET /items/ajax.html HTTP/1.1\" 405 1113"
    };

    public void writeData(String[] text) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("log.txt"))) {
            for (String el : text) {
                out.write(el);
                out.write(System.lineSeparator());
            }
            System.out.println("File record done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readData(String file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            System.out.println("Start reading: " + file);
            in.lines().forEach(System.out::println);
            System.out.println("File read done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String el : log) {
                out.write(el);
            }
            System.out.println("Sorted file record done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> filter(String file) {
        List<String> data = new ArrayList<>();
        String patternStr = "\"\\s404";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(patternStr);
            String line;
            while ((line = reader.readLine()) != null) {
                matcher.reset(line);
                if (matcher.find()) {
                    data.add(line);
                    System.out.println("We found: " + line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        logFilter.writeData(logFilter.text);
        logFilter.readData("log.txt");
        System.out.println("____________________________");
        List<String> log = filter("log.txt");
        System.out.println("____________________________");
        save(log, "404.txt");
    }
}