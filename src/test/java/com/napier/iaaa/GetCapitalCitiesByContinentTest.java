package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetCapitalCitiesByContinentTest {

    @Test
    public void testGetCapitalCitiesByContinent() {
        App app = new App();
        String result = app.getCapitalCitiesByContinent("Asia");
        assertNotNull(result);
        assertTrue(result.contains("=== Capital Cities in Continent: Asia by Largest Population to Smallest ==="));
    }

    @Test
    public void testGetCapitalCitiesByContinentEmpty() {
        App app = new App();
        String result = app.getCapitalCitiesByContinent("");
        assertNotNull(result);
        assertTrue(result.contains("=== Capital Cities in Continent:  by Largest Population to Smallest ==="));
    }

    @Test
    public void testGetCapitalCitiesByContinentNonExistent() {
        App app = new App();
        String result = app.getCapitalCitiesByContinent("Atlantis");
        assertNotNull(result);
        assertTrue(result.contains("=== Capital Cities in Continent: Atlantis by Largest Population to Smallest ==="));
        // Assuming no cities should be found
        assertFalse(result.contains("Name:"));
    }

    @Test
    public void testGetCapitalCitiesByContinentNull() {
        App app = new App();
        String result = app.getCapitalCitiesByContinent(null);
        assertNotNull(result);
        // Depending on how null is handled, adjust the assertions
        assertTrue(result.contains("=== Capital Cities in Continent: null by Largest Population to Smallest ==="));
    }
}