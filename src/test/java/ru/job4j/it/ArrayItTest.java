package ru.job4j.it;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ArrayItTest {

    /**
     * JUnit 4 test

    @Deprecated
    @Test
    public void whenMultiCallhasNextThenTrue() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

     * JUnit 4 test
    @Deprecated
    @Test
    public void whenReadSequence() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }

     * JUnit 4 test

    @Deprecated
    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        ArrayIt it = new ArrayIt(
                new int[] {}
        );
        it.next();
    }
     */

    @Test
    void whenMultiCallHasNextThenTrueJUnit5() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        boolean rsl = it.hasNext();
        assertThat(rsl).isTrue();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    void whenReadSequenceJUnit5() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(3);
    }
}