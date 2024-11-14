package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetTopNPopulatedCitiesInRegionTest {

    @Test
    public void testGetTopNPopulatedCitiesInRegion() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInRegion("Middle East", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Cities in Region: Middle East ==="));
    }

    @Test
    public void testGetTopNPopulatedCitiesInRegionEmpty() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInRegion("", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Cities in Region:  ==="));
    }

    @Test
    public void testGetTopNPopulatedCitiesInRegionNonExistent() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInRegion("Narnia", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Cities in Region: Narnia ==="));
        // Assuming no cities should be found
        assertFalse(result.contains("Name:"));
    }

    @Test
    public void testGetTopNPopulatedCitiesInRegionNull() {
        App app = new App();
        String result = app.getTopNPopulatedCitiesInRegion(null, 5);
        assertNotNull(result);
        // Depending on how null is handled, adjust the assertions
        assertTrue(result.contains("=== Top 5 Populated Cities in Region: null ==="));
    }
}