package ru.job4j.queue;

import ru.job4j.list.stack.SimpleStack;

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
     * для переброски данных из одного в другой в соответствии с принципом FIFO
     */
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * метод передает все данные из одного контейнера в другой
     * затем удаляет первый элемент в переданной коллекции (последний в передаваемой)
     * и отдает оставшиеся элементы в изначальной очередности в первый контейнер
     * @return старое значение (значение удаленного элемента)
     */
    public T pollThanDelete() {
        T rsl;
        while (in.hasNext()) {
            out.pushFirstElement(in.deleteFirstElement());
        }
        rsl = out.deleteFirstElement();
        while (out.hasNext()) {
            in.pushFirstElement(out.deleteFirstElement());
        }
        return rsl;
    }

    /**
     * метод удаляет последний элемент в контейнере
     */
    public void pushToEnd(T value) {
        in.pushFirstElement(value);
    }
}
