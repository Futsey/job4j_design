package ru.job4j.map;

import java.util.*;

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
        boolean rsl = false;
        if (capacity * LOAD_FACTOR <= count) {
            expand();
        }
        int i = indexFor(hash((key == null) ? 0 : key.hashCode()));
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode ^ (hashCode >>> 16));
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] table = new MapEntry[capacity];
        for (MapEntry<K, V> el : this.table) {
            int i = indexFor(hash((el.key == null) ? 0 : el.key.hashCode()));
            if (table[i] == null) {
                table[i] = el;
            }
        }
        this.table = table;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int i = indexFor(hash((key == null) ? 0 : key.hashCode()));
        if (table[i] != null && Objects.equals(table[i].key, key)) {
            rsl = table[i].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int hashCode = (key == null) ? 0 : key.hashCode();
        int i = indexFor(hash(hashCode));
        if (table[i] != null && Objects.equals(table[i].key, key)) {
            table[i] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                while (point < capacity && table[point] == null) {
                    point++;
                }
                return point < capacity && table[point] != null;
            }

            @Override
            public K next() {
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
    }
}
