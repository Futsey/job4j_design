package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void FigureExist() {
        Box box = new Box(8, 10);
        assertTrue(box.isExist());
    }

    @Test
    void FigureDoesNotExist() {
        Box box = new Box(-3, 0);
        assertFalse(box.isExist());
    }

    @Test
    void getAreaOfCube() {
        Box box = new Box(8, 10);
        double expectedArea = 600;
        assertThat(expectedArea).isEqualTo(box.getArea());
    }

    @Test
    void getAreaOfTetrahedron() {
        Box box = new Box(4, 12);
        double expectedArea = 249.4153162899183;
        assertThat(expectedArea).isEqualTo(box.getArea());
    }
}