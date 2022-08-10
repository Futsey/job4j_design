package ru.job4j.kiss;

import java.util.*;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return numerator(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return numerator(value, comparator.reversed());
    }

    public <T> T numerator(List<T> value, Comparator<T> comparator) {
        T res = isEmpty(value);
        for (T el : value) {
            if (comparator.compare(el, res) > 0) {
                res = el;
            }
        }
        return res;
    }

    public <T> T isEmpty(List<T> value) {
        return value.isEmpty() ? null : value.get(0);
    }

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> value = new LinkedList<>();
        Comparator<Integer> comparator = Comparator.naturalOrder();
        MaxMin maxMin = new MaxMin();
        for (int i = 0; i < 10; i++) {
            value.add(random.nextInt(10));
        }
        System.out.println("MaxMin: main: Before sort");
        value.forEach(System.out::println);
        System.out.println("=========================");
        System.out.println("MaxMin: main: After max sort");
        System.out.println(maxMin.max(value, comparator));
        System.out.println("=========================");
        System.out.println("MaxMin: main: After min sort");
        System.out.println(maxMin.min(value, comparator));
    }
}
