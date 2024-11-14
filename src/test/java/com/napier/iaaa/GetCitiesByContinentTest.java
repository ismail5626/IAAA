package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetCitiesByContinentTest {

    @Test
    public void testGetCitiesByContinent() {
        App app = new App();
        String result = app.getCitiesByContinent("Asia");
        assertNotNull(result);
        assertTrue(result.contains("=== Countries in Continent: Asia ==="));
    }

    @Test
    public void testGetCitiesByContinentEmpty() {
        App app = new App();
        String result = app.getCitiesByContinent("");
        assertNotNull(result);
        assertTrue(result.contains("=== Countries in Continent:  ==="));
    }

    @Test
    public void testGetCitiesByContinentNonExistent() {
        App app = new App();
        String result = app.getCitiesByContinent("Atlantis");
        assertNotNull(result);
        assertTrue(result.contains("=== Countries in Continent: Atlantis ==="));
        // Assuming no cities should be found
        assertFalse(result.contains("Name:"));
    }

    @Test
    public void testGetCitiesByContinentNull() {
        App app = new App();
        String result = app.getCitiesByContinent(null);
        assertNotNull(result);
        // Depending on how null is handled, adjust the assertions
        assertTrue(result.contains("=== Countries in Continent: null ==="));
    }
}
