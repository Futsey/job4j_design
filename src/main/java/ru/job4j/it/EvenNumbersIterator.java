package ru.job4j.it;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс реализует инерфейс
 * @see Iterator для прохода по четным элементам плоского массива
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    /**
     * Конструктор дополнен двумя основными полями:
     * инициализирован индекс для корректного перемещения указателя при применении метода
     * {@link #nextEven()}
     */
    public EvenNumbersIterator(int[] data) {
        this.data = data;
        index = -1;
        nextEven();
    }

    /**
     * Метод для проверки наличия следующего элемента (в естесственном порядке)
     * @return индекс массива данных
     */
    @Override
    public boolean hasNext() {
        return index < data.length;
    }

    /**
     * Метод возвращает итерированный элемент (в естесственном порядке)
     * Для фильтррации только четных элементов используем метод
     * {@link #nextEven()}
     * @return четный элемент массива
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No such element.");
        }
        Integer rsl = data[index];
        nextEven();
        return rsl;
    }

    /**
     * Метод - надстройка над методом
     * {@link #next()} для фильтрации четных элементов массива (в естесственном порядке)
     */
    private void nextEven() {
        index = index + 1;
        while (index < data.length && data[index] % 2 == 1) {
            index++;
        }
    }

    @Override
    public String toString() {
        return "EvenNumbersIterator{"
                + "data=" + Arrays.toString(data)
                + ", index=" + index
                + '}';
    }

    public static void main(String[] args) {
        Iterator<Integer> testEvenNumberIt = new EvenNumbersIterator(new int[] {4, 2, 1, 1});
        while (testEvenNumberIt.hasNext()) {
            System.out.println(testEvenNumberIt.next());
        }
    }
}
