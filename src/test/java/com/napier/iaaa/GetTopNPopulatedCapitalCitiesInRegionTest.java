package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetTopNPopulatedCapitalCitiesInRegionTest {

    @Test
    public void testGetTopNPopulatedCapitalCitiesInRegion() {
        App app = new App();
        String result = app.getTopNPopulatedCapitalCitiesInRegion("Western Europe", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Capital Cities in Region: Western Europe ==="));
    }

    @Test
    public void testGetTopNPopulatedCapitalCitiesInRegionEmpty() {
        App app = new App();
        String result = app.getTopNPopulatedCapitalCitiesInRegion("", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Capital Cities in Region:  ==="));
    }

    @Test
    public void testGetTopNPopulatedCapitalCitiesInRegionNonExistent() {
        App app = new App();
        String result = app.getTopNPopulatedCapitalCitiesInRegion("Narnia", 5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Capital Cities in Region: Narnia ==="));
        // Assuming no cities should be found
        assertFalse(result.contains("Name:"));
    }

    @Test
    public void testGetTopNPopulatedCapitalCitiesInRegionNull() {
        App app = new App();
        String result = app.getTopNPopulatedCapitalCitiesInRegion(null, 5);
        assertNotNull(result);
        // Depending on how null is handled, adjust the assertions
        assertTrue(result.contains("=== Top 5 Populated Capital Cities in Region: null ==="));
    }

    @Test
    public void testGetTopNPopulatedCapitalCitiesInRegionZero() {
        App app = new App();
        String result = app.getTopNPopulatedCapitalCitiesInRegion("Western Europe", 0);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 0 Populated Capital Cities in Region: Western Europe ==="));
    }

    @Test
    public void testGetTopNPopulatedCapitalCitiesInRegionNegative() {
        App app = new App();
        String result = app.getTopNPopulatedCapitalCitiesInRegion("Western Europe", -1);
        assertNotNull(result);
        assertTrue(result.contains("=== Top -1 Populated Capital Cities in Region: Western Europe ==="));
    }
}