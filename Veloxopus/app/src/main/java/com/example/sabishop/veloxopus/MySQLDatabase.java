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
///        connection = DriverManager.getConnection("jdbc:mysql://107.180.47.119:3306/QuickJobDatabase",
///                "QuickJobUser", "QJUuosibe066#");

        connection = DriverManager.getConnection(
                "jdbc:mysql://107.180.47.119:3306/QuickJobDatabase?useUnicode=true&characterEncoding=utf-8" ,
                "QuickJobUser" , "QJUuosibe066#");

        System.out.println("Connected to database.");
        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        System.out.println("Created statement.");
    }
    protected void CloseDBConnection() throws SQLException {
        connection.close();
    }

    void RemoveUser(String email) throws SQLException , Exception {
        OpenDBConnection();
        if (1 != stmt.executeUpdate("DELETE FROM USER WHERE email='" + email + "';")) {
            throw new Exception("Failed to delete user " + email + " from database!");
        }
        CloseDBConnection();
    }

    void AddUser(String email , String name , String pword) throws SQLException , Exception {
        OpenDBConnection();
        results = stmt.executeQuery("SELECT email FROM USER WHERE email='" + email + "'");
        if (results.next()) {
            throw new Exception("User already in database!");
        }
        else {
            if (1 != stmt.executeUpdate("INSERT INTO USER VALUES('" + email + "' , '" + name + "' , '" + pword + "');")) {
                throw new Exception("Could not insert user " + email + " into database");
            }
        }
        CloseDBConnection();
    }



}
