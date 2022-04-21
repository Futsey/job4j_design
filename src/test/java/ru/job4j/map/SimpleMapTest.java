package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class SimpleMapTest {
    private SimpleMap<String, String> map = new SimpleMap<>();

    @Before
    public void init() {
        map.put("1", "val1");
        map.put("2", "val2");
        map.put("3", "val3");
        map.put("4", "val4");
        map.put("5", "val5");
    }

    @Test
    public void whenSinglePutAndSingleGet() {
        map.put("6", "val6");
        assertThat(map.get("6"), is("val6"));
    }

    @Test
    public void whenNullPut() {
        map.put(null, "val6");
        assertThat(map.get("6"), nullValue());
    }

    @Test
    public void whenMultiPutAndMultiGet() {
        assertThat(map.get("2"), is("val2"));
        assertThat(map.get("3"), is("val3"));
        assertThat(map.get("4"), is("val4"));
        assertThat(map.get("5"), is("val5"));
    }

    @Test
    public void whenRemove() {
        map.remove("2");
        assertThat(map.get("2"), nullValue());
    }

    @Test
    public void whenPutThanRemove() {
        map.put("6", "val6");
        assertThat(map.get("6"), is("val6"));
        map.remove("6");
        assertThat(map.get("6"), nullValue());
    }

    @Test
    public void testHashCodeFalse() {
        long a = map.get("2").hashCode();
        long b = map.get("3").hashCode();
        assertNotEquals(a, b);
    }

    @Test
    public void testHashCodeTrue() {
        assertEquals(map.get("5").hashCode(), map.get("5").hashCode());
    }
}