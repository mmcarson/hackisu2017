package com.example.sabishop.veloxopus;

import android.os.StrictMode;

import java.sql.*;
import java.util.ArrayList;

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

    public void RemoveUser(String email) throws SQLException , Exception {
        OpenDBConnection();
        if (1 != stmt.executeUpdate("DELETE FROM USER WHERE email='" + email + "';")) {
            throw new Exception("Failed to delete user " + email + " from database!");
        }
        CloseDBConnection();
    }

    public void AddUser(String email , String name , String pword) throws SQLException , Exception {
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

    public void UpdateProfile(long PID , String new_email , String new_title , String new_category , String new_description,
                              String new_type) throws Exception {
        OpenDBConnection();

        String sql = "UPDATE PROFILE SET Email = '" + new_email + "' , Title = '" + new_title + "' , Category = '" +
                new_category + "' , Description = '" + new_description + "' , Type = '" + new_type + "'" +
                " WHERE PID = " + Long.toString(PID) + ";";

        if (1 != stmt.executeUpdate(sql)) {
            throw new Exception("Failed to update Profile for PID " + Long.toString(PID));
        }

        CloseDBConnection();
    }

    public void AddProfile(String email , String title , String category , String description , String type) throws Exception {
        OpenDBConnection();
        String sql = "INSERT INTO PROFILE VALUES(NULL , '" + email + "' , '" + title + "' , '" + category + "' , '" +
                     description + "' , '" + type + "');";

        if (1 != stmt.executeUpdate(sql)) {
            throw new Exception("Failed to create profile for user " + email);
        }
        CloseDBConnection();
    }

    public ArrayList<Profile> GetAllProfiles() throws SQLException {
        ArrayList<Profile> plist = new ArrayList<Profile>();

        OpenDBConnection();

        String sql = "SELECT * FROM PROFILE;";
        results = stmt.executeQuery(sql);

        while (results.next()) {
            plist.add(GetProfileFromResults(results));
        }

        CloseDBConnection();

        return plist;
    }

    public ArrayList<Profile> GetProfiles(String user_email) throws SQLException {
        OpenDBConnection();

        String sql = "SELECT * FROM PROFILE WHERE email = '" + user_email + "';";
        results = stmt.executeQuery(sql);

        ArrayList<Profile> profiles = new ArrayList<Profile>();

        while (results.next()) {
            profiles.add(GetProfileFromResults(results));
        }

        CloseDBConnection();

        return profiles;
    }

    protected Profile GetProfileFromResults(ResultSet results) throws SQLException {
        Profile p = new Profile("");
        p.profileID = results.getLong(1);
        p.email = results.getString(2);
        p.name = results.getString(3);
        p.category = results.getString(4);
        p.description = results.getString(5);
        p.type = results.getString(6);
        return p;
    }

    public Profile GetProfile(long PID) throws Exception {
        OpenDBConnection();

        String sql = "SELECT * FROM PROFILE WHERE PID = " + Long.toString(PID) + ";";
        results = stmt.executeQuery(sql);

        if (!results.next()) {
            throw new Exception("Failed to find PID " + Long.toString(PID) + " in PROFILE table!");
        }

        Profile p = GetProfileFromResults(results);

        CloseDBConnection();

        return p;
    }

    public void RemoveProfile(long PID) throws Exception {
        OpenDBConnection();

        String sql = "DELETE FROM PROFILE WHERE PID = " + Long.toString(PID) + ";";

        int ret = stmt.executeUpdate(sql);
        if (ret != 1) {
            if (ret > 1) {
                throw new Exception("WARNING : Removed multiple profiles!!!");
            }
            else {
                throw new Exception("Failed to remove PID " + Long.toString(PID));
            }
        }

        CloseDBConnection();
    }

    public boolean VerifyUser(String userEmail , String password) throws SQLException {
        boolean valid = false;

        OpenDBConnection();

        String sql = "SELECT * FROM USER WHERE email = " + userEmail + ";";

        results = stmt.executeQuery(sql);

        if (!results.next())
        {
            valid = false;
        }
        else
        {
            if (results.getString(3).equals(password))
            {
                valid = true;
            }
        }

        CloseDBConnection();

        return valid;
    }

}
