const readline = require('readline');
const {
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
} = require('./queries');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

// Display menu options to the user
const showMenu = () => {
    console.log('\nPopulation Reports');
    console.log('1. All countries by population');
    console.log('2. Countries by continent');
    console.log('3. Top N populated countries');
    console.log('4. All cities by population');
    console.log('5. Cities by continent');
    console.log('6. Top N populated cities');
    console.log('7. Capital cities by population');
    console.log('8. Population distribution');
    console.log('9. World/Continent/Region population statistics');
    console.log('10. Language reports (Chinese, English, Hindi, Spanish, Arabic)');
    console.log('11. Exit');
    rl.question('Select an option: ', handleMenuSelection);
};

// Handle user's menu selection
const handleMenuSelection = (choice) => {
    switch (choice) {
        case '1':
            getAllCountriesByPopulation((err, results) => {
                if (err) {
                    console.error('Error fetching data:', err);
                } else {
                    console.log(formatResults(results));
                }
                showMenu();
            });
            break;
        case '2':
            rl.question('Enter continent: ', (continent) => {
                getCountriesByContinent(continent, (err, results) => {
                    if (err) {
                        console.error('Error fetching data:', err);
                    } else {
                        console.log(formatResults(results));
                    }
                    showMenu();
                });
            });
            break;
        case '3':
            rl.question('Enter number of top countries: ', (n) => {
                getTopNCountries(parseInt(n), (err, results) => {
                    if (err) {
                        console.error('Error fetching data:', err);
                    } else {
                        console.log(formatResults(results));
                    }
                    showMenu();
                });
            });
            break;
        case '4':
            getAllCitiesByPopulation((err, results) => {
                if (err) {
                    console.error('Error fetching data:', err);
                } else {
                    console.log(formatResults(results));
                }
                showMenu();
            });
            break;
        case '5':
            rl.question('Enter continent: ', (continent) => {
                getCitiesByContinent(continent, (err, results) => {
                    if (err) {
                        console.error('Error fetching data:', err);
                    } else {
                        console.log(formatResults(results));
                    }
                    showMenu();
                });
            });
            break;
        case '6':
            rl.question('Enter number of top cities: ', (n) => {
                getTopNCities(parseInt(n), (err, results) => {
                    if (err) {
                        console.error('Error fetching data:', err);
                    } else {
                        console.log(formatResults(results));
                    }
                    showMenu();
                });
            });
            break;
        case '7':
            getCapitalCitiesByPopulation((err, results) => {
                if (err) {
                    console.error('Error fetching data:', err);
                } else {
                    console.log(formatResults(results));
                }
                showMenu();
            });
            break;
        case '8':
            getPopulationDistribution((err, results) => {
                if (err) {
                    console.error('Error fetching data:', err);
                } else {
                    console.log(formatResults(results));
                }
                showMenu();
            });
            break;
        case '9':
            getWorldContinentRegionPopulation((err, results) => {
                if (err) {
                    console.error('Error fetching data:', err);
                } else {
                    console.log(formatResults(results));
                }
                showMenu();
            });
            break;
        case '10':
            getLanguageStatistics((err, results) => {
                if (err) {
                    console.error('Error fetching data:', err);
                } else {
                    console.log(formatResults(results));
                }
                showMenu();
            });
            break;
        case '11':
            console.log('Exiting...');
            rl.close();
            process.exit(0);
            break;
        default:
            console.log('Invalid option. Please try again.');
            showMenu();
    }
};

// Function to format results for better display
const formatResults = (results) => {
    return results.map(result => {
        // Check if it's a city result
        if ('City' in result && 'Population' in result) {
            return `${result.City} (${result.Code || 'N/A'}) - ${result.Population} people`;
        }
        // Check if it's a language result
        else if ('Language' in result && 'Speakers' in result) {
            return `${result.Language} - ${result.Speakers} speakers`;
        }
        // Default case (countries or similar)
        else if ('Name' in result && 'Population' in result) {
            return `${result.Name} (${result.Code || 'N/A'}) - ${result.Population} people`;
        }
        // In case of no matching fields, return empty
        return 'Unresolved data format';
    }).join('\n');
};

// Start the program
showMenu();
