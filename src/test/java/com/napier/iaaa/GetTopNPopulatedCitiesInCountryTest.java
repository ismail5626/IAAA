package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetTopNPopulatedCitiesInCountryTest {

    @Test
    public void testGetTopNPopulatedCitiesInCountry() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInCountry("Austria", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Cities in Country: Austria ==="));
    }

    @Test
    public void testGetTopNPopulatedCitiesInCountryEmpty() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInCountry("", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Cities in Country:  ==="));
    }

    @Test
    public void testGetTopNPopulatedCitiesInCountryNonExistent() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInCountry("Atlantis", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Cities in Country: Atlantis ==="));
        // Assuming no cities should be found
        assertFalse(result.contains("Name:"));
    }

    @Test
    public void testGetTopNPopulatedCitiesInCountryNull() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInCountry(null, 5);
        assertNotNull(result);
        // Depending on how null is handled, adjust the assertions
        assertTrue(result.contains("=== Top 5 Populated Cities in Country: null ==="));
    }

    @Test
    public void testGetTopNPopulatedCitiesInCountryZero() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInCountry("Austria", 0);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 0 Populated Cities in Country: Austria ==="));
    }

    @Test
    public void testGetTopNPopulatedCitiesInCountryNegative() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInCountry("Austria", -1);
        assertNotNull(result);
        assertTrue(result.contains("=== Top -1 Populated Cities in Country: Austria ==="));
    }
}
