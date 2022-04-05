package ru.job4j.queue;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.NoSuchElementException;

public class SimpleQueueTest {

    @Test
    public void whenPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.pushToEnd(1);
        int rsl = queue.pollThanDelete();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.pushToEnd(1);
        queue.pushToEnd(2);
        int rsl = queue.pollThanDelete();
        assertThat(rsl, is(1));
    }

    @Test
    public void when2PushPollPushPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.pushToEnd(1);
        queue.pollThanDelete();
        queue.pushToEnd(2);
        int rsl = queue.pollThanDelete();
        assertThat(rsl, is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyPoll() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.pollThanDelete();
    }
    @Test
    public void whenPushPushPollAndPush() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.pushToEnd(1);
        queue.pushToEnd(2);
        queue.pollThanDelete();
        queue.pushToEnd(3);
        assertThat(queue.pollThanDelete(), is(2));
    }

}