package ru.job4j.set;

import org.junit.Test;
import ru.job4j.generics.store.Pet;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenDoubleTypePetThanFalse() {
        Set<Pet> set = new SimpleSet<>();
        Pet heks = new Pet("1", "Heks");
        Pet geks = new Pet("2", "Heks");
        assertTrue(set.add(heks));
        assertTrue(set.add(geks));
        assertFalse(set.add(geks));
        assertFalse(set.add(heks));
    }
}