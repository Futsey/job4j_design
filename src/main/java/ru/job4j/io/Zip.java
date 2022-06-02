package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Класс производит архивацию данных.
 * В классе реализованы два метода:
 * для архивации одного файла
 * {@link #packSingleFile(File, File)}
 * для архивации директории (всех файлов в директории)
 * {@link #packFiles(List, File)}
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class Zip {

    /**
     * Метод для архивации директории (всех файлов в директории)
     */
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

    /**
     * Метод для архивации одного файла
     */
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

    /**
     * Метод для проверки валидности предоставленных ключей
     * В качестве ключа передаётся:
     * -d - directory - директория, в которую мы хотим архивировать.
     * -e - exclude - исключить файлы с расширением class.
     * -o - output - адрес, куда мы архивируем.
     */
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
        if (!output.contains(".")) {
            throw new IllegalArgumentException("You have to add filename to write the data");
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("Incorrect start parameter");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        Path startDirectory = Path.of(argsName.get("d"));
        Predicate<Path> pred = (p) -> p.toFile().getName().endsWith(argsName.get("e"));
        List<Path> list = Search.search(startDirectory, pred);
        Path target = Path.of(argsName.get("o"));
        zip.packFiles(list.stream()
                .map(Path::toFile)
                .collect(Collectors.toList()), target.toFile());
    }
}
