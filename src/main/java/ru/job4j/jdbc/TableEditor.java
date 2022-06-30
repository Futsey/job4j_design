package ru.job4j.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;
    private static final Logger LOG = LogManager.getLogger(TableEditor.class.getName());

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

    private void initStatement(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("TableEditor: initStatement(): Table is missing: " + e);
        }
    }

    public void createTable(String tableName) throws Exception {
            String sql = String.format(
                    "Create table if not exists %s(%s);",
                    tableName,
                    "id serial primary key");
            initStatement(sql);
            System.out.println(getTableScheme(connection, "demo_table"));
    }

    public void dropTable(String tableName) {
        String sql = String.format("Drop table %s;", tableName);
        initStatement(sql);
        System.out.println(tableName + ": " + " table dropped");
    }

    public void addColumn(String tableName, String columnName, String type, String length) throws Exception {
        String sql = String.format("Alter table %s add column %s %s %s;", tableName, columnName, type, length);
        initStatement(sql);
        System.out.println(getTableScheme(connection, "demo_table"));
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("Alter table %s drop column %s;", tableName, columnName);
        initStatement(sql);
        System.out.println(getTableScheme(connection, "demo_table"));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format("Alter table %s rename column %s to %s;", tableName, columnName, newColumnName);
        initStatement(sql);
        System.out.println(getTableScheme(connection, "demo_table"));

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
        } catch (Exception e) {
            LOG.error("Exception in:" + e);
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("tableEditorProperties.properties")) {
            config.load(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("demo_table");
            tableEditor.addColumn("demo_table", "new_column", "varchar", "(200)");
            tableEditor.renameColumn("demo_table", "new_column", "altercolumn");
            tableEditor.dropColumn("demo_table", "altercolumn");
            tableEditor.dropTable("demo_table");
        }
        System.out.println("Program DONE!");
    }
}
