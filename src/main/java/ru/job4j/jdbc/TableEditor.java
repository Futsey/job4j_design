package ru.job4j.jdbc;

import java.io.FileOutputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private static Properties properties = new Properties();

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"));
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println(metaData.getUserName());
        System.out.println(metaData.getURL());
    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "Create table if not exists %s(%s, %s);",
                    tableName,
                    "id serial primary key",
                    "name varchar(255)"
            );
            statement.execute(sql);
            System.out.println(getTableScheme(connection, "demo_table"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "Drop table %s;", tableName
            );
            statement.execute(sql);
            System.out.println(tableName + ": " + " table dropped");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type, String length) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "Alter table %s add column %s %s %s;", tableName, columnName, type, length
            );
            statement.execute(sql);
            System.out.println(getTableScheme(connection, "demo_table"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "Alter table %s drop column %s;", tableName, columnName
            );
            statement.execute(sql);
            System.out.println(getTableScheme(connection, "demo_table"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "Alter table %s rename column %s to %s;", tableName, columnName, newColumnName
            );
            statement.execute(sql);
            System.out.println(getTableScheme(connection, "demo_table"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setProperties(String propName,
                               String keyUrl, String valueUrl,
                               String keyLogin, String valueLogin,
                               String keyPassword, String valuePassword) {
        properties.setProperty(keyUrl, valueUrl);
        properties.setProperty(keyLogin, valueLogin);
        properties.setProperty(keyPassword, valuePassword);
        try (FileOutputStream fout = new FileOutputStream(propName)) {
            properties.store(fout, propName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("Program START!");
        String propName = "tableEditorProperties.properties";
        String keyUrl = "url";
        String valueUrl = "jdbc:postgresql://localhost:5432/electorate";
        String keyLogin = "login";
        String valueLogin = "postgres";
        String keyPassword = "password";
        String valuePassword = "password";
        setProperties(propName,
                keyUrl, valueUrl,
                keyLogin, valueLogin,
                keyPassword, valuePassword);

        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.createTable("demo_table");
        tableEditor.addColumn("demo_table", "new_column", "varchar", "(200)");
        tableEditor.renameColumn("demo_table", "new_column", "altercolumn");
        tableEditor.dropColumn("demo_table", "altercolumn");
        tableEditor.dropTable("demo_table");
        try {
            tableEditor.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Program DONE!");
    }
}
