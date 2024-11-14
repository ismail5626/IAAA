package com.napier.iaaa;

import java.sql.*;

public class App {
    // Database connection details
    static final String DB_URL = "jdbc:mysql://db:3306/world"; // URL to the database
    static final String USER = "root"; // Database username
    static final String PASSWORD = "root"; // Database password

    // Main method to run the application
    public static void main(String[] args) {
        App app = new App(); // Create an instance of App

        // Print a welcoming message
        System.out.println("Welcome to the Country Reports Application!\n");

        // Inform user that reports are being generated
        System.out.println("Generating reports...\n");

        // Generate and print each report, with spacing between for readability
        System.out.println(app.getAllCountriesByPopulation()); // Report 1: Countries in the world by population
        System.out.println(); // Spacing between reports

        System.out.println(app.getCountriesByContinent("Asia")); // Report 2: Countries by continent (Asia)
        System.out.println(); // Spacing between reports

        System.out.println(app.getCountriesByRegion("Middle East")); // Report 3: Countries by region (Middle East)
        System.out.println(); // Spacing between reports

        System.out.println(app.getTopNPopulatedCountries(5)); // Report 4: Top N populated countries globally
        System.out.println(); // Spacing between reports

        System.out.println(app.getTopNPopulatedCountriesInContinent("Asia", 5)); // Report 5: Top N populated countries in Asia
        System.out.println(); // Spacing between reports

        System.out.println(app.getTopNPopulatedCountriesInRegion("Middle East", 5)); // Report 6: Top N populated countries in Middle East
        System.out.println(); // Spacing between reports

        System.out.println(app.getAllCitiesByPopulation()); // Report 7: Cities in the world by population
        System.out.println(); // Spacing between reports

        System.out.println(app.getCitiesByContinent("Asia")); // Report 8: Cities by continent (Asia)
        System.out.println(); // Spacing between reports

        System.out.println(app.getCitiesByRegion("Middle East")); // Report 9: Cities by region (Middle East)
        System.out.println(); // Spacing between reports

        System.out.println(app.getCitiesByCountry("Syria")); // Report 10: Cities by country (Syria)
        System.out.println(); // Spacing between reports

        System.out.println(app.getCitiesByDistrict("Dubai")); // Report 11: Cities by district (Dubai)
        System.out.println(); // Spacing between reports

        System.out.println(app.getTopNPopulatedCities(5)); // Report 12: Top N populated cities globally
        System.out.println(); // Spacing between reports

        System.out.println(app.getTopNPopulatedCitiesInContinent("South America", 5)); // Report 13: Top N populated cities in South America
        System.out.println(); // Spacing between reports

        System.out.println(app.getTopNPopulatedCitiesInRegion("Caribbean", 5)); // Report 14: Top N populated cities in Caribbean
        System.out.println(); // Spacing between reports

        System.out.println(app.getTopNPopulatedCitiesInCountry("Austria", 5)); // Report 15: Top N populated cities in Austria
        System.out.println(); // Spacing between reports

        System.out.println(app.getTopNPopulatedCitiesInDistrict("Noord-Brabant", 5)); // Report 16: Top N populated cities in Noord-Brabant
        System.out.println(); // Spacing between reports

        System.out.println(app.getAllCapitalCitiesByPopulation()); // Report 17: Capital cities by population
        System.out.println(); // Spacing between reports

        System.out.println(app.getCapitalCitiesByContinent("Asia")); // Report 18: Capital cities by continent (Asia)
        System.out.println(); // Spacing between reports

        System.out.println(app.getCapitalCitiesByRegion("Western Europe")); // Report 19: Capital cities by region (Western Europe)
        System.out.println(); // Spacing between reports

        System.out.println(app.getTopNPopulatedCapitalCities(5)); // Report 20: Top N populated capital cities globally
        System.out.println(); // Spacing between reports

        System.out.println(app.getTopNPopulatedCapitalCitiesInContinent("Africa", 5)); // Report 21: Top N populated capital cities in Africa
        System.out.println(); // Spacing between reports

        System.out.println(app.getTopNPopulatedCapitalCitiesInRegion("Polynesia", 5)); // Report 22: Top N populated capital cities in Polynesia
        System.out.println(); // Spacing between reports

        System.out.println(app.getContinentPopulationReports("Australia")); // Report 23: Population report by continent (Australia)
        System.out.println(); // Spacing between reports

        System.out.println(app.getRegionPopulationReports("Nordic Countries")); // Report 24: Population report by region (Nordic Countries)
        System.out.println(); // Spacing between reports

        System.out.println(app.getCountryPopulationReports("Afghanistan")); // Report 25: Population report by country (Afghanistan)
        System.out.println(); // Spacing between reports

        System.out.println(app.getWorldPopulation()); // Report 26: World population report
        System.out.println(); // Spacing between reports

        System.out.println(app.getContinentPopulation("Oceania")); // Report 27: Population of continent Oceania
        System.out.println(); // Spacing between reports

        System.out.println(app.getRegionPopulation("Micronesia")); // Report 28: Population of region Micronesia
        System.out.println(); // Spacing between reports

        System.out.println(app.getCountryPopulation("Bhutan")); // Report 29: Population of country Bhutan
        System.out.println(); // Spacing between reports

        System.out.println(app.getDistrictPopulation("Utrecht")); // Report 30: Population of district Utrecht
        System.out.println(); // Spacing between reports

        System.out.println(app.getCityPopulation("Tokyo")); // Report 31: Population of city Tokyo
        System.out.println(); // Spacing between reports

        System.out.println(app.getLanguageSpeakersReport()); // Report 32: Language speakers report
        System.out.println(); // Final spacing
    }


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// COUNTRIES REPORTS:
//
// This section provides methods to generate reports on countries.
// Each report includes information such as:
//     - Code: Unique country code
//     - Name: Country name
//     - Continent: Continent where the country is located
//     - Region: Specific region within the continent
//     - Population: Population count of the country
//     - Capital: Capital city of the country
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    // Method to get all countries ordered by population (largest to smallest)
    public String getAllCountriesByPopulation() {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== All Countries by Largest Population to Smallest ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC;";
        executeQuery(query, report); // Execute query without parameters and build report
        return report.toString(); // Return the generated report as a string
    }

