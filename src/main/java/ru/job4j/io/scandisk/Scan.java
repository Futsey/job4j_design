package ru.job4j.io.scandisk;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Scan {


    public void find(ArgsCheck param) throws IOException {
        FileVisitor file = new FileVisitor(getCondition(param));
        Files.walkFileTree(Path.of(param.get("d")), file);
        try (PrintWriter out = new PrintWriter(new FileWriter(param.get("o")))) {
            for (Path path : file.getPath()) {
                out.println(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Predicate<Path> getCondition(ArgsCheck param) {
        String type = param.get("t");
        String tmp = param.get("n");
        Predicate<Path> rsl = null;
        if ("name".equals(type)) {
            rsl = p -> p.toFile().getName().equals(tmp);
        } else if ("mask".equals(type)) {
            rsl = p -> {
                String replaceStr = tmp
                        .replace(".", "[.]")
                        .replace("*", "\\w+")
                        .replace("?", "\\w");
                return Pattern.compile(replaceStr)
                        .matcher(p.toString())
                        .find();
            };
        } else if ("regex".equals(type)) {
            rsl = p -> Pattern.compile(tmp)
                    .matcher(p.toString())
                    .find();
        }
        return rsl;
    }

    private void validate(ArgsCheck param) {
        if (!Files.isDirectory(Path.of(param.get("d")))) {
            throw new IllegalArgumentException("Path is incorrect");
        }
        param.get("n");
        String type = param.get("t");
        if (!"name".equals(type) && !"mask".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException("Please check correctness of name, mask or regular expression");
        }
        param.get("o");
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Program arguments is null");
        }
        Scan findFile = new Scan();
        ArgsCheck param = ArgsCheck.of(args);
        findFile.validate(param);
        findFile.find(param);
    }
}
