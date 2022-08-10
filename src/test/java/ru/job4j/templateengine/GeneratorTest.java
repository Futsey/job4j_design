package ru.job4j.templateengine;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
class GeneratorTest {

    @Test
    public void whenAdd() {
        Generator generator = new UserGenerator();
        Map<String, String> users = new HashMap<>();
        users.put("Petr Arsentev", "you");
        String expected = "I am a Petr Arsentev, Who are you? ";
        assertThat(expected).isEqualTo(generator.produce("User Generator", users));
    }

    @Test()
    public void whenAddNullKey() {
        Generator generator = new UserGenerator();
        Map<String, String> users = new HashMap<>();
        users.put(null, "you");
        assertThrows(NullPointerException.class, () -> {
            generator.produce("User Generator", users);
        });
    }

    @Test()
    public void whenAddNullValue() {
        Generator generator = new UserGenerator();
        Map<String, String> users = new HashMap<>();
        users.put("Petr Arsentev", null);
        assertThrows(NullPointerException.class, () -> {
            generator.produce("User Generator", users);
        });
    }
}