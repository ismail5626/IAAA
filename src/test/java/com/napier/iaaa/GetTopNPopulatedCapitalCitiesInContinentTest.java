package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetTopNPopulatedCapitalCitiesInContinentTest {

    @Test
    public void testGetTopNPopulatedCapitalCitiesInContinent() {
        App app = new App();
        String result = app.getTopNPopulatedCapitalCitiesInContinent("Africa", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Capital Cities in Continent: Africa ==="));
    }

    @Test
    public void testGetTopNPopulatedCapitalCitiesInContinentEmpty() {
        App app = new App();
        String result = app.getTopNPopulatedCapitalCitiesInContinent("", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Capital Cities in Continent:  ==="));
    }

    @Test
    public void testGetTopNPopulatedCapitalCitiesInContinentNonExistent() {
        App app = new App();
        String result = app.getTopNPopulatedCapitalCitiesInContinent("Atlantis", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Capital Cities in Continent: Atlantis ==="));
        // Assuming no cities should be found
        assertFalse(result.contains("Name:"));
    }

    @Test
    public void testGetTopNPopulatedCapitalCitiesInContinentNull() {
        App app = new App();
        String result = app.getTopNPopulatedCapitalCitiesInContinent(null, 5);
        assertNotNull(result);
        // Depending on how null is handled, adjust the assertions
        assertTrue(result.contains("=== Top 5 Populated Capital Cities in Continent: null ==="));
    }
}