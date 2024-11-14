package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetTopNPopulatedCitiesTest {

    @Test
    public void testGetTopNPopulatedCities() {
        App app = new App();
        String result = app.getTopNPopulatedCities(5);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 5 Populated Cities in the World ==="));
    }

    @Test
    public void testGetTopNPopulatedCitiesZero() {
        App app = new App();
        String result = app.getTopNPopulatedCities(0);
        assertNotNull(result);
        assertTrue(result.contains("=== Top 0 Populated Cities in the World ==="));
    }

    @Test
    public void testGetTopNPopulatedCitiesNegative() {
        App app = new App();
        String result = app.getTopNPopulatedCities(-1);
        assertNotNull(result);
        assertTrue(result.contains("=== Top -1 Populated Cities in the World ==="));
    }
}