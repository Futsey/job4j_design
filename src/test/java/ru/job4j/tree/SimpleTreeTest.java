package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleTreeTest {
    private Tree<Integer> tree = new SimpleTree<>(1);

    @Before
    public void init() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
    }

    @Test
    public void when6ElFindLastThen6() {
        assertThat(
                tree.findBy(2).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenChildExistOnLeafThenNotAdd() {
        assertFalse(tree.add(2, 5));
    }

    @Test
    public void whenNodeIsNotBinary() {
        tree.add(1, 11);
        assertFalse(tree.isBinary());
    }

    @Test
    public void whenNodeIsBinary() {
        assertTrue(tree.isBinary());
    }
}