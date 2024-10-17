package com.napier.iaaa;

import java.sql.*;

public class App
{
    static final String QUERY1 = "SHOW TABLES";

    public static void main (String [] args) {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Open a connection
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://db:3306/test", "root", "root");
             Statement stmt = conn.createStatement();
        ) {

            ResultSet rs=stmt.executeQuery(QUERY1);
            // Extract data from result set
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}