package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                    text.append((char) read);
            }
            String[] data = text.toString().split(System.lineSeparator());
            for (String el : data) {
                int number = Integer.parseInt(el);
                System.out.println((number % 2 == 0) ? el + " even" : el + " odd");
                if (number % 2 == 0) {
                    System.out.println("Just even: " + el);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
