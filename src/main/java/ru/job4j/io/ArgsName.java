package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        String patternStr = "=";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(patternStr);
        if (args.length == 0) {
            throw new IllegalArgumentException("Key and value is absent");
        } else if (args.length == 1) {
            throw new IllegalArgumentException("Value is absent");
        } else {
            for (String el : args) {
                if (matcher.find()) {
                    String[] tmp = el.replaceFirst("-", "").split(patternStr, 2);
                    values.put(tmp[0], tmp[1]);
        }
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
