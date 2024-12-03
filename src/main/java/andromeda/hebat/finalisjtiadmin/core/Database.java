package andromeda.hebat.finalisjtiadmin.core;

import java.sql.*;

import andromeda.hebat.finalisjtiadmin.helper.EnvLoader;

public class Database {

    static {
        EnvLoader.loadEnv(".env");
    }

    private static final String DB_SERVER = System.getProperty("DB_SERVER");
    private static final String DB_NAME = System.getProperty("DB_NAME");
    private static final String DB_USER = System.getProperty("DB_USER");
    private static final String DB_PASSWORD = System.getProperty("DB_PASSWORD");

    public static Connection getConnection() {
        final String URL = "jdbc:sqlserver://"+DB_SERVER+";Database="+DB_NAME+";encrypt=false";

        try {
            return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
