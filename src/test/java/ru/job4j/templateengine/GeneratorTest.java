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
        users.put("name", "Petr Arsentev");
        users.put("subject", "you");
        String expected = "I am a Petr Arsentev, Who are you? ";
        assertThat(expected).isEqualTo(generator.produce("I am a ${name}, Who are ${subject}? ", users));
    }

    @Test()
    public void whenAddOnlyName() {
        Generator generator = new UserGenerator();
        Map<String, String> users = new HashMap<>();
        users.put("name", "Petr Arsentev");
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce("I am a ${name}, Who are ${subject}? ", users);
        });
    }

    @Test()
    public void whenAddOnlySubject() {
        Generator generator = new UserGenerator();
        Map<String, String> users = new HashMap<>();
        users.put("subject", "you");
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce("I am a ${name}, Who are ${subject}? ", users);
        });
    }

    @Test()
    public void whenAddNullValueInSubject() {
        Generator generator = new UserGenerator();
        Map<String, String> users = new HashMap<>();
        users.put("name", "Petr Arsentev");
        users.put("subject", null);
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce("I am a ${name}, Who are ${subject}? ", users);
        });
    }

    @Test()
    public void whenAddNullValueInName() {
        Generator generator = new UserGenerator();
        Map<String, String> users = new HashMap<>();
        users.put("name", null);
        users.put("subject", "you");
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce("I am a ${name}, Who are ${subject}? ", users);
        });
    }

    @Test()
    public void whenAddNullKeyInSubject() {
        Generator generator = new UserGenerator();
        Map<String, String> users = new HashMap<>();
        users.put("name", "Petr Arsentev");
        users.put(null, "you");
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce("I am a ${name}, Who are ${subject}? ", users);
        });
    }

    @Test()
    public void whenAddNullKeyInName() {
        Generator generator = new UserGenerator();
        Map<String, String> users = new HashMap<>();
        users.put(null, "Petr Arsentev");
        users.put("subject", "you");
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce("I am a ${name}, Who are ${subject}? ", users);
        });
    }
}