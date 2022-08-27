package ru.job4j.foodstore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

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
        assertThat(pork).isEqualTo(store.getProduct(0));
    }

    @Test
    public void whenAddToWarehouse() {
        Store store = new Warehouse();
        store.add(pork);
        assertThat(pork).isEqualTo(store.getProduct(0));
    }

    @Test
    public void whenAddToTrash() {
        Store store = new Trash();
        store.add(pork);
        assertThat(pork).isEqualTo(store.getProduct(0));
    }

    @Test
    public void whenAddDiscount() {
        LocalDateTime testCreateDate = createDate.minusDays(15);
        Store store = new Shop();
        QualityControl control = new QualityControl();
        Food unbelievablePork = new Meat("Pork", testCreateDate, expDate, 590D, 0);
        store.add(pork);
        control.storeDistributor(control.getTimeDiffInPercent(unbelievablePork), unbelievablePork);
        assertEquals(unbelievablePork.getPrice(), 501.5);
    }
}