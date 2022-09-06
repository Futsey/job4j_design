package ru.job4j.solid.isp;

public class ConsolePrint implements MenuPrinter {

    public static final String INDENT = "----";
    public static final String SEPARATOR = System.lineSeparator();

    public void print(Menu menu) {
        StringBuilder builder = new StringBuilder();
        menu.forEach(x -> {
            builder.append(INDENT.repeat((x.getNumber().length() / 2)));
            builder.append(x.getNumber()).append(x.getName()).append(SEPARATOR);
        });
        System.out.println(builder);
    }
}
