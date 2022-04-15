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
        Calendar date = new GregorianCalendar(2002, 4, 14, 11, 24, 56);
        User kolya = new User("Nikolay", 2, date);
        User kolyaTwin = new User("Nikolay", 2, date);

        Map<User, Object> map = new HashMap<>();
        map.put(kolya, new Object());
        map.put(kolyaTwin, new Object());
        System.out.println("User main() kolya:" + map.get(kolya));
        System.out.println("User main() kolyaTwin:" + map.get(kolya));
        System.out.println("User main() map:" + map);
        System.out.println("User main() mapSize:" + map.size());
        System.out.println("User main() kolyaHash:" + kolya.hashCode() + " kolyaTwinHash: " + kolyaTwin.hashCode());
        System.out.println("User main() equals:" + kolya.equals(kolyaTwin));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
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