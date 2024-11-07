const mysql = require('mysql2');

// Set up the MySQL connection pool
const pool = mysql.createPool({
    host: 'localhost',    // Use 'localhost' if MySQL is running locally
    user: 'root',         // Your MySQL root username
    password: 'example',  // Your MySQL root password
    database: 'world',    // Name of the database you're working with
    port: 3306,           // Default MySQL port
    waitForConnections: true,
    connectionLimit: 10,
    queueLimit: 0
});

// Export the pool for use in other files
module.exports = pool;




