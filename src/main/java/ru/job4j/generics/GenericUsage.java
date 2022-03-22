package ru.job4j.generics;

import ru.job4j.it.BackwardArrayIt;

import java.lang.reflect.ParameterizedType;
import java.sql.Array;
import java.util.*;

public class GenericUsage {

    public static void main(String[] args) {
        List<Integer> l = List.of(1, 2, 3, 4, 5);
        new GenericUsage().printRsl(l);
        System.out.println("_________________________________");

        List<Person> per = List.of(new Person("name", 21, new Date(913716000000L)));
        new GenericUsage().printInfo(per);

        List<Programmer> pro = List.of(new Programmer("name123", 23, new Date(913716000000L)));
        new GenericUsage().printInfo(pro);
        System.out.println("_________________________________");

        ArrayList<Float> listOfNumbers = new FloatList();
        GenericUsage genericUsage = new GenericUsage();
        genericUsage.getClassType(listOfNumbers);
        System.out.println("_________________________________");

        List<? super Integer> listWithSuper = new ArrayList<>();
        listWithSuper.add(11);
        listWithSuper.add(12);
        listWithSuper.add(13);
        listWithSuper.add(14);
        new GenericUsage().addAll(listWithSuper);
        System.out.println("_________________________________");

        GenericsClass<String, String> gen = new GenericsClass<>("First key", "First value");
        System.out.println("Вывод в консоль: " + gen);

        GenericsClass<Integer, String> second = new GenericsClass<>(12345, "Second value");
        System.out.println("Вывод в консоль: " + second);
        System.out.println("_________________________________");

        List list = new ArrayList();
        list.add("first");
        list.add("second");
        list.add("third");
        System.out.println("Количество элементов в списке: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            String s = (String) list.get(i);
            System.out.println("Текущий элемент: " + s);
        }
        System.out.println("_________________________________");
    }

    public void printRsl(Collection<?> col) {
        for (Iterator<?> it = col.iterator(); it.hasNext();) {
            Object next = it.next();
            System.out.println(next);
        }
    }

    public void printInfo(Collection<? extends Person> col) {
        for (Iterator<? extends Person> it = col.iterator(); it.hasNext();) {
            Person next = it.next();
            System.out.println(next);
        }
    }

    public void addAll(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        for (Object o : list) {
            System.out.println("Текущий элемент: " + o);
        }
    }

    public void getClassType(List<?> list) {
        Class actual = list.getClass();
        ParameterizedType type = (ParameterizedType) actual.getGenericSuperclass();
        System.out.println("Type of class: " + type);
        Class parameter = (Class) type.getActualTypeArguments()[0];
        System.out.println("Parameter of class: " + parameter);
    }

    public static class FloatList extends ArrayList<Float> {
    }
}