    // Method to get countries within a specified continent ordered by population (largest to smallest)
    public String getCountriesByContinent(String continent) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Countries in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = ? ORDER BY Population DESC";
        executeQuery(query, report, continent); // Execute query with continent as parameter and build report
        return report.toString(); // Return the generated report
    }

    // Method to get countries within a specified region ordered by population (largest to smallest)
    public String getCountriesByRegion(String region) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Countries in Region: ").append(region).append(" ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = ? ORDER BY Population DESC";
        executeQuery(query, report, region); // Execute query with region as parameter and build report
        return report.toString(); // Return the generated report
    }

    // Method to get the top N most populated countries
    public String getTopNPopulatedCountries(int N) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Top ").append(N).append(" Populated Countries ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report); // Execute query to limit results to top N countries by population
        return report.toString(); // Return the generated report
    }

    // Method to get the top N most populated countries in a specified continent
    public String getTopNPopulatedCountriesInContinent(String continent, int N) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Top ").append(N).append(" Populated Countries in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = ? ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report, continent); // Execute query with continent parameter
        return report.toString(); // Return the generated report
    }

    // Method to get the top N most populated countries in a specified region
    public String getTopNPopulatedCountriesInRegion(String region, int N) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Top ").append(N).append(" Populated Countries in Region: ").append(region).append(" ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = ? ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report, region); // Execute query with region parameter
        return report.toString(); // Return the generated report
    }

    // Method to get all cities ordered by population (largest to smallest)
    public String getAllCitiesByPopulation() {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== All Cities by Largest Population to Smallest ===\n");
        String query = "SELECT Name, CountryCode, District, Population FROM city ORDER BY Population DESC;";
        executeQuery(query, report); // Execute query with no parameters
        return report.toString(); // Return the generated report
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        CITIES REPORTS:

        Formatted Like:
                        Name.
                        Country.
                        District
                        Population.
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    */

    // Method to get cities within a specified continent, ordered by population (largest to smallest)
    public String getCitiesByContinent(String continent) {
        StringBuilder report = new StringBuilder();
        report.append("=== Cities in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT city.Name, CountryCode, District, city.Population " +
                "FROM City " +
                "JOIN country ON city.CountryCode = country.code " +
                "WHERE country.continent = ? " +
                "ORDER BY city.Population DESC";
        executeQuery(query, report, continent); // Execute query with continent parameter
        return report.toString(); // Return the generated report
    }

    // Method to get cities within a specified region, ordered by population (largest to smallest)
    public String getCitiesByRegion(String region) {
        StringBuilder report = new StringBuilder();
        report.append("=== All Cities in Region: ").append(region).append(" by Largest Population to Smallest ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC";
        executeQuery(query, report, region); // Execute query with region parameter
        return report.toString(); // Return the generated report
    }

    // Method to get cities within a specified country, ordered by population (largest to smallest)
    public String getCitiesByCountry(String country) {
        StringBuilder report = new StringBuilder();
        report.append("=== All Cities in Country: ").append(country).append(" by Largest Population to Smallest ===\n");
        String query = "SELECT city.Name, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = ? " +
                "ORDER BY city.Population DESC";
        executeQuery(query, report, country); // Execute query with country parameter
        return report.toString(); // Return the generated report
    }

    // Method to get cities within a specified district, ordered by population (largest to smallest)
    public String getCitiesByDistrict(String district) {
        StringBuilder report = new StringBuilder();
        report.append("=== All Cities in District: ").append(district).append(" by Largest Population to Smallest ===\n");
        String query = "SELECT Name, Population FROM city WHERE District = ? ORDER BY Population DESC";
        executeQuery(query, report, district); // Execute query with district parameter
        return report.toString(); // Return the generated report
    }

    // Method to get the top N most populated cities in the world
    public String getTopNPopulatedCities(int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Cities in the World ===\n");
        String query = "SELECT Name, CountryCode, District, Population FROM city ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report); // Execute query to limit results to top N cities globally
        return report.toString(); // Return the generated report
    }

    // Method to get the top N most populated cities within a specified continent
    public String getTopNPopulatedCitiesInContinent(String continent, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Cities in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC LIMIT " + N;
        executeQuery(query, report, continent); // Execute query with continent parameter
        return report.toString(); // Return the generated report
    }

    // Method to get the top N most populated cities within a specified region
    public String getTopNPopulatedCitiesInRegion(String region, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Cities in Region: ").append(region).append(" ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC LIMIT " + N;
        executeQuery(query, report, region); // Execute query with region parameter
        return report.toString(); // Return the generated report
    }

    // Method to get the top N most populated cities within a specified country
    public String getTopNPopulatedCitiesInCountry(String country, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Cities in Country: ").append(country).append(" ===\n");
        String query = "SELECT city.Name, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = ? " +
                "ORDER BY city.Population DESC LIMIT " + N;
        executeQuery(query, report, country); // Execute query with country parameter
        return report.toString(); // Return the generated report
    }

    // Method to get the top N most populated cities within a specified district
    public String getTopNPopulatedCitiesInDistrict(String district, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Cities in District: ").append(district).append(" ===\n");
        String query = "SELECT Name, Population FROM city WHERE District = ? ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report, district); // Execute query with district parameter
        return report.toString(); // Return the generated report
    }


       /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        CAPITAL CITIES REPORTS:

        Formatted Like:
                        Name.
                        Country.
                        Population.
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    */

    // Method to get all capital cities in the world, ordered by population (largest to smallest)
    public String getAllCapitalCitiesByPopulation() {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== All Capital Cities in the World by Largest Population to Smallest ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC";
        executeQuery(query, report); // Execute query with no parameters and build report
        return report.toString(); // Return the generated report
    }

    // Method to get capital cities in a specified continent, ordered by population (largest to smallest)
    public String getCapitalCitiesByContinent(String continent) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Capital Cities in Continent: ").append(continent).append(" by Largest Population to Smallest ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC";
        executeQuery(query, report, continent); // Execute query with continent parameter and build report
        return report.toString(); // Return the generated report
    }

    // Method to get capital cities in a specified region, ordered by population (largest to smallest)
    public String getCapitalCitiesByRegion(String region) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Capital Cities in Region: ").append(region).append(" by Largest Population to Smallest ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC";
        executeQuery(query, report, region); // Execute query with region parameter and build report
        return report.toString(); // Return the generated report
    }

    // Method to get the top N most populated capital cities in the world
    public String getTopNPopulatedCapitalCities(int N) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Top ").append(N).append(" Populated Capital Cities in the World ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC LIMIT " + N;
        executeQuery(query, report); // Execute query to limit results to top N capital cities globally
        return report.toString(); // Return the generated report
    }

    // Method to get the top N most populated capital cities in a specified continent
    public String getTopNPopulatedCapitalCitiesInContinent(String continent, int N) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Top ").append(N).append(" Populated Capital Cities in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC LIMIT " + N;
        executeQuery(query, report, continent); // Execute query with continent parameter and limit to top N results
        return report.toString(); // Return the generated report
    }

    // Method to get the top N most populated capital cities in a specified region
    public String getTopNPopulatedCapitalCitiesInRegion(String region, int N) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Top ").append(N).append(" Populated Capital Cities in Region: ").append(region).append(" ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC LIMIT " + N;
        executeQuery(query, report, region); // Execute query with region parameter and limit to top N results
        return report.toString(); // Return the generated report
    }


       /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        POPULATION REPORTS:

        Formatted Like:
                        Name of Continent / Region / Country
                        The total population of the continent/region/country.
                        The total population of the continent/region/country living in cities (including a %).
                        The total population of the continent/region/country not living in cities (including a %).
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    */

    // Method to get a population report for a specific continent, including total population, population in cities,
