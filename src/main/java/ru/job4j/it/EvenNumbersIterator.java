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

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * Метод для проверки наличия следующего элемента (в естесственном порядке)
     * @return индекс четного элемента массива данных
     */
    @Override
    public boolean hasNext() {
        while (index < data.length && data[index] % 2 == 1) {
            index++;
        }
        return index < data.length;
    }

    /**
     * Метод возвращает итерированный элемент (в естесственном порядке)
     * @return следующий элемент массива
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No such element.");
        }
        return data[index++];
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
