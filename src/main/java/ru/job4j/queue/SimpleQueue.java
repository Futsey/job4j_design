package ru.job4j.queue;

import ru.job4j.list.stack.SimpleStack;

import java.util.NoSuchElementException;

/**
 * Класс является собственной реализацией очереди
 * @see java.util.Queue
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class SimpleQueue<T> {
    /**
     * все данные содержим в двух контейнерах собственной реализации
     * @see SimpleStack
     * для переброски данных из одного в другой в соответствии с принципом LIFO
     */
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inStack;
    private int outStack;

    /**
     * метод передает все данные из одного контейнера в другой
     * инкрементирует счетчик принимающего данные стэка и декрементирует счетчик стэка-донора
     * превентивно уменьшаем счетчик принимающего стэка, потому как далее из него удаляем первый элемент
     * @return старое значение (значение удаленного элемента)
     */

    public boolean isEmpty() {
        if (inStack == 0 && outStack == 0) {
            throw new NoSuchElementException();
        }
        return outStack == 0;
    }

    public T poll() {
        if (isEmpty()) {
            while (inStack > 0) {
                out.push(in.pop());
                inStack--;
                outStack++;
            }
        }
        outStack--;
        return out.pop();
    }

    /**
     * метод добавляет элемент в самое начало контейнера
     */
    public void push(T value) {
        in.push(value);
        inStack++;
    }
}
