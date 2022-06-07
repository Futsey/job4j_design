package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class CSVReader {
    private final Path path;
    private final String stdout;
    private final String delimiter;
    private final String out;
    private final String[] filter;

    public CSVReader(ArgsName args) {
        this.path = Paths.get(args.get("path"));
        this.stdout = "stdout";
        this.delimiter = args.get("delimiter");
        this.out = args.get("out");
        this.filter = args.get("filter").split(",");
    }

    private void validate() {
        if (!";".equals(delimiter)) {
            throw new IllegalArgumentException("Wrong char to split: " + delimiter);
        }

        if (!path.toFile().isFile() || !path.toFile().exists()) {
            throw new IllegalArgumentException("Wrong filepath or file doesn't exist");
        }
    }

    public void handle() {
        validate();
        try (Scanner scanner = new Scanner(new FileInputStream(path.toFile()), StandardCharsets.UTF_8)) {
            scanner.useDelimiter(";");
            List<String> writerList = new ArrayList<>();
            List<Integer> indexList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");
                if (indexList.isEmpty()) {
                    indexList = findColumnIndexes(line, filter);
                }
                String concatString = concatStr(line, indexList);
                writerList.add(concatString);
            }
            printer(writerList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> findColumnIndexes(String[] splitString, String[] columns) {
        List<Integer> indexList = new ArrayList<>();
        for (String colName : columns) {
            int n = Arrays.asList(splitString).indexOf(colName);
            if (n != -1) {
                indexList.add(n);
            }
        }
        return indexList;
    }

    private void printer(List<String> listWriter) {
        if (out.equals(stdout)) {
            listWriter.forEach(System.out::println);
        } else {
            try (PrintWriter pw = new PrintWriter(new FileWriter(out, StandardCharsets.UTF_8))) {
                listWriter.forEach(pw::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String concatStr(String[] splitString, List<Integer> indexList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indexList.size(); i++) {
            int index = indexList.get(i);
            if (i == indexList.size() - 1) {
                sb.append(splitString[index]);
            } else {
                sb.append(splitString[index]).append(";");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        CSVReader csvReader = new CSVReader(argsName);
        csvReader.handle();
    }
}
