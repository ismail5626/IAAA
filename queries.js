const pool = require('./db');  // Import the MySQL connection pool

// Query to get all countries by population
const getAllCountriesByPopulation = (callback) => {
    const query = `
        SELECT Code, Name, Continent, Region, Population, Capital
        FROM country
        ORDER BY Population DESC;
    `;
    pool.query(query, (err, results) => {
        if (err) {
            return callback(err, null);
        }
        return callback(null, results);
    });
};

// Query to get countries by continent
const getCountriesByContinent = (continent, callback) => {
    const query = `
        SELECT Code, Name, Continent, Region, Population, Capital
        FROM country
        WHERE Continent = ?
        ORDER BY Population DESC;
    `;
    pool.query(query, [continent], (err, results) => {
        if (err) {
            return callback(err, null);
        }
        return callback(null, results);
    });
};

// Query to get top N countries by population
const getTopNCountries = (n, callback) => {
    const query = `
        SELECT Code, Name, Continent, Region, Population, Capital
        FROM country
        ORDER BY Population DESC
        LIMIT ?;
    `;
    pool.query(query, [n], (err, results) => {
        if (err) {
            return callback(err, null);
        }
        return callback(null, results);
    });
};

// Add similar query functions for cities, capital cities, population distribution, and languages...

module.exports = {
    getAllCountriesByPopulation,
    getCountriesByContinent,
    getTopNCountries,
    // Add other exports here as needed...
};
