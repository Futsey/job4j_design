package ru.job4j.solid.isp;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static final String SEPARATOR = System.lineSeparator();

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelect() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Cat", STUB_ACTION);
        menu.add("Cat", "Feed cat", STUB_ACTION);
        menu.add("Cat", "Caress cat", STUB_ACTION);
        menu.add("Cat", "Tell the cat a story about JAVA Patterns", STUB_ACTION);
        assertEquals(menu.select("Cat").get(),
                new Menu.MenuItemInfo(
                        "Cat", List.of("Feed cat",
                        "Caress cat", "Tell the cat a story about JAVA Patterns"), STUB_ACTION, "1."));
    }

    @Test
    void whenSelectNonExistent() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Cat", STUB_ACTION);
        menu.add("Cat", "Feed cat", STUB_ACTION);
        assertEquals(menu.select("Study"),
                Optional.empty());
    }

    @Test
    public void whenSomethingPrinted() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new ConsolePrint().print(menu);
        String exp = new StringBuilder().
                append("1.Сходить в магазин").append(System.lineSeparator()).
                append("--1.1.Купить продукты").append(System.lineSeparator()).
                append("----1.1.1.Купить хлеб").append(System.lineSeparator()).
                append("----1.1.2.Купить молоко").append(System.lineSeparator()).
                append("2.Покормить собаку").append(System.lineSeparator()).toString();
        assertEquals(exp, out.toString());
    }
}