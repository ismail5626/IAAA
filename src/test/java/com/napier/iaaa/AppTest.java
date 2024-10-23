package com.napier.iaaa;

// Import necessary JUnit classes for testing
import org.junit.jupiter.api.BeforeAll; // Annotation for a method that runs before all tests
import org.junit.jupiter.api.Test; // Annotation to indicate a test method

// Class declaration for AppTest, which contains unit tests for the App class
public class AppTest
{
    // Static variable to hold an instance of the App class
    static App app;

    // This method is annotated with @BeforeAll, indicating it runs once before all tests
    @BeforeAll
    static void init()
    {
        // Initialize the app instance before running any tests
        app = new App();
    }

    // Test method to verify behavior of printTableNames method
    @Test
    void printTableNamesTest() {
        // To ensure the method can handle various scenarios, we might use mocking for database interactions
        // Here, we'll just invoke the method and expect it to run without exceptions

        // Call the printTableNames method on the app instance
        app.printTableNames();

        // Assertions can be added here based on expected output or state
        // Since this method prints directly to the console, we would typically want to capture the output
        // However, for the sake of this simple test, we are only checking if it executes without exceptions
    }
}