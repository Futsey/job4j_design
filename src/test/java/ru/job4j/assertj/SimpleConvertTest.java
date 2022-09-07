package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    String[] arr = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> arrList = simpleConvert.toList(arr);
        assertThat(arrList).hasSize(10)
                .contains("Five")
                .contains("Six", Index.atIndex(5))
                .containsAnyOf("Nine", "Ten")
                .containsSequence("One", "Two", "Three", "Four", "Five")
                .doesNotContain("Eleven");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet(arr);
        assertThat(set).hasSize(10)
                .contains("Five")
                .containsExactlyInAnyOrder(
                        "One", "Eight", "Three", "Four", "Five", "Six", "Seven", "Two", "Nine", "Ten")
                .doesNotContain("Eleven");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap(arr);
        assertThat(map).hasSize(10)
                .containsOnlyKeys(arr)
                .containsKey("Five")
                .containsEntry("Three", 2)
                .containsValues(6, 7)
                .doesNotContainKey("Eleven")
                .doesNotContainKeys("Eleven", "Twelve")
                .doesNotContainEntry("Twelve", 11)
                .isNotNull();
    }
}