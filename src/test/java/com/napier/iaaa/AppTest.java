package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    @Test
    public void testApp() {
        assertEquals(1, 1, "Simple Test");
    }

    @Test
    public void testGetCitiesByRegion() {
        App app = new App();
        String result = app.getCitiesByRegion("Middle East");
        assertNotNull(result);
        assertTrue(result.contains("=== All Cities in Region: Middle East by Largest Population to Smallest ==="));
    }

}

