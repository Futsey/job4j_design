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
    Store warehouse = new Warehouse();
    Store shop = new Shop();
    Store trash = new Trash();
    QualityControl qc = new QualityControl(List.of(warehouse, shop, trash));

    @Test
    public void whenAddToShop() {
        Food pork = new Meat("pork", testDate, expDate, 590D, 0);
        qc.addFood(pork);
        assertThat(shop.getAllProducts()).containsAll(List.of(pork));
    }

    @Test
    public void whenAddToWarehouse() {
        testDate = createDate.minusDays(1);
        expDate = createDate.plusDays(14);
        Food pork = new Meat("pork", testDate, expDate, 590D, 0);
        qc.addFood(pork);
        assertThat(warehouse.getAllProducts()).containsAll(List.of(pork));
    }

    @Test
    public void whenAddToTrash() {
        testDate = createDate.minusDays(14);
        expDate = createDate.minusDays(1);
        Food pork = new Meat("pork", testDate, expDate, 590D, 0);
        qc.addFood(pork);
        assertThat(trash.getAllProducts()).containsAll(List.of(pork));
    }

    @Test
    public void whenAddDiscount() {
        testDate = createDate.minusDays(5);
        expDate = createDate.plusDays(2);
        Food pork = new Meat("Pork", testDate, expDate, 590D, 0);
        qc.addFood(pork);
        assertEquals(pork.getPrice(), 501.5);
    }

    @Test
    public void whenSortByStorages() {
        testDate = createDate.minusDays(1);
        expDate = createDate.plusDays(2);
        Food shopPork = new Meat("shopPork", testDate, expDate, 590D, 0);
        Food shopPork2 = new Meat("shopPork2", testDate, expDate, 590D, 0);
        qc.addFood(shopPork);
        qc.addFood(shopPork2);
        testDate = createDate.minusDays(1);
        expDate = createDate.plusDays(14);
        Food warehousePork = new Meat("warehousePork", testDate, expDate, 590D, 0);
        qc.addFood(warehousePork);
        testDate = createDate.minusDays(6);
        expDate = createDate.minusDays(1);
        Food trashPork = new Meat("trashPork", testDate, expDate, 590D, 0);
        qc.addFood(trashPork);
        assertThat(shop.getAllProducts()).containsAll(List.of(shopPork, shopPork2));
        assertThat(warehouse.getAllProducts()).containsAll(List.of(warehousePork));
        assertThat(trash.getAllProducts()).containsAll(List.of(trashPork));
    }
}