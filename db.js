const mysql = require('mysql2');

// Set up the MySQL connection
const connection = mysql.createConnection({
    host: 'localhost',    // Use 'localhost' if MySQL is running locally
    user: 'root',         // Your MySQL root username
    password: 'example',  // Your MySQL root password
    database: 'world',    // Name of the database you're working with
    port: 3306            // Default MySQL port
});

// Connect to the database
connection.connect((err) => {
    if (err) {
        console.error('Error connecting to MySQL:', err.stack);
        return;
    }
    console.log('Connected to MySQL as id ' + connection.threadId);
});

// Export the connection for use in other files
module.exports = connection;
