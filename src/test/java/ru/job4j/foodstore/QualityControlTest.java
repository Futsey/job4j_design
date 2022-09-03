package ru.job4j.foodstore;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QualityControlTest {

    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime testDate = createDate.minusDays(1);
    private LocalDateTime expDate = createDate.plusDays(4);
    private Food pork = new Meat("Pork", testDate, expDate, 590D, 0);

    @Test
    public void whenAddToShop() {
        Store store = new Shop();
        store.add(pork);
        assertThat(store.getAllProducts()).containsAll(List.of(pork));
    }

    @Test
    public void whenAddToWarehouse() {
        Store store = new Warehouse();
        testDate = createDate.minusDays(1);
        expDate = createDate.plusDays(12);
        Food pork = new Meat("Pork", testDate, expDate, 590D, 0);
        store.add(pork);
        assertThat(store.getAllProducts()).containsAll(List.of(pork));
    }

    @Test
    public void whenAddToTrash() {
        Store store = new Trash();
        testDate = createDate.plusDays(2);
        expDate = createDate.plusDays(1);
        Food pork = new Meat("Pork", testDate, expDate, 590D, 0);
        store.add(pork);
        assertThat(store.getAllProducts()).containsAll(List.of(pork));
    }

    /*
    @Test
    public void whenAddDiscount() {
        Store store = new Shop();
        testDate = createDate.minusDays(5);
        expDate = createDate.plusDays(2);
        Food pork = new Meat("Pork", testDate, expDate, 590D, 0);
        store.add(pork);
        assertEquals(pork.getPrice(), 501.5);
    }
     */
}