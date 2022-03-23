package ru.job4j.generics.store;

public class Pet extends Base {

    private final String petname;

    public Pet(String id, String name) {
        super(id);
        this.petname = name;
    }

    public Pet(String id, String name, String userName) {
        super(id);
        this.petname = name;
    }

    public String getPetname() {
        return petname;
    }

    public String findUserById() {
        return getPetname();
    }
}
