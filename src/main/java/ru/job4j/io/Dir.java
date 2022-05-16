package ru.job4j.io;

import java.io.File;

public class Dir {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Fut\\IdeaProjects\\job4j_design\\src\\main\\java\\ru\\job4j\\io");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        String fileName = "Dir.java";
        for (File subfile : file.listFiles()) {
            if (subfile.getName().equals(fileName)) {
                System.out.println("Size of file : " + subfile.length());
                System.out.println("Name of file : " + subfile.getName());
            }
        }
    }
}
