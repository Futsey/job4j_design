package ru.job4j.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.list.arraylist.List;
import ru.job4j.list.arraylist.SimpleArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

public class SimpleArrayListTest {

    List<Integer> list;

    @Before
    public void initData() {
        list = new SimpleArrayList<>(3);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThenSizeIncrease() {
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void whenGetByCorrectIndex() {
        Assert.assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test
    public void whenAddAndGetByCorrectIndex() {
        list.add(5);
        System.out.println(list);
        list.add(6);
        list.add(7);
        System.out.println(list);
        list.add(7);
        System.out.println(list);
        list.add(7);
        System.out.println(list);
        list.add(7);
        System.out.println(list);
        list.add(7);
        Assert.assertEquals(Integer.valueOf(7), list.get(7));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAndGetByIncorrectIndexThenGetException() {
        list.get(5);
    }

    @Test public void whenExpand() {
        list = new SimpleArrayList<>(2);
        list.add(1);
        assertTrue(Objects.equals(list.get(0), 1));
        list.add(2);
        System.out.println(list);
        assertTrue(Objects.equals(list.get(1), 2));
        list.add(3);
        System.out.println(list);
        System.out.println(list.size());
        assertTrue(Objects.equals(list.get(2), 3));
        list.add(4);
        assertTrue(Objects.equals(list.get(2), 3));
        System.out.println(list.get(3));
    }

    @Test public void whenAddFromZeroPoint() {
        list = new SimpleArrayList<>(0);
        list.add(1);
        list.add(2);
    }

    @Test
    public void whenRemoveThenGetValueAndSizeDecrease() {
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(Integer.valueOf(2), list.remove(1));
        Assert.assertEquals(2, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveLastElementThenGetException() {
        list.remove(2);
        list.get(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveByIncorrectIndexThenGetException() {
        list.remove(5);
    }

    @Test
    public void whenRemoveThenMustNotBeEmpty() {
        list.remove(1);
        Assert.assertEquals(Integer.valueOf(1), list.get(0));
        Assert.assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test
    public void whenAddNullThenMustBeSameBehavior() {
        list = new SimpleArrayList<>(3);
        list.add(null);
        list.add(null);
        Assert.assertEquals(2, list.size());
        Assert.assertNull(list.get(0));
        Assert.assertNull(list.get(1));
    }

    @Test
    public void whenSetThenGetOldValueAndSizeNotChanged() {
        Assert.assertEquals(Integer.valueOf(2), list.set(1, 22));
        Assert.assertEquals(3, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetByIncorrectIndexThenGetException() {
        list.set(5, 22);
    }

    @Test
    public void whenGetIteratorFromEmptyListThenHasNextReturnFalse() {
        list = new SimpleArrayList<>(5);
        Assert.assertFalse(list.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        list = new SimpleArrayList<>(5);
        list.iterator().next();
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        Assert.assertEquals(Integer.valueOf(1), list.iterator().next());
        Assert.assertEquals(Integer.valueOf(1), list.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void whenNoPlaceThenMustIncreaseCapacity() {
        IntStream.range(3, 10).forEach(v -> list.add(v));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(4);
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        list.add(0);
        iterator.next();
    }
}