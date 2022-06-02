package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс производит проверку и поиск файлов по определенному условию (Predicate).
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class Search {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        Path start = Paths.get(args[0]);
        check(args);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    /**
     * Метод для хождения по файловому дереву
     * Через созданный экземпляр класса
     * @see SearchFiles
     * реализующего интерфейс
     * @see java.nio.file.FileVisitor
     * используем функционал метода
     * {@link java.nio.file.Files#walkFileTree(Path, FileVisitor)}
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    /**
     * Метод для проверки всех заданных по умолчанию аргументов
     */
    public static void check(String[] args) {
        File path = new File(args[0]);
        String charToSearch = args[1];
            if (!charToSearch.startsWith(".")) {
                throw new IllegalArgumentException("Illegal file extension " + "\"" + charToSearch + "\"");
            }
            if (path.isAbsolute() && !path.isDirectory()) {
                throw new IllegalArgumentException("Argument is not a directory " + "\"" + path + "\"");
            }
    }
}
