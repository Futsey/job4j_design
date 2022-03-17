package ru.job4j.it;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс реализует инерфейс
 * @see Iterator для поэлементного прохода по двумерному массиву
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class MatrixIt implements Iterator<Integer> {

    /**
     * Все данные храним в двумерном массиве data[][]
     * Хождение по каждому измерению массива осуществляется благодаря полям row и column
     */
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Метод для проверки наличия следующего элемента (в естесственном порядке)
     * В методе произведена проверка на наличие элементов в двух измерениях массива
     * @return индекс двумерного массива (координата row элемента матрицы)
     */
    @Override
    public boolean hasNext() {
        while (row < data.length && column == data[row].length) {
            row++;
            column = 0;
        }
        return row < data.length;
    }

    /**
     * Метод для движения по элементам массива с инкрементацией поля column(в естесственном порядке)
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }

    @Override
    public String toString() {
        return "MatrixIt{"
                + "row=" + Arrays.toString(data[row])
                + ", column=" + Arrays.toString(data[column])
                + '}';
    }

    public static void main(String[] args) {
        int[][] in = {
                {1, 2},
                {3, 4}
        };
        MatrixIt testMatrix = new MatrixIt(in);
        testMatrix.hasNext();
        testMatrix.next();
        System.out.println(testMatrix);
    }
}