// population not in cities, and percentage of population in and outside cities
    public String getContinentPopulationReports(String continent) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Population Report for Continent: ").append(continent).append(" ===\n");
        String query = "SELECT country.Continent AS Continent, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS PopulationInCities, " +
                "SUM(country.Population) - SUM(city.Population) AS PopulationNotInCities, " +
                "ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2) AS PercentageInCities, " +
                "ROUND((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population) * 100, 2) AS PercentageNotInCities " +
                "FROM country " +
                "JOIN city ON country.Code = city.CountryCode " +
                "WHERE country.Continent = ? " +
                "GROUP BY country.Continent";
        executeQuery(query, report, continent); // Execute query with continent parameter and build report
        return report.toString(); // Return the generated report
    }

    // Method to get a population report for a specific region, including total population, population in cities,
// population not in cities, and percentage of population in and outside cities
    public String getRegionPopulationReports(String region) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Population Report for Region: ").append(region).append(" ===\n");
        String query = "SELECT country.Region AS Region, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS PopulationInCities, " +
                "SUM(country.Population) - SUM(city.Population) AS PopulationNotInCities, " +
                "ROUND(SUM(city.Population) / SUM(country.Population) * 100, 2) AS PercentageInCities, " +
                "ROUND((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population) * 100, 2) AS PercentageNotInCities " +
                "FROM country " +
                "JOIN city ON country.Code = city.CountryCode " +
                "WHERE country.Region = ? " +
                "GROUP BY country.Region";
        executeQuery(query, report, region); // Execute query with region parameter and build report
        return report.toString(); // Return the generated report
    }

    // Method to get a population report for a specific country, including total population, population in cities,
