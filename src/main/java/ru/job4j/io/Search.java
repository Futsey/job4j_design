package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        Path start = Paths.get(args[0]);
        if (check(args, String.valueOf(start))) {
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static boolean check(String[] args, String path) {
        boolean rsl = false;
        File file = new File(path);
        if (args.length == 2) {
            for (String el : args) {
                if (file.isDirectory()) {
                    if (el.startsWith(".")) {
                        rsl = true;
                    }
                } else {
                    throw new IllegalArgumentException("Argument is not a directory " + el);
                }
            }
        } else {
            throw new IllegalArgumentException("Not enough arguments");
        }
        return rsl;
    }
}
