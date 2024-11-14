package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetCapitalCitiesByRegionTest {

    @Test
    public void testGetCapitalCitiesByRegion() {
        App app = new App();
        String result = app.getCapitalCitiesByRegion("Western Europe");
        assertNotNull(result);
        assertTrue(result.contains("=== Capital Cities in Region: Western Europe by Largest Population to Smallest ==="));
    }

    @Test
    public void testGetCapitalCitiesByRegionEmpty() {
        App app = new App();
        String result = app.getCapitalCitiesByRegion("");
        assertNotNull(result);
        assertTrue(result.contains("=== Capital Cities in Region:  by Largest Population to Smallest ==="));
    }

    @Test
    public void testGetCapitalCitiesByRegionNonExistent() {
        App app = new App();
        String result = app.getCapitalCitiesByRegion("Narnia");
        assertNotNull(result);
        assertTrue(result.contains("=== Capital Cities in Region: Narnia by Largest Population to Smallest ==="));
        // Assuming no cities should be found
        assertFalse(result.contains("Name:"));
    }

    @Test
    public void testGetCapitalCitiesByRegionNull() {
        App app = new App();
        String result = app.getCapitalCitiesByRegion(null);
        assertNotNull(result);
        // Depending on how null is handled, adjust the assertions
        assertTrue(result.contains("=== Capital Cities in Region: null by Largest Population to Smallest ==="));
    }
}