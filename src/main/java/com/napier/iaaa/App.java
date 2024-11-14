package com.napier.iaaa;

import java.sql.*;

public class App {
    // Database connection details
    static final String DB_URL = "jdbc:mysql://localhost:3306/world";
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
        System.out.println(app.getAllCountriesByPopulation()); // Generate and print report
        System.out.println(); // Add spacing between reports

        // Report 2: Countries in a specific continent (e.g., Asia)
        System.out.println(app.getCountriesByContinent("Asia")); // Generate and print report
        System.out.println(); // Add spacing between reports

        // Report 3: Countries in a specific region (e.g., Europe)
        System.out.println(app.getCountriesByRegion("Middle East")); // Generate and print report
        System.out.println(); // Add spacing between reports

        // Report 4: Top N populated countries globally
        System.out.println(app.getTopNPopulatedCountries(5)); // Generate and print report
        System.out.println(); // Add spacing between reports

        // Report 5: Top N populated countries in a specific continent (e.g., Asia)
        System.out.println(app.getTopNPopulatedCountriesInContinent("Asia", 5)); // Generate and print report
        System.out.println(); // Add spacing after the last report

        //Report 5: Top N populated countries by specific reigion (e.g., Middle East)
        System.out.println(app.getTopNPopulatedCountriesInRegion("Middle East", 5)); // Generate and print report
        System.out.println(); // Add spacing after the last report
    }

    // Method to get all countries by population
    public String getAllCountriesByPopulation() {
        StringBuilder report = new StringBuilder();
        report.append("=== All Countries by Largest Population to Smallest ===\n");
        String query = "SELECT Name, Population FROM country ORDER BY Population DESC";
        executeQuery(query, report); // Execute query with no parameters
        return report.toString();
    }

    // Method to get countries by continent
    public String getCountriesByContinent(String continent) {
        StringBuilder report = new StringBuilder();
        report.append("=== Countries in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT Name, Population FROM country WHERE Continent = ? ORDER BY Population DESC";
        executeQuery(query, report, continent); // Execute query with continent parameter
        return report.toString();
    }

    // Method to get countries by region
    public String getCountriesByRegion(String region) {
        StringBuilder report = new StringBuilder();
        report.append("=== Countries in Region: ").append(region).append(" ===\n");
        String query = "SELECT Name, Population FROM country WHERE Region = ? ORDER BY Population DESC";
        executeQuery(query, report, region); // Execute query with region parameter
        return report.toString();
    }

    // Method to get top N populated countries
    public String getTopNPopulatedCountries(int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Countries ===\n");
        String query = "SELECT Name, Population FROM country ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report); // Execute query without parameters
        return report.toString();
    }

    // Method to get top N populated countries in a continent
    public String getTopNPopulatedCountriesInContinent(String continent, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Countries in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT Name, Population FROM country WHERE Continent = ? ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report, continent); // Execute query with continent parameter
        return report.toString();

    }

    // Method to get countries by region
    public String getTopNPopulatedCountriesInRegion(String continent, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Countries in Region: ").append(continent).append(" ===\n");
        String query = "SELECT Name, Population FROM country WHERE Region = ? ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report, continent); // Execute query with continent parameter
        return report.toString();

    }

    // Private method to execute SQL queries
    private void executeQuery(String query, StringBuilder report, String... params) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Could not load SQL driver: " + e.getMessage()); // Handle driver loading error
            return; // Exit method if driver fails to load
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
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
                // Append country name and population to report
                report.append(String.format("%s: %d%n", rs.getString(1), rs.getInt(2)));
            }

        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage()); // Handle SQL errors
        }
    }
}
