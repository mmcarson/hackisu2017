package com.example.sabishop.veloxopus;

import android.os.StrictMode;

import java.sql.*;

/**
 * Created by Marc on 2/25/2017.
 */

public class MySQLDatabase {

    protected Connection connection;
    protected Statement stmt;
    protected ResultSet results;

    MySQLDatabase() throws ClassNotFoundException {
        SetupDatabase();
    }

    protected void SetupDatabase() throws ClassNotFoundException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.jdbc.Driver");
    }

    protected void OpenDBConnection() throws SQLException {
        /// jdbc:mysql://hostname:port/databasename
        connection = DriverManager.getConnection("jdbc:mysql://107.180.47.119:3306/QuickJobDatabase",
                "QuickJobUser", "QJUuosibe066#");
        stmt = connection.createStatement();
    }
    protected void CloseDBConnection() throws SQLException {
        connection.close();
    }

    void AddUser(String email , String name , String pword) throws SQLException {
        OpenDBConnection();
        results = stmt.executeQuery("SELECT email FROM USER WHERE email='" + email + "'");
        if (results.)
        CloseDBConnection();
    }



}
