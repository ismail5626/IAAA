package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetCitiesByDistrictTest {

    @Test
    public void testGetCitiesByDistrict() {
        App app = new App();
        String result = app.getCitiesByDistrict("Dubai");
        assertNotNull(result);
        assertTrue(result.contains("=== All Cities in District: Dubai by Largest Population to Smallest ==="));
    }

    @Test
    public void testGetCitiesByDistrictEmpty() {
        App app = new App();
        String result = app.getCitiesByDistrict("");
        assertNotNull(result);
        assertTrue(result.contains("=== All Cities in District:  by Largest Population to Smallest ==="));
    }

    @Test
    public void testGetCitiesByDistrictNonExistent() {
        App app = new App();
        String result = app.getCitiesByDistrict("Atlantis");
        assertNotNull(result);
        assertTrue(result.contains("=== All Cities in District: Atlantis by Largest Population to Smallest ==="));
        // Assuming no cities should be found
        assertFalse(result.contains("Name:"));
    }

    @Test
    public void testGetCitiesByDistrictNull() {
        App app = new App();
        String result = app.getCitiesByDistrict(null);
        assertNotNull(result);
        // Depending on how null is handled, adjust the assertions
        assertTrue(result.contains("=== All Cities in District: null by Largest Population to Smallest ==="));
    }
}
