package com.napier.iaaa;


import java.sql.*;

public class App {
    // Database connection details
    static final String DB_URL = "jdbc:mysql://db:3306/world";
    static final String USER = "root";
    static final String PASSWORD = "root";

    // Main method to run the application
    public static void main(String[] args) {
        App app = new App(); // Create an instance of App

        // Print a welcoming message
        System.out.println("Welcome to the Country Reports Application!\n");

        // Generate and print all reports with clear separation
        System.out.println("Generating reports...\n");

        // Report 1: All countries by population
        app.getAllCountriesByPopulation(); // Generate and print report
        System.out.println(); // Add spacing between reports

        // Report 2: Countries in a specific continent (e.g., Asia)
        app.getCountriesByContinent("Asia"); // Generate and print report
        System.out.println(); // Add spacing between reports

        // Report 3: Countries in a specific region (e.g., Europe)
        app.getCountriesByRegion("Europe"); // Generate and print report
        System.out.println(); // Add spacing between reports

        // Report 4: Top N populated countries globally
        app.getTopNPopulatedCountries(5); // Generate and print report
        System.out.println(); // Add spacing between reports

        // Report 5: Top N populated countries in a specific continent (e.g., Asia)
        app.getTopNPopulatedCountriesInContinent("Asia", 5); // Generate and print report
        System.out.println(); // Add spacing after the last report
    }

    // Method to get all countries by population
    public void getAllCountriesByPopulation() {
        System.out.println("=== All Countries by Population ==="); // Section header
        String query = "SELECT Name, Population FROM country ORDER BY Population DESC"; // SQL query
        executeQuery(query); // Execute query with no parameters
    }

    // Method to get countries by continent
    public void getCountriesByContinent(String continent) {
        System.out.println("=== Countries in Continent: " + continent + " ==="); // Section header
        String query = "SELECT Name, Population FROM country WHERE Continent = ? ORDER BY Population DESC"; // SQL query
        executeQuery(query, continent); // Execute query with continent parameter
    }

    // Method to get countries by region
    public void getCountriesByRegion(String region) {
        System.out.println("=== Countries in Region: " + region + " ==="); // Section header
        String query = "SELECT Name, Population FROM country WHERE Region = ? ORDER BY Population DESC"; // SQL query
        executeQuery(query, region); // Execute query with region parameter
    }

    // Method to get top N populated countries
    public void getTopNPopulatedCountries(int N) {
        System.out.println("=== Top " + N + " Populated Countries ==="); // Section header
        String query = "SELECT Name, Population FROM country ORDER BY Population DESC LIMIT " + N; // SQL query with limit
        executeQuery(query); // Execute query without parameters
    }

    // Method to get top N populated countries in a continent
    public void getTopNPopulatedCountriesInContinent(String continent, int N) {
        System.out.println("=== Top " + N + " Populated Countries in Continent: " + continent + " ==="); // Section header
        String query = "SELECT Name, Population FROM country WHERE Continent = ? ORDER BY Population DESC LIMIT " + N; // SQL query with parameters
        executeQuery(query, continent); // Execute query with continent parameter
    }

    // Private method to execute SQL queries
    private void executeQuery(String query, String... params) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Could not load SQL driver: " + e.getMessage()); // Handle driver loading error
            return; // Exit method if driver fails to load
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD); // Establish connection
             PreparedStatement pstmt = conn.prepareStatement(query)) { // Prepare SQL statement

            // Set parameters for prepared statement if any
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setString(i + 1, params[i]); // Set each parameter
                }
            }

            // Execute the query and retrieve results
            ResultSet rs = pstmt.executeQuery(); // Execute the query
            while (rs.next()) { // Iterate through the result set
                // Print country name and population
                System.out.printf("%s: %d%n", rs.getString(1), rs.getInt(2));
            }

        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage()); // Handle SQL errors
        }
    }
}