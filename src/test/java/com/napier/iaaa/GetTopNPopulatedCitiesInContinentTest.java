package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetTopNPopulatedCitiesInContinentTest {

    @Test
    public void testGetTopNPopulatedCitiesInContinent() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInContinent("Asia", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Cities in Continent: Asia ==="));
    }

    @Test
    public void testGetTopNPopulatedCitiesInContinentEmpty() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInContinent("", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Cities in Continent:  ==="));
    }

    @Test
    public void testGetTopNPopulatedCitiesInContinentNonExistent() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInContinent("Atlantis", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Cities in Continent: Atlantis ==="));
        // Assuming no cities should be found
        assertFalse(result.contains("Name:"));
    }

    @Test
    public void testGetTopNPopulatedCitiesInContinentNull() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInContinent(null, 5);
        assertNotNull(result);
        // Depending on how null is handled, adjust the assertions
        assertTrue(result.contains("=== Top 5 Populated Cities in Continent: null ==="));
    }
}
