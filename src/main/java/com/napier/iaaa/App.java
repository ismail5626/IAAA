package com.napier.iaaa;

import java.sql.*;

public class App {
    static final String QUERY1 = "SHOW TABLES";

    // Main method to run the application
    public static void main(String[] args) {
        App app = new App(); // Create an instance of App
        app.printTableNames(); // Call the method to print table names
    }

    // Method to get and print table names from the database
    public void printTableNames() {
        // Load Database driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Could not load SQL driver: " + e.getMessage());
            return; // Exit method if driver fails to load
        }

        // Open a connection
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://db:3306/world", "root", "root");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY1)) {

            // Extract data from result set and print table names
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }
}
