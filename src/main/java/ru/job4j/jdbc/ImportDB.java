package ru.job4j.jdbc;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    private void createTable(Connection cnt, String tableName) throws SQLException {
        try (Statement cs = cnt.createStatement()) {
            cs.execute(String.format(
                    "Create table if not exists %s(%s, %s, %s);",
                    tableName,
                    "id serial primary key",
                    "name varchar (200)",
                    "email varchar (200)"));
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines()
                    .map(m -> m.split(";"))
                    .filter(filter -> {
                        String regex = "^\\S+@\\S+\\.\\S+$";
                        Matcher matcher = Pattern.compile(regex).matcher(filter[1]);
                        if (filter.length != 2 || filter[0].isEmpty() || filter[1].isEmpty() || !matcher.find()) {
                            throw new IllegalArgumentException("Check pattern matching");
                        }
                        return true;
                    })
                    .forEach(f -> users.add(new User(f[0], f[1])));
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            try {
                createTable(cnt, "spammer");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into spammer(name, email) values(?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("spammer.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}
