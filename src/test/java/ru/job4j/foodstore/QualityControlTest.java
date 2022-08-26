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
        QualityControl control = new QualityControl();
        store.add(pork);
        control.storeDistributor(control.getTimeDiffInPercent(testDate, expDate), pork);
        assertThat(pork).isEqualTo(store.getProduct(0));
    }

    @Test
    public void whenAddToWarehouse() {
        Store store = new Warehouse();
        QualityControl control = new QualityControl();
        store.add(pork);
        control.storeDistributor(control.getTimeDiffInPercent(testDate, expDate), pork);
        assertThat(pork).isEqualTo(store.getProduct(0));
    }

    @Test
    public void whenAddToTrash() {
        Store store = new Trash();
        QualityControl control = new QualityControl();
        store.add(pork);
        control.storeDistributor(control.getTimeDiffInPercent(testDate, expDate), pork);
        assertThat(pork).isEqualTo(store.getProduct(0));
    }

    @Test
    void whenExpired() {
        Store store = new Shop();
        QualityControl control = new QualityControl();
        expDate = createDate.minusDays(0);
        store.add(pork);
        Assertions.assertThrows(NullPointerException.class, () -> {
            control.storeDistributor(control.getTimeDiffInPercent(testDate, expDate), pork);
        });
    }

    @Test
    public void whenAddDiscount() {
        Store store = new Shop();
        QualityControl control = new QualityControl();
        testDate = createDate.minusDays(1);
        expDate = createDate.plusDays(11);
        store.add(pork);
        control.storeDistributor(control.getTimeDiffInPercent(testDate, expDate), pork);
        assertEquals(pork.getPrice(), 501.5);
    }
}