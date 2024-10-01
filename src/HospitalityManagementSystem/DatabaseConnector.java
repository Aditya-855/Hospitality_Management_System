package HospitalityManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DatabaseConnector {

        // Database URL, username, and password
        private static final String URL = "jdbc:mysql://localhost:3306/HospitalityManagement";
        private static final String USER = "root"; // Replace with your MySQL username
        private static final String PASSWORD = "ADITYA@7060"; // Replace with your MySQL password

        // Method to establish the connection
        public static Connection getConnection() {
            Connection connection = null;
            try {
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establish the connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connection Established successfully!");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

        // Method to close the connection
        public static void closeConnection(Connection connection) {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection closed successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
