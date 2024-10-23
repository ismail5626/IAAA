package com.napier.iaaa;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private App app;

    @BeforeEach
    void setUp() {
        app = new App(); // Initialize the App instance
    }

    @Test
    void testGetAllCountriesByPopulation() {
        String result = app.getAllCountriesByPopulation();
        assertNotNull(result, "The report should not be null");
        assertTrue(result.contains("=== All Countries by Population ==="), "Report header should be present");
    }

    @Test
    void testGetCountriesByContinent() {
        String result = app.getCountriesByContinent("Asia");
        assertNotNull(result, "The report should not be null");
        assertTrue(result.contains("=== Countries in Continent: Asia ==="), "Report header should be present");
    }

    @Test
    void testGetCountriesByRegion() {
        String result = app.getCountriesByRegion("Europe");
        assertNotNull(result, "The report should not be null");
        assertTrue(result.contains("=== Countries in Region: Europe ==="), "Report header should be present");
    }

    @Test
    void testGetTopNPopulatedCountries() {
        String result = app.getTopNPopulatedCountries(5);
        assertNotNull(result, "The report should not be null");
        assertTrue(result.contains("=== Top 5 Populated Countries ==="), "Report header should be present");
    }

    @Test
    void testGetTopNPopulatedCountriesInContinent() {
        String result = app.getTopNPopulatedCountriesInContinent("Asia", 5);
        assertNotNull(result, "The report should not be null");
        assertTrue(result.contains("=== Top 5 Populated Countries in Continent: Asia ==="), "Report header should be present");
    }

    @AfterEach
    void tearDown() {
        app = null; // Clean up
    }
}
