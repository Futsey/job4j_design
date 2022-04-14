package ru.job4j.map;

import java.util.*;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar date = new GregorianCalendar(2002, Calendar.APRIL, 14);
        User kolya = new User("Nikolay", 2, date);
        User kolyaTwin = new User("Nikolay", 2, date);

        Map<User, Object> map = new HashMap<>();
        map.put(kolya, new Object());
        map.put(kolyaTwin, new Object());
        System.out.println("User main() kolya:" + map.get(kolya));
        System.out.println("User main() kolyaTwin:" + map.get(kolya));
        System.out.println("User main() map:" + map);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }
}