// population not in cities, and percentage of population in and outside cities
    public String getCountryPopulationReports(String country) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Population Report for Country: ").append(country).append(" ===\n");
        String query = "SELECT country.Name AS Country, " +
                "country.Population AS TotalPopulation, " +
                "IFNULL(city.PopInCities, 0) AS PopulationInCities, " +
                "country.Population - IFNULL(city.PopInCities, 0) AS PopulationNotInCities, " +
                "ROUND(IFNULL(city.PopInCities, 0) / country.Population * 100, 2) AS PercentageInCities, " +
                "ROUND((country.Population - IFNULL(city.PopInCities, 0)) / country.Population * 100, 2) AS PercentageNotInCities " +
                "FROM country " +
                "LEFT JOIN ( " +
                "    SELECT CountryCode, SUM(Population) AS PopInCities " +
                "    FROM city " +
                "    GROUP BY CountryCode " +
                ") AS city ON country.Code = city.CountryCode " +
                "WHERE country.Name = ?";
        executeQuery(query, report, country); // Execute query with country parameter and build report
        return report.toString(); // Return the generated report
    }

    // Method to get the total world population
    public String getWorldPopulation() {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== World Population Report ===\n");
        String query = "SELECT SUM(Population) AS WorldPopulation FROM country";
        executeQuery(query, report); // Execute query without parameters
        return report.toString(); // Return the generated report
    }

    // Method to get the total population of a specific continent
    public String getContinentPopulation(String continent) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Population Report for Continent: ").append(continent).append(" ===\n");
        String query = "SELECT SUM(Population) AS ContinentPopulation FROM country WHERE Continent = ?";
        executeQuery(query, report, continent); // Execute query with continent parameter
        return report.toString(); // Return the generated report
    }

    // Method to get the total population of a specific region
    public String getRegionPopulation(String region) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Population Report for Region: ").append(region).append(" ===\n");
        String query = "SELECT SUM(Population) AS RegionPopulation FROM country WHERE Region = ?";
        executeQuery(query, report, region); // Execute query with region parameter
        return report.toString(); // Return the generated report
    }

    // Method to get the total population of a specific country
    public String getCountryPopulation(String country) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Population Report for Country: ").append(country).append(" ===\n");
        String query = "SELECT Population AS CountryPopulation FROM country WHERE Name = ?";
        executeQuery(query, report, country); // Execute query with country parameter
        return report.toString(); // Return the generated report
    }

    // Method to get the total population of a specific district
    public String getDistrictPopulation(String district) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Population Report for District: ").append(district).append(" ===\n");
        String query = "SELECT SUM(Population) AS DistrictPopulation FROM city WHERE District = ?";
        executeQuery(query, report, district); // Execute query with district parameter
        return report.toString(); // Return the generated report
    }

    // Method to get the population of a specific city
    public String getCityPopulation(String city) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Population Report for City: ").append(city).append(" ===\n");
        String query = "SELECT Population AS CityPopulation FROM city WHERE Name = ?";
        executeQuery(query, report, city); // Execute query with city parameter
        return report.toString(); // Return the generated report
    }

    // Method to generate a report on speakers of specific languages, ordered by the total number of speakers
    public String getLanguageSpeakersReport() {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Language Speakers Report ===\n");
        String query = "SELECT language.Language, " +
                "SUM(country.Population * language.Percentage / 100) AS Speakers, " +
                "ROUND(SUM(country.Population * language.Percentage / 100) / (SELECT SUM(Population) FROM country) * 100, 2) AS Percentage_Of_World " +
                "FROM country " +
                "JOIN countrylanguage AS language ON country.Code = language.CountryCode " +
                "WHERE language.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                "GROUP BY language.Language " +
                "ORDER BY Speakers DESC";
        executeQuery(query, report); // Execute query without parameters
        return report.toString(); // Return the generated report
    }


         /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        Code for Connecting With Database

    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    */

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
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) { // Iterate through the result set
                for (int i = 1; i <= columnCount; i++) {
                    // Append column value to report
                    report.append(metaData.getColumnLabel(i)).append(": ").append(rs.getString(i));
                    if (i < columnCount) report.append(", "); // Add comma if not last column
                }
                report.append("\n"); // Add newline after each row
            }

        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage()); // Handle SQL errors
        }
    }
}