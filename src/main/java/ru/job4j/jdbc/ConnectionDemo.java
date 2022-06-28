package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/electorate";
        String login = "postgres";
        String password = "password";
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }

        String path = "./data/jdbc.properties";
        Config config = new Config(path);
        config.load();
        try (Connection configConnection = DriverManager.getConnection(
                config.value("url"),
                config.value("login"),
                config.value("password"))) {
            DatabaseMetaData metaData = configConnection.getMetaData();
            System.out.println("ConfigConnection user: " + metaData.getUserName());
            System.out.println("ConfigConnection url: " + metaData.getURL());
        }
    }
}