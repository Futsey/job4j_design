package ru.job4j.generics.store;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PetStoreTest {

    @Test
    public void whenAddPetAndFindThenUsernameIsMurzik() {
        PetStore store = new PetStore();
        store.add(new Pet("1", "Murzik"));
        Pet result = store.findById("1");
        assertThat(result.getPetname(), is("Murzik"));
    }

    @Test
    public void whenAddPetAndFindByIdThenUsernameIsPetr() {
        PetStore store = new PetStore();
        store.add(new Pet("1", "Petr", "Murzik"));
        Pet result = store.findUserById("1");
        assertThat(result.findUserById(), is("Petr"));
    }

    @Test
    public void whenAddPetAndDidntFindByIdThenUserIsNull() {
        PetStore store = new PetStore();
        store.add(new Pet("1", "Murzik"));
        Pet result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddPetAndDidntFindUserByIdThenUserIsNull() {
        PetStore store = new PetStore();
        store.add(new Pet("1", "Petr", "Murzik"));
        Pet result = store.findUserById("12");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindPetnameIsMurzik() {
        PetStore store = new PetStore();
        store.add(new Pet("1", "Murzik"));
        store.add(new Pet("1", "Barsik"));
        store.add(new Pet("1", "Vaska"));
        Pet result = store.findById("1");
        assertThat(result.getPetname(), is("Murzik"));
    }

    @Test
    public void whenReplaceThenPetnameIsBarsik() {
        PetStore store = new PetStore();
        store.add(new Pet("1", "Murzik"));
        store.replace("1", new Pet("1", "Barsik"));
        Pet result = store.findById("1");
        assertThat(result.getPetname(), is("Barsik"));
    }

    @Test
    public void whenNoReplacePetThenNoChangePetname() {
        PetStore store = new PetStore();
        store.add(new Pet("1", "Murzik"));
        store.replace("10", new Pet("10", "Barsik"));
        Pet result = store.findById("1");
        assertThat(result.getPetname(), is("Murzik"));
    }

    @Test
    public void whenDeletePetThenPetIsNull() {
        PetStore store = new PetStore();
        store.add(new Pet("1", "Murzik"));
        store.delete("1");
        Pet result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeletePetThenPetnameIsMurzik() {
        PetStore store = new PetStore();
        store.add(new Pet("1", "Murzik"));
        store.delete("10");
        Pet result = store.findById("1");
        assertThat(result.getPetname(), is("Murzik"));
    }
}