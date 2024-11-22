package andromeda.hebat.finalisjtiadmin.core;

import java.sql.*;

public class Database {

    private static final String SERVER_NAME = "LAPTOP-V9Q55RPI";
    private static final String DB_NAME = "db_temp_finalis_jti";
    private static final String USER = "sa";
    private static final String PASSWORD = "sem0gaBISA";

    public static Connection getConnection() {
        final String URL = "jdbc:sqlserver://"+SERVER_NAME+";Database="+DB_NAME+";encrypt=false";

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
