package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс является собственной реализацией
 * @see java.util.HashMap (но без разрешений коллизий и без связного списка внутри бакетов)
 * @author ANDREW PETRUSHIN (JOB4J Project)
 * @version 1.0
 */
public class SimpleMap<K, V> implements Map<K, V> {

    /**
     * данные храним в массиве с универсальной параметризацией <K, V>[]
     * поле LOAD_FACTOR является ограничителем для добавления новых элементов в массив
     * и драйвером для расширения массива
     * поле capacity регламентирует размер массива
     * поле count является счетчиком операций
     * поле modCount является счетчиком по добавлению\удалению элементов
     */
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Метод принимает новую пару
     * @param key,value ключ\значение
     * осуществляет проверку размерности массива
     * и добавляет новые элемент в первую пустую ячейку
     * @return true\false (элемент добавлен\не добавлен)
     */
    @Override
    public boolean put(K key, V value) {
        if (capacity * LOAD_FACTOR <= count) {
            expand();
        }
        boolean rsl = false;
        if (key != null) {
            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) {
                    table[i] = new MapEntry<K, V>(key, value);
                    rsl = true;
                    count++;
                    modCount++;
                    break;
                }
            }
        }
        return rsl;
    }

    /**
     * Метод удваивает объем массива и переносит все элементы в новый массив
     */
    private void expand() {
        capacity *= 2;
        int count = 0;
        MapEntry<K, V>[] table = new MapEntry[capacity];
        for (MapEntry<K, V> el : this.table) {
                table[count] = el;
                count++;
        }
        this.table = table;
    }

    /**
     * Метод возвращает значение элемента по ключу
     */
    @Override
    public V get(K key) {
        V rsl = null;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null
                    && ((table[i].key != null
                    && key.equals(table[i].key))
                    || (table[i].key == null && key == null))) {
                rsl = table[i].value;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод вудаляет ключ элемента
     */
    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null
                    && ((table[i].key != null
                    && key.equals(table[i].key))
                    || (table[i].key == null && key == null))) {
                table[i].key = null;
                rsl = true;
                count++;
                modCount--;
                break;
            }
        }
        return rsl;
    }

    /**
     * TODO расширить функционал метода через цикл
     * Метод вычисляет хэшкод элемента
     */
    public int hashCode(String key) {
        int rsl = key.charAt(0);
        if (key == null) {
            rsl = key.charAt(0);
        } else {
            char[] chars = key.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                 rsl = key.charAt(i++);
            }
        }
        return 31 * rsl;
    }

    /**
     * Метод принимает хэшкод объекта
     * @param hashCode
     * и высчитывает номер бакета, куда будет добавлен элемент
     */
    private int hash(int hashCode) {
        return hashCode % capacity;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int point = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length && table[point] == null) {
                    point++;
                }
                return point < table.length && table[point] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "MapEntry{"
                    + "key=" + key
                    + ", value=" + value
                    + '}';
        }
    }

    @Override
    public String toString() {
        return "SimpleMap{"
                + "capacity=" + capacity
                + ", count=" + count
                + ", modCount=" + modCount
                + ", table=" + Arrays.toString(table)
                + '}';
    }
}
