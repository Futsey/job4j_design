package ru.job4j.srp.kiss;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.kiss.MaxMin;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MaxMinTest {

    private List<Integer> value = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    private Comparator<Integer> comparator = Comparator.naturalOrder();
    private MaxMin maxMin = new MaxMin();

    @Test
    void maxTest() {
        assertEquals(12, maxMin.max(value, comparator), "Should return max");
    }

    @Test
    void minTest() {
        assertEquals(1, maxMin.min(value, comparator), "Should return min");
    }

    @Test
    void expectedNull() {
        List<Integer> nullValue = List.of();
        Assertions.assertNull(maxMin.isEmpty(nullValue), "Should return null");
    }
}