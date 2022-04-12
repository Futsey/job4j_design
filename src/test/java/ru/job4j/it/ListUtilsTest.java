package ru.job4j.it;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenUseRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Predicate<Integer> filter = el -> el < 4;
        ListUtils.removeIf(input, filter);
        assertThat(input, is(Arrays.asList(4, 5, 6)));
    }

    @Test
    public void whenUseReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Predicate<Integer> filter = el -> el == 4;
        ListUtils.replaceIf(input, filter, 99);
        assertThat(input, is(Arrays.asList(1, 2, 3, 99, 5, 6)));
        filter = el -> el <= 6;
        ListUtils.replaceIf(input, filter, 1);
        assertThat(input, is(Arrays.asList(1, 1, 1, 99, 1, 1)));
    }

    @Test
    public void whenUseRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> second = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, second);
        assertThat(input, is(Arrays.asList(4, 5, 6)));
    }
}