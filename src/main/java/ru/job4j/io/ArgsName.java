package ru.job4j.io;

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
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("You have no arguments loaded");
        }
        String patternStr = "=";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(patternStr);
            for (String el : args) {
                matcher.reset(el);
                if (matcher.find()) {
                    String[] tmp = el.replaceFirst("-", "").split(patternStr, 2);
                    check(tmp);
                    values.put(tmp[0], tmp[1]);
                }
            }
    }

    public static ArgsName of(String[] args) {
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
