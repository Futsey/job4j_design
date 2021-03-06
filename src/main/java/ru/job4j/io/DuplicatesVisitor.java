package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, List<Path>> allFiles = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        allFiles.putIfAbsent(fileProperty, new ArrayList<>());
        allFiles.get(fileProperty).add(file);
        return super.visitFile(file, attrs);
    }

    public void printDublicate() {
        for (List<Path> el : allFiles.values()) {
            if (el.size() > 1) {
                el.forEach(System.out::println);
            }
        }
    }
}

