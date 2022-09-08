package ru.job4j.solid.isp;

public class ConsolePrint implements MenuPrinter {

    public static final String INDENT = "--";
    public static final String SEPARATOR = System.lineSeparator();

    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            int count = menuItemInfo.getNumber().split("\\.").length - 1;
            System.out.println(INDENT.repeat(count)
                    + menuItemInfo.getNumber()
                    + menuItemInfo.getName());
        }
    }
}
