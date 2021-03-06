package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void whenPairWithIllegal() {
        thrown.expect(IllegalArgumentException.class);
        String path = "./data/pair_with_Illegal.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairWithoutSplitSymbol() {
        thrown.expect(IllegalArgumentException.class);
        String path = "./data/pair_without_split.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairWithoutValues() {
        thrown.expect(IllegalArgumentException.class);
        String path = "./data/pair_without_values.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairMultiSplitSymbol() {
        String path = "./data/pair_multi_split.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.=hibernate.dialect.=PostgreSQLDialect"));
    }

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenPairWithNull() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("#hibernate.dialect"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithWhitespaces() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }




}