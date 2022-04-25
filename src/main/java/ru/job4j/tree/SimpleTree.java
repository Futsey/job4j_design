package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

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
     * передает для утверждения функцию сравнения значения со значением в дереве
     * @return true\false (нашел\не нашел)
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> pred = el -> el.value.equals(value);
        return findByPredicate(pred);
    }

    /**
     * Метод передает для утверждения функцию сравнения количества связанных нодов с корнем дерева
     * @return true\false (больше\ пусто Optional)
     */
    public boolean isBinary() {
        Predicate<Node<E>> pred = i -> i.children.size() > 2;
        return findByPredicate(pred).isEmpty();
    }

    /**
     * Метод принимает значение
     * @param condition
     * производит проверку по переданному условию и возвращает булево значение
     * Для безопасного доступа к элементу используем обертку от NPE
     * @see Optional
     * @return true\false (проверка пройдена\не пройдена)
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
