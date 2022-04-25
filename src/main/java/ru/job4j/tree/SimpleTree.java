package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * Класс является собственной реализацией
 * @see java.util.TreeSet (без балансировки и сортировки)
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class SimpleTree<E> implements Tree<E> {

    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод принимает значение корня и потомка
     * @param parent,child
     * и, в случае присутствия элемента в дереве и пустой ссылкой в поле child, добавляет новый элемент
     * Для безопасного доступа к элементу используем обертку от NPE
     * @see Optional
     * @return true\false (добавил\не добавил)
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> findPar = findBy(parent);
        if (findPar.isPresent() && findBy(child).isEmpty()) {
            rsl = findPar.get().children.add(new Node<>(child));
        }
        return rsl;
    }

    /**
     * Метод принимает значение
     * @param value
     * Создает связный список на основе очереди, куда после проверки перекладывает все элементы
     * и сравнивает их на предмет соответствия с value
     * Для безопасного доступа к элементу используем обертку от NPE
     * @see Optional
     * @return искомое значение или пустой Optional
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
