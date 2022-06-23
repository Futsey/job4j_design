package ru.job4j.io.scandisk;

import java.util.HashMap;
import java.util.Map;

public class ArgsCheck {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key doesn`t exist: " + key);
        }
        return values.get(key);
    }

    public static ArgsCheck of(String[] args) {
        ArgsCheck names = new ArgsCheck();
        names.parse(args);
        return names;
    }

    private void parse(String[] args) {
        for (String str : args) {
            String[] tmp = validate(str);
            values.put(tmp[0].substring(1), tmp[1]);
        }
    }

    private String[] validate(String str) {
        if (!str.startsWith("-") || !str.contains("=")) {
            throw new IllegalArgumentException("Pattern does`t match. You need to start with " + "'-'"
                                                + "and delimeter must be: " + "'='");
        }
        String[] tmp = str.split("=", 2);
        if (tmp[0].isBlank()) {
            throw new IllegalArgumentException("Please add key");
        }
        if (tmp[1].isBlank()) {
            throw new IllegalArgumentException("Please add value");
        }
        return tmp;
    }
}
