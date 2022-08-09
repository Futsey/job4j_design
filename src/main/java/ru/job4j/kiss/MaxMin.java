package ru.job4j.kiss;

import java.util.*;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        T result = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (value.get(i) != null
                    && comparator.compare(result, value.get(i)) < 0) {
                result = value.get(i);
            }
        }
        return result;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        T result = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (value.get(i) != null
                    && comparator.compare(result, value.get(i)) > 0) {
                result = value.get(i);
            }
        }
        return result;
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
