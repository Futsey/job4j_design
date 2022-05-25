package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Illegal key value");
        }
        return values.get(key);
    }

    private void check(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Key and value is absent");
        }
        if (args.length == 1) {
            throw new IllegalArgumentException("Value is absent");
        }
        if (args[0].isEmpty()) {
            throw new IllegalArgumentException("Key is empty");
        }
        if (args[1].isEmpty()) {
            throw new IllegalArgumentException("Value is empty");
        }
        if (!args[0].startsWith("-")) {
            throw new IllegalArgumentException("Missing \"-\" in key");
        }
    }

    private void parse(String[] args) {
            for (String el : args) {
                if (el.contains("=")) {
                    String[] temp = el.split("=", 2);
                    check(temp);
                    values.put(temp[0].replaceFirst("-", ""), temp[1]);
                } else {
                    throw new IllegalArgumentException("Missing \"=\" in file");
                }
            }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("You have no arguments loaded");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
