package ru.job4j.it;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        index = -1;
        nextEven();
    }

    @Override
    public boolean hasNext() {
        return index < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No such element.");
        }
        Integer rsl = data[index];
        nextEven();
        return rsl;
    }

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
