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

        // Report 1: All the countries in the world organised by largest population to smallest.
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

        //Report 6: Top N populated countries by specific reigion (e.g., Middle East)
        System.out.println(app.getTopNPopulatedCountriesInRegion("Middle East", 5)); // Generate and print report
        System.out.println(); // Add spacing after the last report

        //Report 7: All the cities in the world organised by largest population to smallest.
        System.out.println(app.getAllCitiesByPopulation()); // Generate and print report
        System.out.println(); // Add spacing between reports

        //Report 8:
        System.out.println(app.getCitiesByContinent("Asia")); // Generate and print report
        System.out.println(); // Add spacing between reports

        //Report 9:
        System.out.println(app.getCitiesByRegion("Middle East")); // Generate and print report
        System.out.println(); // Add spacing between reports

        //Report 10:
        System.out.println(app.getCitiesByCountry("Syria")); // Generate and print report
        System.out.println(); // Add spacing between reports

        //Report 11:
        System.out.println(app.getCitiesByDistrict("Dubai")); // Generate and print report
        System.out.println(); // Add spacing between reports

        //Report 12:
        System.out.println(app.getTopNPopulatedCities(5)); // Generate and print report
        System.out.println(); // Add spacing between reports

        //Report 13:
        System.out.println(app.getTopNPopulatedCitiesInContinent("South America", 5)); // Generate and print report
        System.out.println(); // Add spacing after the last report

        //Report 14:
        System.out.println(app.getTopNPopulatedCitiesInRegion("Caribbean", 5)); // Generate and print report
        System.out.println(); // Add spacing after the last report

        //Report 15:
        System.out.println(app.getTopNPopulatedCitiesInCountry("Austria", 5)); // Generate and print report
        System.out.println(); // Add spacing after the last report

        //Report 16:
        System.out.println(app.getTopNPopulatedCitiesInDistrict("Noord-Brabant", 5)); // Generate and print report
        System.out.println(); // Add spacing after the last report

        //Report 17:
        //Report 7: All the cities in the world organised by largest population to smallest.
        System.out.println(app.getAllCapitalCitiesByPopulation()); // Generate and print report
        System.out.println(); // Add spacing between reports

        //Report 18:
        System.out.println(app.getCapitalCitiesByContinent("Asia")); // Generate and print report
        System.out.println(); // Add spacing between reports

        //Report 19:
        System.out.println(app.getCapitalCitiesByRegion("Western Europe")); // Generate and print report
        System.out.println(); // Add spacing between reports

        //Report 20:
        System.out.println(app.getTopNPopulatedCapitalCities(5)); // Generate and print report
        System.out.println(); // Add spacing between reports

        //Report 21:
        System.out.println(app.getTopNPopulatedCapitalCitiesInContinent("Africa", 5)); // Generate and print report
        System.out.println(); // Add spacing after the last report

        //Report 22:
        System.out.println(app.getTopNPopulatedCapitalCitiesInRegion("Polynesia", 5)); // Generate and print report
        System.out.println(); // Add spacing after the last report

        //Report 23:
        System.out.println(app.getContinentPopulationReports("Australia")); // Generate and print report
        System.out.println(); // Add spacing after the last report

        //Report 24:
        System.out.println(app.getRegionPopulationReports("Nordic Countries")); // Generate and print report
        System.out.println(); // Add spacing after the last report

        //Report 25:
        System.out.println(app.getCountryPopulationReports("Afghanistan")); // Generate and print report
        System.out.println(); // Add spacing after the last report

        //Report 26:
        System.out.println(app.getWorldPopulation());
        System.out.println();

        //Report 27:
        System.out.println(app.getContinentPopulation("Oceania"));
        System.out.println();

        //Report 28:
        System.out.println(app.getRegionPopulation("Micronesia"));
        System.out.println();

        //Report 29:
        System.out.println(app.getCountryPopulation("Bhutan"));
        System.out.println();

        //Report 30:
        System.out.println(app.getDistrictPopulation("Utrecht"));
        System.out.println();

        //Report 31
        System.out.println(app.getCityPopulation("Tokyo"));
        System.out.println();

        //Report 32
        System.out.println(app.getLanguageSpeakersReport());
        System.out.println();


    }



       /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        COUNTRIES REPORTS:

        Formatted Like:
                        Code.
                        Name.
                        Continent.
                        Region.
                        Population.
                        Capital.
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    */

    // Method to get all countries by population
    public String getAllCountriesByPopulation() {
        StringBuilder report = new StringBuilder();
        report.append("=== All Countries by Largest Population to Smallest ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC;";
        executeQuery(query, report); // Execute query with no parameters
        return report.toString();
    }


    // Method to get countries by continent
    public String getCountriesByContinent(String continent) {
        StringBuilder report = new StringBuilder();
        report.append("=== Countries in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = ? ORDER BY Population DESC";
        executeQuery(query, report, continent); // Execute query with continent parameter
        return report.toString();
    }


    // Method to get countries by region
    public String getCountriesByRegion(String region) {
        StringBuilder report = new StringBuilder();
        report.append("=== Countries in Region: ").append(region).append(" ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = ? ORDER BY Population DESC";
        executeQuery(query, report, region); // Execute query with region parameter
        return report.toString();
    }


    // Method to get top N populated countries
    public String getTopNPopulatedCountries(int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Countries ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report); // Execute query without parameters
        return report.toString();
    }


    // Method to get top N populated countries in a continent
    public String getTopNPopulatedCountriesInContinent(String continent, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Countries in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = ? ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report, continent); // Execute query with continent parameter
        return report.toString();
    }


    // Method to get countries by region
    public String getTopNPopulatedCountriesInRegion(String continent, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Countries in Region: ").append(continent).append(" ===\n");
        String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region = ? ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report, continent); // Execute query with continent parameter
        return report.toString();
    }


    // Method to get all cities by population
    public String getAllCitiesByPopulation() {
        StringBuilder report = new StringBuilder();
        report.append("=== All Cities by Largest Population to Smallest ===\n");
        String query = "SELECT Name, CountryCode, District, Population FROM city ORDER BY Population DESC;";
        executeQuery(query, report); // Execute query with no parameters
        return report.toString();
    }

       /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        CITIES REPORTS:

        Formatted Like:
                        Name.
                        Country.
                        District
                        Population.
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    */


    // Method to get countries by continent
    public String getCitiesByContinent(String continent) {
        StringBuilder report = new StringBuilder();
        report.append("=== Countries in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT city.Name, CountryCode, District, city.Population " +
                       "FROM City " +
                       "JOIN  country ON city.CountryCode = country.code " +
                       "WHERE country.continent = ? " +
                       "ORDER BY city.Population DESC";
        executeQuery(query, report, continent); // Execute query with continent parameter
        return report.toString();
    }

    // Method to get all cities in a region by population
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

    // Method to get all cities in a country by population
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

    // Method to get all cities in a district by population
    public String getCitiesByDistrict(String district) {
        StringBuilder report = new StringBuilder();
        report.append("=== All Cities in District: ").append(district).append(" by Largest Population to Smallest ===\n");
        String query = "SELECT Name, Population FROM city WHERE District = ? ORDER BY Population DESC";
        executeQuery(query, report, district);
        return report.toString();
    }

    public String getTopNPopulatedCities(int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Cities in the World ===\n");
        String query = "SELECT Name, CountryCode, District, Population FROM city ORDER BY Population DESC LIMIT " + N;
        executeQuery(query, report);
        return report.toString();
    }


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

    public String getTopNPopulatedCitiesInCountry(String country, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Cities in Country: ").append(country).append(" ===\n");
        String query = "SELECT city.Name, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Name = ?" +
                "ORDER BY city.Population DESC LIMIT " + N;
        executeQuery(query, report, country);
        return report.toString();
    }

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

    public String getAllCapitalCitiesByPopulation() {
        StringBuilder report = new StringBuilder();
        report.append("=== All Capital Cities in the World by Largest Population to Smallest ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC";
        executeQuery(query, report);
        return report.toString();
    }

    public String getCapitalCitiesByContinent(String continent) {
        StringBuilder report = new StringBuilder();
        report.append("=== Capital Cities in Continent: ").append(continent).append(" by Largest Population to Smallest ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC";
        executeQuery(query, report, continent);
        return report.toString();
    }

    public String getCapitalCitiesByRegion(String region) {
        StringBuilder report = new StringBuilder();
        report.append("=== Capital Cities in Region: ").append(region).append(" by Largest Population to Smallest ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC";
        executeQuery(query, report, region);
        return report.toString();
    }

    public String getTopNPopulatedCapitalCities(int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Capital Cities in the World ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC LIMIT " + N;
        executeQuery(query, report);
        return report.toString();
    }

    public String getTopNPopulatedCapitalCitiesInContinent(String continent, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Capital Cities in Continent: ").append(continent).append(" ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC LIMIT " + N;
        executeQuery(query, report, continent);
        return report.toString();
    }

    public String getTopNPopulatedCapitalCitiesInRegion(String region, int N) {
        StringBuilder report = new StringBuilder();
        report.append("=== Top ").append(N).append(" Populated Capital Cities in Region: ").append(region).append(" ===\n");
        String query = "SELECT city.Name, country.Name as Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC LIMIT " + N;
        executeQuery(query, report, region);
        return report.toString();
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

    public String getContinentPopulationReports(String continent) {
        StringBuilder report = new StringBuilder();
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
        executeQuery(query, report, continent);
        return report.toString();
    }


    public String getRegionPopulationReports(String region) {
        StringBuilder report = new StringBuilder();
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
        executeQuery(query, report, region);
        return report.toString();
    }


    public String getCountryPopulationReports(String country) {
        StringBuilder report = new StringBuilder();
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
                "WHERE country.Name = ? ";
        executeQuery(query, report, country);
        return report.toString();
    }

      /*
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ADDITIONAL REPORTS:

    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    */

    public String getWorldPopulation() {
        StringBuilder report = new StringBuilder();
        report.append("=== World Population Report ===\n");
        String query = "SELECT SUM(Population) AS WorldPopulation FROM country";
        executeQuery(query, report);
        return report.toString();
    }

    public String getContinentPopulation(String continent) {
        StringBuilder report = new StringBuilder();
        report.append("=== Population Report for Continent: ").append(continent).append(" ===\n");
        String query = "SELECT SUM(Population) AS ContinentPopulation FROM country WHERE Continent = ?";
        executeQuery(query, report, continent);
        return report.toString();
    }

    public String getRegionPopulation(String region) {
        StringBuilder report = new StringBuilder();
        report.append("=== Population Report for Region: ").append(region).append(" ===\n");
        String query = "SELECT SUM(Population) AS RegionPopulation FROM country WHERE Region = ?";
        executeQuery(query, report, region);
        return report.toString();
    }

    public String getCountryPopulation(String country) {
        StringBuilder report = new StringBuilder();
        report.append("=== Population Report for Country: ").append(country).append(" ===\n");
        String query = "SELECT Population AS CountryPopulation FROM country WHERE Name = ?";
        executeQuery(query, report, country);
        return report.toString();
    }

    public String getDistrictPopulation(String district) {
        StringBuilder report = new StringBuilder();
        report.append("=== Population Report for District: ").append(district).append(" ===\n");
        String query = "SELECT SUM(Population) AS DistrictPopulation FROM city WHERE District = ?";
        executeQuery(query, report, district);
        return report.toString();
    }

    public String getCityPopulation(String city) {
        StringBuilder report = new StringBuilder();
        report.append("=== Population Report for City: ").append(city).append(" ===\n");
        String query = "SELECT Population AS CityPopulation FROM city WHERE Name = ?";
        executeQuery(query, report, city);
        return report.toString();
    }

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