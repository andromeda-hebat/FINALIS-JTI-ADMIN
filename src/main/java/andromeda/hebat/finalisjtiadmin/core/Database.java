package andromeda.hebat.finalisjtiadmin.core;

import java.sql.*;

public class Database {

    private static final String URL = "jdbc:sqlserver://LAPTOP-V9Q55RPI;Database=db_belajar;encrypt=false";
    private static final String USER = "sa";
    private static final String PASSWORD = "sem0gaBISA";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String getUserData() {
        StringBuilder data = new StringBuilder();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT EmployeeID, FirstName, LastName FROM Employees")) {

            while (resultSet.next()) {
                int employeeID = resultSet.getInt("EmployeeID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                data.append("ID: ").append(employeeID).append(", Name: ").append(firstName).append(lastName).append("\n");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }

        return data.toString();
    }
}
