package com.napier.iaaa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetAllCapitalCitiesByPopulationTest {

    @Test
    public void testGetAllCapitalCitiesByPopulation() {
        App app = new App();
        String result = app.getAllCapitalCitiesByPopulation();
        assertNotNull(result);
        assertTrue(result.contains("=== All Capital Cities in the World by Largest Population to Smallest ==="));
    }
}
