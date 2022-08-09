package ru.job4j.kiss;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MaxMinTest {

    private List<Integer> value = List.of(1,2,3,4,5,6,7,8,9,10,11,12);
    private Comparator<Integer> comparator = Comparator.naturalOrder();
    private MaxMin maxMin = new MaxMin();

    @Test
    void maxTest() {
        assertEquals(12, maxMin.max(value, comparator));
    }

    @Test
    void minTest() {
        assertEquals(1, maxMin.min(value, comparator));
    }

    @Test
    void testExpectedExceptionWithParentType() {
        List<Integer> nullValue = List.of();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            maxMin.max(nullValue, comparator);
        });
    }
}