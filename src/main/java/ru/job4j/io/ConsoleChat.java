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
     * {@link ru.job4j.io.ConsoleChat#chatList)} - Лист, куда записываем все фразы от пользователя и Бота
     * для последующей записи в Лог-файл
     * {@link ru.job4j.io.ConsoleChat#answerList)} - Лист, куда записываем все фразы от Бота
     */
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private static List<String> chatList = new LinkedList<>();
    private static List<String> answerList = new ArrayList<>();;

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
        chatList.add(userMessage);
        readPhrases();
        if (STOP.equals(userMessage)) {
            String stop = "Stop";
            System.out.println(stop);
            chatList.add(stop);
            getContinue();
            String contin = "Continue";
            System.out.println(contin);
            chatList.add(contin);
            run();
        }
        if (OUT.equals(userMessage)) {
            String out = "I`ll be back";
            System.out.println(out);
            chatList.add(out);
            saveLog(chatList);
            System.exit(0);
        }
        if (!STOP.equals(userMessage) && !CONTINUE.equals(userMessage) && !OUT.equals(userMessage)) {
            String botTalks = answerList.get(randomBotLine(answerList));
            System.out.println(botTalks);
            chatList.add(botTalks);
            run();
        }
    }

    /**
     * Метод, считывающий в определенной кодировке варианты ответа бота и выбирающий случайный ответ посредством метода
     * {@link ru.job4j.io.ConsoleChat#randomBotLine(List)} )}
     */
    private void readPhrases() {
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            br.lines().forEach(answerList::add);
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
        while (!CONTINUE.equals(continueMessage)) {
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
        return random.nextInt(lineList.size());
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(args[0], args[1]);
        cc.run();
    }
}
