package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file: sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                    zip.closeEntry();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("You have no arguments loaded");
        }
        ArgsName argsName = ArgsName.of(args);
        String dir = argsName.get("d");
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        Path path = Paths.get(dir);
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException("Source doesn't exist");
        }
        if (!output.isEmpty()) {
            throw new IllegalArgumentException("You have to add filename to write the data");
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("Incorrect start parameter");
        }
    }

    public static void main(String[] args) {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        validate(args);
        List<File> sources = List.of(new File("C:\\Test"));
        zip.packFiles(sources, new File("C:\\Test\\zipped.zip"));
    }
}
