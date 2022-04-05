package ru.job4j.list.stack;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class SimpleStackTest {

    @Test
    public void whenPushThenPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.pushFirstElement(1);
        assertThat(stack.deleteFirstElement(), is(1));
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.pushFirstElement(1);
        stack.deleteFirstElement();
        stack.pushFirstElement(2);
        assertThat(stack.deleteFirstElement(), is(2));
    }

    @Test
    public void whenPushPushThenPollPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.pushFirstElement(1);
        stack.pushFirstElement(2);
        stack.deleteFirstElement();
        assertThat(stack.deleteFirstElement(), is(1));
    }

}