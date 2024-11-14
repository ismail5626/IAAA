package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetTopNPopulatedCapitalCitiesTest {

    @Test
    public void testGetTopNPopulatedCapitalCities() {
        App app = new App();
        String result = app.getTopNPopulatedCapitalCities(5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Capital Cities in the World ==="));
    }

    @Test
    public void testGetTopNPopulatedCapitalCitiesZero() {
        App app = new App();
        String result = app.getTopNPopulatedCapitalCities(0);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 0 Populated Capital Cities in the World ==="));
    }

    @Test
    public void testGetTopNPopulatedCapitalCitiesNegative() {
        App app = new App();
        String result = app.getTopNPopulatedCapitalCities(-1);
        assertNotNull(result);
        assertTrue(result.contains("=== Top -1 Populated Capital Cities in the World ==="));
    }
}