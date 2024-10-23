const connection = require('./db'); // Import the MySQL connection

// Query to get all countries by population
const getAllCountriesByPopulation = (callback) => {
    const query = `
        SELECT Code, Name, Continent, Region, Population, Capital
        FROM country
        ORDER BY Population DESC;
    `;
    connection.query(query, (err, results) => {
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
    connection.query(query, [continent], (err, results) => {
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
    connection.query(query, [n], (err, results) => {
        if (err) {
            return callback(err, null);
        }
        return callback(null, results);
    });
};

// Query to get all cities by population
const getAllCitiesByPopulation = (callback) => {
    const query = `
        SELECT city.Name AS City, city.CountryCode AS Code, city.Population
        FROM city
        ORDER BY city.Population DESC;
    `;
    connection.query(query, (err, results) => {
        if (err) {
            return callback(err, null);
        }
        return callback(null, results);
    });
};

// Query to get cities by continent
const getCitiesByContinent = (continent, callback) => {
    const query = `
        SELECT city.Name AS City, city.CountryCode AS Code, city.Population
        FROM city
                 JOIN country ON city.CountryCode = country.Code
        WHERE country.Continent = ?
        ORDER BY city.Population DESC;
    `;
    connection.query(query, [continent], (err, results) => {
        if (err) {
            return callback(err, null);
        }
        return callback(null, results);
    });
};

// Query to get top N cities by population
const getTopNCities = (n, callback) => {
    const query = `
        SELECT city.Name AS City, city.CountryCode AS Code, city.Population
        FROM city
        ORDER BY city.Population DESC
        LIMIT ?;
    `;
    connection.query(query, [n], (err, results) => {
        if (err) {
            return callback(err, null);
        }
        return callback(null, results);
    });
};

// Query to get capital cities by population
const getCapitalCitiesByPopulation = (callback) => {
    const query = `
        SELECT city.Name AS City, country.Code, city.Population
        FROM city
                 JOIN country ON city.ID = country.Capital
        ORDER BY city.Population DESC;
    `;
    connection.query(query, (err, results) => {
        if (err) {
            return callback(err, null);
        }
        return callback(null, results);
    });
};

// Query to get population distribution (people living in cities vs. rural areas)
const getPopulationDistribution = (callback) => {
    const query = `
        SELECT country.Name, country.Population,
               SUM(city.Population) AS CityPopulation,
               (country.Population - SUM(city.Population)) AS RuralPopulation
        FROM country
                 LEFT JOIN city ON city.CountryCode = country.Code
        GROUP BY country.Name, country.Population
        ORDER BY country.Population DESC;
    `;
    connection.query(query, (err, results) => {
        if (err) {
            return callback(err, null);
        }
        return callback(null, results);
    });
};

// Query to get population statistics for world/continent/region
const getWorldContinentRegionPopulation = (callback) => {
    const query = `
        SELECT Continent, SUM(Population) AS Population
        FROM country
        GROUP BY Continent
        UNION
        SELECT 'World' AS Continent, SUM(Population) AS Population
        FROM country;
    `;
    connection.query(query, (err, results) => {
        if (err) {
            return callback(err, null);
        }
        return callback(null, results);
    });
};

// Query to get language statistics
const getLanguageStatistics = (callback) => {
    const query = `
        SELECT countrylanguage.Language,
               SUM(country.Population * countrylanguage.Percentage / 100) AS Speakers
        FROM countrylanguage
                 JOIN country ON country.Code = countrylanguage.CountryCode
        WHERE countrylanguage.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic')
        GROUP BY countrylanguage.Language
        ORDER BY Speakers DESC;
    `;
    connection.query(query, (err, results) => {
        if (err) {
            return callback(err, null);
        }
        return callback(null, results);
    });
};

// Export the query functions
module.exports = {
    getAllCountriesByPopulation,
    getCountriesByContinent,
    getTopNCountries,
    getAllCitiesByPopulation,
    getCitiesByContinent,
    getTopNCities,
    getCapitalCitiesByPopulation,
    getPopulationDistribution,
    getWorldContinentRegionPopulation,
    getLanguageStatistics
};





