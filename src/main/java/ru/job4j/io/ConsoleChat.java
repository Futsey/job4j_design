package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.util.*;

/**
 * Класс описывает поведение простого консольного чата.
 * Класс реализует следующее поведение:
 * - пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * - программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
 * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
 * - при вводе слова «закончить» программа прекращает работу.
 * - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class ConsoleChat {

    /**
     * Поля:
     * {@link ru.job4j.io.ConsoleChat#OUT)} - завершает работу программы
     * {@link ru.job4j.io.ConsoleChat#STOP)} - завершает вывод ответа Бота в чат
     * {@link ru.job4j.io.ConsoleChat#CONTINUE)} - возобновляет вывод ответа Бота в чат
     * {@link ru.job4j.io.ConsoleChat#path)} - путь к текстовому Лог-файлу
     * {@link ru.job4j.io.ConsoleChat#botAnswers)} - путь к текстовому перечню ответов Бота
     * {@link ru.job4j.io.ConsoleChat#chatList)} - Лист, куда записываем все фразы от пользователя и бота
     * для последующей записи в Лог-файл
     */
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private static List<String> chatList = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Метод, описывающий логику работы программы с ветвлением в зависимости от введенных пользователем данных
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String userMessage = scanner.next();
        if (userMessage.equals(STOP)) {
            chatList.add(userMessage);
            getContinue();
            System.out.println("Continue");
            run();
        }
        if (userMessage.equals(OUT)) {
            chatList.add(userMessage);
            System.out.println("I`ll be back");
            System.exit(0);
        }
        if (!userMessage.equals(STOP) && !userMessage.equals(CONTINUE) && !userMessage.equals(OUT)) {
            chatList.add(userMessage);
            saveLog(chatList);
            readPhrases();
            run();
        }
    }

    /**
     * Метод, считывающий в определенной кодировке варианты ответа бота и выбирающий случайный ответ посредством метода
     * {@link ru.job4j.io.ConsoleChat#randomBotLine(List)} )}
     */
    private void readPhrases() {
        List<String> lineList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            br.lines().forEach(lineList::add);
            String tmp = lineList.get(randomBotLine(lineList));
            System.out.println(tmp);
            chatList.add(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для записи всего массива чата как со стороны бота, так и со стороны пользователя
     * @param chatList
     */
    private void saveLog(List<String> chatList) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            chatList.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для отслеживания ключевого слова
     * {@link ru.job4j.io.ConsoleChat#CONTINUE)} - возобновляет вывод ответа Бота в чат
     * @return true
     */
    private boolean getContinue() {
        Scanner scanner = new Scanner(System.in);
        String continueMessage = scanner.next();
        while (!continueMessage.equals(CONTINUE)) {
            continueMessage = scanner.next();
            chatList.add(continueMessage);
        }
        return true;
    }

    /**
     * Метод, в случайном порядке выбирающий вариант ответа по индексу поступившего в аргументы Листа
     * @param lineList
     * @return индекс случайной строки
     */
    private int randomBotLine(List<String> lineList) {
        Random random = new Random();
        return random.nextInt(chatList.size());
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(args[0], args[1]);
        cc.run();
    }
}
