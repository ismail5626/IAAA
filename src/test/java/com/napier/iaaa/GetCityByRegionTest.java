package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetCityByRegionTest {

    @Test
    public void testGetCityByRegion() {
        App app = new App();
        String result = app.getCitiesByRegion("Middle East");
        assertNotNull(result);
        assertTrue(result.contains("=== All Cities in Region: Middle East by Largest Population to Smallest ==="));
    }

    @Test
    public void testGetCityByRegionEmpty() {
        App app = new App();
        String result = app.getCitiesByRegion("");
        assertNotNull(result);
        assertTrue(result.contains("=== All Cities in Region:  by Largest Population to Smallest ==="));
    }

    @Test
    public void testGetCityByRegionNonExistent() {
        App app = new App();
        String result = app.getCitiesByRegion("Narnia");
        assertNotNull(result);
        assertTrue(result.contains("=== All Cities in Region: Narnia by Largest Population to Smallest ==="));
        // Assuming no cities should be found
        assertFalse(result.contains("Name:"));
    }

    @Test
    public void testGetCityByRegionNull() {
        App app = new App();
        String result = app.getCitiesByRegion(null);
        assertNotNull(result);
        // Depending on how null is handled, adjust the assertions
        assertTrue(result.contains("=== All Cities in Region: null by Largest Population to Smallest ==="));
    }
}