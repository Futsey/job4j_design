package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String split = "=";
            String comment = "#";
            String whiteLines = " ";
            Pattern pattern = Pattern.compile(split);
            Matcher matcherSplit = pattern.matcher(split);
            Matcher matcherComment = pattern.matcher(comment);
            Matcher matcherWhiteLines = pattern.matcher(whiteLines);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(split);
                matcherSplit.reset(line);
                if (matcherSplit.find()) {
                    values.put(array[0], array[1]);
                } else if (matcherComment.find() || matcherWhiteLines.find()) {
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
       return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
