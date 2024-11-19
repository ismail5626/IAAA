package com.napier.iaaa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);

    }

    @Test
    void testGetAllCountriesByPopulation() {
        // Test the method for getting all countries by population.
        String result = app.getAllCountriesByPopulation();

        // Verify that the result is not null
        assertNotNull(result, "Result should not be null");

        // Verify that the result contains population data
        assertTrue(result.contains("Population"), "Result should contain 'Population'");

        // Optionally, verify specific countries or population numbers
        assertTrue(result.contains("China"), "Result should contain 'China'");
        assertTrue(result.contains("India"), "Result should contain 'India'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetCountriesByContinent() {
        // Test the method for getting countries by continent.
        String continent = "Asia";  // Example continent
        String result = app.getCountriesByContinent(continent);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(continent), "Result should contain the continent name");

        // Verify that at least one country is included
        assertTrue(result.contains("China"), "Result should contain 'China'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }

    @Test
    void testGetCountriesByRegion() {
        // Assume the App class has a method to get countries by region.
        String region = "Middle East";  // Use a region that exists in your database
        String result = app.getCountriesByRegion(region);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");

        // Verify that the result contains the region name (indicating that it's querying for the correct region)
        assertTrue(result.contains("Middle East"), "Result should contain the region name");

        // Verify that the result contains at least one country (assuming your database has countries in the region)
        assertTrue(result.contains("Syria") || result.contains("Jordan"), "Result should contain countries from the region");

        // Optionally, if you have specific countries, you can check their presence
        assertTrue(result.contains("Georgia"), "Result should contain 'India'");
        assertTrue(result.contains("Armenia"), "Result should contain 'China'");
    }

    @Test
    void testGetTopNPopulatedCountries() {
        // Test the method for getting the top N populated countries globally.
        int N = 5;
        String result = app.getTopNPopulatedCountries(N);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Population"), "Result should contain 'Population'");

        // Verify that the result contains top countries by population
        assertTrue(result.contains("China"), "Result should contain 'China'");
        assertTrue(result.contains("India"), "Result should contain 'India'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }

    @Test
    void testGetTopNPopulatedCountriesInContinent() {
        // Test the method for getting the top N populated countries in a continent.
        String continent = "Asia";
        int N = 5;
        String result = app.getTopNPopulatedCountriesInContinent(continent, N);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(continent), "Result should contain the continent name");

        // Verify that the result contains top countries by population
        assertTrue(result.contains("China"), "Result should contain 'China'");
        assertTrue(result.contains("India"), "Result should contain 'India'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetTopNPopulatedCountriesInRegion() {
        // Test the method for getting the top N populated countries in a region.
        String region = "Middle East";
        int N = 5;
        String result = app.getTopNPopulatedCountriesInRegion(region, N);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(region), "Result should contain the region name");

        // Verify that the result contains top countries by population
        assertTrue(result.contains("Turkey"), "Result should contain 'Turkey'");
        assertTrue(result.contains("Saudi Arabia"), "Result should contain 'Saudi Arabia'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetAllCitiesByPopulation() {
        // Test the method for getting all cities by population.
        String result = app.getAllCitiesByPopulation();

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Population"), "Result should contain 'Population'");

        // Verify that at least one city is included
        assertTrue(result.contains("Tokyo"), "Result should contain 'Tokyo'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetCitiesByContinent() {
        // Test the method for getting cities by continent.
        String continent = "Asia";
        String result = app.getCitiesByContinent(continent);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(continent), "Result should contain the continent name");

        // Verify that at least one city is included
        assertTrue(result.contains("Kuwait"), "Result should contain 'Kuwait'");
        assertTrue(result.contains("Thimphu"), "Result should contain 'Thimphu'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetCitiesByRegion() {
        // Test the method for getting cities by region.
        String region = "Middle East";
        String result = app.getCitiesByRegion(region);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(region), "Result should contain the region name");

        // Verify that at least one city is included
        assertTrue(result.contains("Dubai"), "Result should contain 'Dubai'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetCitiesByCountry() {
        // Test the method for getting cities by country.
        String country = "Syria";
        String result = app.getCitiesByCountry(country);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(country), "Result should contain the country name");

        // Verify that at least one city is included
        assertTrue(result.contains("Damascus"), "Result should contain 'Damascus'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetCitiesByDistrict() {
        // Test the method for getting cities by district.
        String district = "Dubai";  // Example district
        String result = app.getCitiesByDistrict(district);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(district), "Result should contain the district name");

        // Verify that at least one city is included
        assertTrue(result.contains("Dubai"), "Result should contain 'Dubai'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetTopNPopulatedCities() {
        // Test the method for getting the top N populated cities globally.
        int N = 5;
        String result = app.getTopNPopulatedCities(N);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Population"), "Result should contain 'Population'");

        // Verify that the result contains top cities by population
        assertTrue(result.contains("Shanghai"), "Result should contain 'Shanghai'");
        assertTrue(result.contains("Jakarta"), "Result should contain 'Jakarta'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetTopNPopulatedCitiesInContinent() {
        // Test the method for getting the top N populated cities in a continent.
        String continent = "South America";
        int N = 5;
        String result = app.getTopNPopulatedCitiesInContinent(continent, N);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(continent), "Result should contain the continent name");

        // Verify that the result contains top cities by population
        assertTrue(result.contains("São Paulo"), "Result should contain 'São Paulo'");
        assertTrue(result.contains("Rio de Janeiro"), "Result should contain 'Rio de Janeiro'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetTopNPopulatedCitiesInRegion() {
        // Test the method for getting the top N populated cities in a region.
        String region = "Caribbean";
        int N = 5;
        String result = app.getTopNPopulatedCitiesInRegion(region, N);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(region), "Result should contain the region name");

        // Verify that the result contains top cities by population
        assertTrue(result.contains("La Habana"), "Result should contain 'La Habana'");
        assertTrue(result.contains("San Juan"), "Result should contain 'San Juan'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetTopNPopulatedCitiesInCountry() {
        // Test the method for getting the top N populated cities in a country.
        String country = "Austria";
        int N = 5;
        String result = app.getTopNPopulatedCitiesInCountry(country, N);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(country), "Result should contain the country name");

        // Verify that the result contains top cities by population
        assertTrue(result.contains("Linz"), "Result should contain 'Linz'");
        assertTrue(result.contains("Graz"), "Result should contain 'Graz'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetTopNPopulatedCitiesInDistrict() {
        // Test the method for getting the top N populated cities in a district.
        String district = "Noord-Brabant";
        int N = 5;
        String result = app.getTopNPopulatedCitiesInDistrict(district, N);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(district), "Result should contain the district name");

        // Verify that the result contains top cities by population
        assertTrue(result.contains("Eindhoven"), "Result should contain 'Eindhoven'");
        assertTrue(result.contains("Tilburg"), "Result should contain 'Tilburg'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetAllCapitalCitiesByPopulation() {
        // Test the method for getting all capital cities by population.
        String result = app.getAllCapitalCitiesByPopulation();

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Capital"), "Result should contain 'Capital'");

        // Verify that at least one capital city is included
        assertTrue(result.contains("Tokyo"), "Result should contain 'Tokyo'");
        assertTrue(result.contains("Washington"), "Result should contain 'Washington'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetCapitalCitiesByContinent() {
        // Test the method for getting capital cities by continent.
        String continent = "Asia";
        String result = app.getCapitalCitiesByContinent(continent);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(continent), "Result should contain the continent name");

        // Verify that the result contains capital cities from Asia
        assertTrue(result.contains("Tokyo"), "Result should contain 'Tokyo'");
        assertTrue(result.contains("New Delhi"), "Result should contain 'New Delhi'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetCapitalCitiesByRegion() {
        // Test the method for getting capital cities by region.
        String region = "Western Europe";
        String result = app.getCapitalCitiesByRegion(region);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(region), "Result should contain the region name");

        // Verify that the result contains capital cities from the region
        assertTrue(result.contains("Paris"), "Result should contain 'Paris'");
        assertTrue(result.contains("Berlin"), "Result should contain 'Berlin'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetTopNPopulatedCapitalCities() {
        // Test the method for getting the top N populated capital cities globally.
        int N = 5;
        String result = app.getTopNPopulatedCapitalCities(N);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Population"), "Result should contain 'Population'");

        // Verify that the result contains top capital cities by population
        assertTrue(result.contains("Seoul"), "Result should contain 'Seoul'");
        assertTrue(result.contains("Tokyo"), "Result should contain 'Tokyo'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetTopNPopulatedCapitalCitiesInContinent() {
        // Test the method for getting the top N populated capital cities in a continent.
        String continent = "Africa";
        int N = 5;
        String result = app.getTopNPopulatedCapitalCitiesInContinent(continent, N);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(continent), "Result should contain the continent name");

        // Verify that the result contains top capital cities by population
        assertTrue(result.contains("Cairo"), "Result should contain 'Cairo'");
        assertTrue(result.contains("Kinshasa"), "Result should contain 'Kinshasa'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetContinentPopulationReports() {
        // Test the method for getting population reports by continent
        String result = app.getContinentPopulationReports();

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Continent"), "Result should contain 'Continent'");
        assertTrue(result.contains("TotalPopulation"), "Result should contain 'TotalPopulation'");
        assertTrue(result.contains("PopulationInCities"), "Result should contain 'PopulationInCities'");
        assertTrue(result.contains("PopulationNotInCities"), "Result should contain 'PopulationNotInCities'");

        // Example check: Verify that a continent (e.g., 'Asia') is included in the report
        assertTrue(result.contains("Asia"), "Result should contain 'Asia'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetRegionPopulationReports() {
        // Test the method for getting population reports by region
        String result = app.getRegionPopulationReports();

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Region"), "Result should contain 'Region'");
        assertTrue(result.contains("TotalPopulation"), "Result should contain 'TotalPopulation'");
        assertTrue(result.contains("PopulationInCities"), "Result should contain 'PopulationInCities'");
        assertTrue(result.contains("PopulationNotInCities"), "Result should contain 'PopulationNotInCities'");

        // Example check: Verify that a region (e.g., 'Western Europe') is included in the report
        assertTrue(result.contains("Western Europe"), "Result should contain 'Western Europe'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetCountryPopulationReports() {
        // Test the method for getting population reports by country
        String result = app.getCountryPopulationReports();

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Country"), "Result should contain 'Country'");
        assertTrue(result.contains("TotalPopulation"), "Result should contain 'TotalPopulation'");
        assertTrue(result.contains("PopulationInCities"), "Result should contain 'PopulationInCities'");
        assertTrue(result.contains("PopulationNotInCities"), "Result should contain 'PopulationNotInCities'");

        // Example check: Verify that a country (e.g., 'Germany') is included in the report
        assertTrue(result.contains("Germany"), "Result should contain 'Germany'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }

    @Test
    void testGetWorldPopulation() {
        // Test the method for getting the world population report
        String result = app.getWorldPopulation();

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("World Population"), "Result should contain 'World Population'");

        // Example check: Verify that the world population is mentioned
        assertTrue(result.contains("6078749450"), "Result should contain the global population count");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetContinentPopulation() {
        // Test the method for getting the population of a specific continent
        String continent = "Oceania";
        String result = app.getContinentPopulation(continent);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Continent"), "Result should contain 'Continent'");
        assertTrue(result.contains("Oceania"), "Result should contain 'Oceania'");

        // Example check: Verify that the result contains population data for the continent
        assertTrue(result.contains("30401150"), "Result should contain population data for Oceania");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetRegionPopulation() {
        // Test the method for getting the population of a specific region
        String region = "Micronesia";
        String result = app.getRegionPopulation(region);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Region"), "Result should contain 'Region'");
        assertTrue(result.contains("Micronesia"), "Result should contain 'Micronesia'");

        // Example check: Verify that the result contains population data for the region
        assertTrue(result.contains("543000"), "Result should contain population data for Micronesia");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetCountryPopulation() {
        // Test the method for getting the population of a specific country
        String country = "Bhutan";
        String result = app.getCountryPopulation(country);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Country"), "Result should contain 'Country'");
        assertTrue(result.contains("Bhutan"), "Result should contain 'Bhutan'");

        // Example check: Verify that the result contains population data for the country
        assertTrue(result.contains("2124000"), "Result should contain population data for Bhutan");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetDistrictPopulation() {
        // Test the method for getting the population of a specific district
        String district = "Utrecht";
        String result = app.getDistrictPopulation(district);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("District"), "Result should contain 'District'");
        assertTrue(result.contains("Utrecht"), "Result should contain 'Utrecht'");

        // Example check: Verify that the result contains population data for the district
        assertTrue(result.contains("360593"), "Result should contain population data for Utrecht");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }
    @Test
    void testGetCityPopulation() {
        // Test the method for getting the population of a specific city
        String city = "Tokyo";
        String result = app.getCityPopulation(city);

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("City"), "Result should contain 'City'");
        assertTrue(result.contains("Tokyo"), "Result should contain 'Tokyo'");

        // Example check: Verify that the result contains population data for the city
        assertTrue(result.contains("7980230"), "Result should contain population data for Tokyo");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Spacing between reports
    }

    @Test
    void testGetLanguageSpeakersReport() {
        // Test the method for getting the language speakers report
        String result = app.getLanguageSpeakersReport();

        // Verify that the result is not null and contains expected data
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains("Language"), "Result should contain 'Language'");
        assertTrue(result.contains("Speakers"), "Result should contain 'Speakers'");
        assertTrue(result.contains("Percentage_Of_World"), "Result should contain 'Percentage_Of_World'");

        // Example check: Verify that the report includes specific languages
        assertTrue(result.contains("English"), "Result should contain 'English'");
        assertTrue(result.contains("Chinese"), "Result should contain 'Mandarin'");

        System.out.println(result); // Print the result as per report format
        System.out.println(); // Final spacing
    }

}


