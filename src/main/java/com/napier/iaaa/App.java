package com.napier.iaaa;

import java.sql.*;

public class App {
    // Declare the persistent connection variable
    private Connection con;

    /**
    /**
     * Closes the persistent database connection if it is open.
     */
    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Error closing database connection: " + e.getMessage());
            }
        }
    }

    /**
     * Establishes a connection to the database with a specified number of retry attempts
     * and a delay between retries.
     *
     * @param location the location of the database (e.g., "localhost:33060")
     * @param delay    the delay in milliseconds between retry attempts
     */
    public void connect(String location, int delay) {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);  // Exit if driver is not found
        }

        int retries = 10;  // Number of retry attempts
        boolean shouldWait = false;  // Flag to wait before next retry
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");

            try {
                if (shouldWait) {
                    // Wait before retrying (if needed)
                    Thread.sleep(delay);
                }

                // Build the connection URL dynamically with the location parameter (e.g., "localhost:33060")
                String dbUrl = "jdbc:mysql://" + location + "/world?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

                // Connect to database using the dynamically constructed URL
                con = DriverManager.getConnection(dbUrl, "root", "root");

                System.out.println("Successfully connected to the database.");
                break;  // Exit loop if connected successfully
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database (attempt " + (i + 1) + ")");
                System.out.println(sqle.getMessage());

                // Set flag to wait before the next retry attempt
                shouldWait = true;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted unexpectedly.");
            }
        }

        if (con == null) {
            System.out.println("Unable to connect to the database after " + retries + " attempts.");
            System.exit(-1);  // Exit if the connection is unsuccessful
        }
    }

    /**
     * Main method to run the application.
     * Connects to the database and initiates the report generation process.
     *
     * @param args command-line arguments:
     *             the first argument specifies the database location,
     *             the second argument specifies the retry delay in milliseconds.
     */
    public static void main(String[] args) {
        App app = new App(); // Create an instance of App

        // Default to localhost:33060 if no arguments are provided
        String dbLocation = (args.length < 1) ? "localhost:33060" : args[0];
        int delay = (args.length < 2) ? 10000 : Integer.parseInt(args[1]);

        // Connect to the database with retry and delay
        app.connect(dbLocation, delay);

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

        System.out.println(app.getContinentPopulationReports()); // Report 23: Population report by continent (Australia)
        System.out.println(); // Spacing between reports

        System.out.println(app.getRegionPopulationReports()); // Report 24: Population report by region (Nordic Countries)
        System.out.println(); // Spacing between reports

        System.out.println(app.getCountryPopulationReports()); // Report 25: Population report by country (Afghanistan)
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

    /**
     * Retrieves all countries ordered by population in descending order (largest to smallest).
     *
     * @return a string report containing the country details including code, name, continent,
     *         region, population, and capital, ordered by population.
     */
    public String getAllCountriesByPopulation() {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== All Countries by Largest Population to Smallest ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC;";
        executeQuery(query, report); // Execute query without parameters and build report
        return report.toString(); // Return the generated report as a string
    }

    /**
     * Retrieves countries within a specified continent ordered by population in descending order (largest to smallest).
     *
     * @param continent the name of the continent for which the countries should be retrieved
     * @return a string report containing the country details including code, name, continent,
     *         region, population, and capital, ordered by population within the specified continent
     */
    public String getCountriesByContinent(String continent) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Countries in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = ? ORDER BY Population DESC";
        executeQuery(query, report, continent); // Execute query with continent as parameter and build report
        return report.toString(); // Return the generated report
    }


    /**
     * Retrieves countries within a specified region ordered by population in descending order (largest to smallest).
     *
     * @param region the name of the region for which the countries should be retrieved
     * @return a string report containing the country details including code, name, continent,
     *         region, population, and capital, ordered by population within the specified region
     */
    public String getCountriesByRegion(String region) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Countries in Region: ").append(region).append(" ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = ? ORDER BY Population DESC";
        executeQuery(query, report, region); // Execute query with region as parameter and build report
        return report.toString(); // Return the generated report
    }

    /**
     * Retrieves the top N most populated countries globally.
     *
     * @param N the number of top populated countries to retrieve
     * @return a string report containing the top N country details including code, name, continent,
     *         region, population, and capital, ordered by population
     */
    public String getTopNPopulatedCountries(int N) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Top ").append(N).append(" Populated Countries ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report); // Execute query to limit results to top N countries by population
        return report.toString(); // Return the generated report
    }

    /**
     * Retrieves the top N most populated countries in a specified continent.
     *
     * @param continent the name of the continent for which the top N populated countries should be retrieved
     * @param N the number of top populated countries to retrieve within the specified continent
     * @return a string report containing the top N country details including code, name, continent,
     *         region, population, and capital, ordered by population
     */
    public String getTopNPopulatedCountriesInContinent(String continent, int N) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Top ").append(N).append(" Populated Countries in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = ? ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report, continent); // Execute query with continent parameter
        return report.toString(); // Return the generated report
    }

    /**
     * Retrieves the top N most populated countries in a specified region.
     *
     * @param region the name of the region for which the top N populated countries should be retrieved
     * @param N the number of top populated countries to retrieve within the specified region
     * @return a string report containing the top N country details including code, name, continent,
     *         region, population, and capital, ordered by population
     */
    public String getTopNPopulatedCountriesInRegion(String region, int N) {
        StringBuilder report = new StringBuilder(); // Initialize report
        report.append("=== Top ").append(N).append(" Populated Countries in Region: ").append(region).append(" ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = ? ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report, region); // Execute query with region parameter
        return report.toString(); // Return the generated report
    }

    /**
     * Retrieves all cities ordered by population in descending order (largest to smallest).
     *
     * @return a string report containing the city details including name, country code, district,
     *         and population, ordered by population
     */
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

    /**
     * Retrieves cities within a specified continent, ordered by population in descending order (largest to smallest).
     *
     * @param continent the name of the continent for which the cities should be retrieved
     * @return a string report containing city details including name, country code, district, and population
     */
    public String getCitiesByContinent(String continent) {
        StringBuilder report = new StringBuilder();
        report.append("=== Cities in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT city.Name, CountryCode, District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.code " +
                "WHERE country.continent = ? " +
                "ORDER BY city.Population DESC";
        executeQuery(query, report, continent);
        return report.toString();
    }

    /**
     * Retrieves cities within a specified region, ordered by population in descending order (largest to smallest).
     *
     * @param region the name of the region for which the cities should be retrieved
     * @return a string report containing city details including name, country, district, and population
     */
    public String getCitiesByRegion(String region) {
        StringBuilder report = new StringBuilder();
        report.append("=== All Cities in Region: ").append(region).append(" by Largest Population to Smallest ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC";
        executeQuery(query, report, region);
        return report.toString();
    }

    /**
     * Retrieves cities within a specified country, ordered by population in descending order (largest to smallest).
     *
     * @param country the name of the country for which the cities should be retrieved
     * @return a string report containing city details including name, district, and population
     */
    public String getCitiesByCountry(String country) {
        StringBuilder report = new StringBuilder();
        report.append("=== All Cities in Country: ").append(country).append(" by Largest Population to Smallest ===\n");
        String query = "SELECT city.Name, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = ? " +
                "ORDER BY city.Population DESC";
        executeQuery(query, report, country);
        return report.toString();
    }

    /**
     * Retrieves cities within a specified district, ordered by population in descending order (largest to smallest).
     *
     * @param district the name of the district for which the cities should be retrieved
     * @return a string report containing city details including name and population
     */
    public String getCitiesByDistrict(String district) {
        StringBuilder report = new StringBuilder();
        report.append("=== All Cities in District: ").append(district).append(" by Largest Population to Smallest ===\n");
        String query = "SELECT Name, Population FROM city WHERE District = ? ORDER BY Population DESC";
        executeQuery(query, report, district);
        return report.toString();
    }

    /**
     * Retrieves the top N most populated cities in the world.
     *
     * @param N the number of top populated cities to retrieve
     * @return a string report containing city details including name, country code, district, and population
     */
    public String getTopNPopulatedCities(int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Cities in the World ===\n");
        String query = "SELECT Name, CountryCode, District, Population FROM city ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report);
        return report.toString();
    }

    /**
     * Retrieves the top N most populated cities within a specified continent.
     *
     * @param continent the name of the continent for which the top N populated cities should be retrieved
     * @param N the number of top populated cities to retrieve within the specified continent
     * @return a string report containing city details including name, country, district, and population
     */
    public String getTopNPopulatedCitiesInContinent(String continent, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Cities in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC LIMIT " + N;
        executeQuery(query, report, continent);
        return report.toString();
    }

    /**
     * Retrieves the top N most populated cities within a specified region.
     *
     * @param region the name of the region for which the top N populated cities should be retrieved
     * @param N the number of top populated cities to retrieve within the specified region
     * @return a string report containing city details including name, country, district, and population
     */
    public String getTopNPopulatedCitiesInRegion(String region, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Cities in Region: ").append(region).append(" ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC LIMIT " + N;
        executeQuery(query, report, region);
        return report.toString();
    }

    /**
     * Retrieves the top N most populated cities within a specified country.
     *
     * @param country the name of the country for which the top N populated cities should be retrieved
     * @param N the number of top populated cities to retrieve within the specified country
     * @return a string report containing city details including name, district, and population
     */
    public String getTopNPopulatedCitiesInCountry(String country, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Cities in Country: ").append(country).append(" ===\n");
        String query = "SELECT city.Name, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = ? " +
                "ORDER BY city.Population DESC LIMIT " + N;
        executeQuery(query, report, country);
        return report.toString();
    }

    /**
     * Retrieves the top N most populated cities within a specified district.
     *
     * @param district the name of the district for which the top N populated cities should be retrieved
     * @param N the number of top populated cities to retrieve within the specified district
     * @return a string report containing city details including name and population
     */
    public String getTopNPopulatedCitiesInDistrict(String district, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Cities in District: ").append(district).append(" ===\n");
        String query = "SELECT Name, Population FROM city WHERE District = ? ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report, district);
        return report.toString();
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

    /**
     * Retrieves all capital cities in the world, ordered by population in descending order (largest to smallest).
     *
     * @return a string report containing capital city details including name, country, and population
     */
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

    /**
     * Retrieves capital cities in a specified continent, ordered by population in descending order (largest to smallest).
     *
     * @param continent the name of the continent for which the capital cities should be retrieved
     * @return a string report containing capital city details including name, country, and population
     */
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

    /**
     * Retrieves capital cities in a specified region, ordered by population in descending order (largest to smallest).
     *
     * @param region the name of the region for which the capital cities should be retrieved
     * @return a string report containing capital city details including name, country, and population
     */
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

    /**
     * Retrieves the top N most populated capital cities in the world.
     *
     * @param N the number of top populated capital cities to retrieve
     * @return a string report containing capital city details including name, country, and population
     */
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

    /**
     * Retrieves the top N most populated capital cities in a specified continent.
     *
     * @param continent the name of the continent for which the top N populated capital cities should be retrieved
     * @param N the number of top populated capital cities to retrieve within the specified continent
     * @return a string report containing capital city details including name, country, and population
     */
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

    /**
     * Retrieves the top N most populated capital cities in a specified region.
     *
     * @param region the name of the region for which the top N populated capital cities should be retrieved
     * @param N the number of top populated capital cities to retrieve within the specified region
     * @return a string report containing capital city details including name, country, and population
     */
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

    /**
     * Generates a population report for each continent, including total population, population in cities,
     * population not in cities, and percentages of populations in and outside cities.
     *
     * @return a string report containing population details for each continent
     */
    public String getContinentPopulationReports() {
        StringBuilder report = new StringBuilder();
        report.append("=== Population Report by Continent ===\n");
        String query = "SELECT country.Continent AS Continent, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS PopulationInCities, " +
                "SUM(country.Population) - SUM(city.Population) AS PopulationNotInCities " +
                "FROM country " +
                "LEFT JOIN city ON country.Code = city.CountryCode " +
                "GROUP BY country.Continent";
        executeQuery(query, report);
        return report.toString();
    }

    /**
     * Generates a population report for each region, including total population, population in cities,
     * population not in cities, and percentages of populations in and outside cities.
     *
     * @return a string report containing population details for each region
     */
    public String getRegionPopulationReports() {
        StringBuilder report = new StringBuilder();
        report.append("=== Population Report by Region ===\n");
        String query = "SELECT country.Region AS Region, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS PopulationInCities, " +
                "SUM(country.Population) - SUM(city.Population) AS PopulationNotInCities " +
                "FROM country " +
                "LEFT JOIN city ON country.Code = city.CountryCode " +
                "GROUP BY country.Region";
        executeQuery(query, report);
        return report.toString();
    }

    /**
     * Generates a population report for each country, including total population, population in cities,
     * population not in cities, and percentages of populations in and outside cities.
     *
     * @return a string report containing population details for each country
     */
    public String getCountryPopulationReports() {
        StringBuilder report = new StringBuilder();
        report.append("=== Population Report by Country ===\n");
        String query = "SELECT country.Name AS Country, " +
                "SUM(country.Population) AS TotalPopulation, " +
                "IFNULL(SUM(city.Population), 0) AS PopulationInCities, " +
                "SUM(country.Population) - IFNULL(SUM(city.Population), 0) AS PopulationNotInCities " +
                "FROM country " +
                "LEFT JOIN city ON country.Code = city.CountryCode " +
                "GROUP BY country.Name";
        executeQuery(query, report);
        return report.toString();
    }

    /**
     * Retrieves the total world population.
     *
     * @return a string report containing the total world population
     */
    public String getWorldPopulation() {
        StringBuilder report = new StringBuilder();
        report.append("=== World Population Report ===\n");
        String query = "SELECT SUM(Population) AS WorldPopulation FROM country";
        executeQuery(query, report);
        return report.toString();
    }

    /**
     * Retrieves the total population of a specific continent.
     *
     * @param continent the name of the continent
     * @return a string report containing the total population of the specified continent
     */
    public String getContinentPopulation(String continent) {
        StringBuilder report = new StringBuilder();
        report.append("=== Population Report for Continent: ").append(continent).append(" ===\n");
        String query = "SELECT SUM(Population) AS ContinentPopulation FROM country WHERE Continent = ?";
        executeQuery(query, report, continent);
        return report.toString();
    }

    /**
     * Retrieves the total population of a specific region.
     *
     * @param region the name of the region
     * @return a string report containing the total population of the specified region
     */
    public String getRegionPopulation(String region) {
        StringBuilder report = new StringBuilder();
        report.append("=== Population Report for Region: ").append(region).append(" ===\n");
        String query = "SELECT SUM(Population) AS RegionPopulation FROM country WHERE Region = ?";
        executeQuery(query, report, region);
        return report.toString();
    }

    /**
     * Retrieves the total population of a specific country.
     *
     * @param country the name of the country
     * @return a string report containing the total population of the specified country
     */
    public String getCountryPopulation(String country) {
        StringBuilder report = new StringBuilder();
        report.append("=== Population Report for Country: ").append(country).append(" ===\n");
        String query = "SELECT Population AS CountryPopulation FROM country WHERE Name = ?";
        executeQuery(query, report, country);
        return report.toString();
    }

    /**
     * Retrieves the total population of a specific district.
     *
     * @param district the name of the district
     * @return a string report containing the total population of the specified district
     */
    public String getDistrictPopulation(String district) {
        StringBuilder report = new StringBuilder();
        report.append("=== Population Report for District: ").append(district).append(" ===\n");
        String query = "SELECT SUM(Population) AS DistrictPopulation FROM city WHERE District = ?";
        executeQuery(query, report, district);
        return report.toString();
    }

    /**
     * Retrieves the population of a specific city.
     *
     * @param city the name of the city
     * @return a string report containing the population of the specified city
     */
    public String getCityPopulation(String city) {
        StringBuilder report = new StringBuilder();
        report.append("=== Population Report for City: ").append(city).append(" ===\n");
        String query = "SELECT Population AS CityPopulation FROM city WHERE Name = ?";
        executeQuery(query, report, city);
        return report.toString();
    }

    /**
     * Generates a report on the speakers of specific languages, ordered by the total number of speakers.
     * The report includes the total speakers and the percentage of the world population for each language.
     *
     * @return a string report containing language speaker statistics
     */
    public String getLanguageSpeakersReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Language Speakers Report ===\n");
        String query = "SELECT language.Language, " +
                "SUM(country.Population * language.Percentage / 100) AS Speakers, " +
                "ROUND(SUM(country.Population * language.Percentage / 100) / (SELECT SUM(Population) FROM country) * 100, 2) AS Percentage_Of_World " +
                "FROM country " +
                "JOIN countrylanguage AS language ON country.Code = language.CountryCode " +
                "WHERE language.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                "GROUP BY language.Language " +
                "ORDER BY Speakers DESC";
        executeQuery(query, report);
        return report.toString();
    }



         /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        Code for Connecting With Database

    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    */

    /**
     * Executes an SQL query using the persistent database connection and appends the results to a report.
     * Supports parameterized queries for enhanced security.
     *
     * @param query  the SQL query to execute
     * @param report the {@code StringBuilder} to append the query results
     * @param params optional parameters for the query; used to set values in a prepared statement
     */
    private void executeQuery(String query, StringBuilder report, String... params) {
        try (PreparedStatement pstmt = con.prepareStatement(query)) {  // Use the persistent connection
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
    }}



