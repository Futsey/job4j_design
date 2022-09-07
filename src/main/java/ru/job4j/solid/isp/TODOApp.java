package ru.job4j.solid.isp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TODOApp {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    private static final String SEPARATOR = System.lineSeparator();
    private static final String MAINMENU = "Welcome! Any task you need to: "
            .concat(SEPARATOR).concat("1. Show me my tasks")
            .concat(SEPARATOR).concat("2. Find my task")
            .concat(SEPARATOR).concat("3. Add a task");
    private Menu menu = new SimpleMenu();
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void showMainMenu() throws IOException {
        System.out.println(MAINMENU);
        int menuButton = Integer.parseInt(reader.readLine());
        choice(menuButton);
    }

    private void choice(int menuButton) throws IOException {
        switch (menuButton) {
            case 1: showMenu();
                break;
            case 2: findTask();
                break;
            case 3: addTask();
                break;
            default:
                System.out.println("Oooops, there`s no such command. Please enter from 1 to 3!");
                showMainMenu();
        }
    }

    private void showMenu() throws IOException {
        MenuPrinter print = new ConsolePrint();
        print.print(menu);
        showMainMenu();
    }

    private boolean findTask() throws IOException {
        boolean rsl = false;
        System.out.println("Please enter the name of the task to find:");
        if (menu.select(reader.readLine()).isPresent()) {
            rsl = true;
            System.out.println("Menu item found");
        } else {
            System.out.println("Menu item not found");
        }
        showMainMenu();
        return rsl;
    }

    private void addTask() throws IOException {
        String parentName = Menu.ROOT;
        System.out.println("Please enter 1 if it`s new task".concat(SEPARATOR)
                .concat("Please enter 2 if you want to expand TODOList"));
        String isNewTaskUserAnswer = reader.readLine();
        if (isNewTask(Integer.parseInt(isNewTaskUserAnswer))) {
            System.out.println("Please enter new task name:");
            menu.add(parentName, reader.readLine(), STUB_ACTION);
        } else {
            System.out.println("Please enter the name of existing task:");
            String taskName = reader.readLine();
            System.out.println("Please enter subtask");
            String childName = reader.readLine();
            menu.add(taskName, childName, STUB_ACTION);
        }
        showMainMenu();
    }

    private boolean isNewTask(int menuButton) throws IOException {
        boolean rsl = false;
        switch (menuButton) {
            case 1: rsl = true;
                break;
            case 2: rsl = false;
                break;
            default:
                System.out.println("Oooops, there`s no such command. Please enter from 1 or 2!");
                showMainMenu();
        }
        return rsl;
    }

    public static void main(String[] args) throws IOException {
        TODOApp td = new TODOApp();
        td.showMainMenu();
    }
}
