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
    protected Statement stmt2;
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
        stmt2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        System.out.println("Created statement.");
    }
    protected void CloseDBConnection() throws SQLException {
        connection.close();
    }

    public void RemoveUser(String email) throws SQLException , Exception {
        OpenDBConnection();
        if (1 != stmt.executeUpdate("DELETE FROM USER WHERE Email='" + email + "';")) {
            throw new Exception("Failed to delete user " + email + " from database!");
        }
        CloseDBConnection();
    }

    public void AddUser(String email , String name , String pword) throws SQLException , Exception {
        OpenDBConnection();
        results = stmt.executeQuery("SELECT * FROM USER WHERE Email='" + email + "'");
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

        String sql = "SELECT * FROM PROFILE WHERE Email = '" + user_email + "';";
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

        String sql = "SELECT * FROM USER WHERE Email = '" + userEmail + "';";

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

    enum OFFER_ACCEPTANCE_TYPE {
        WORKER_ACCEPTS ,
        EMPLOYER_ACCEPTS
    }

    /**
        Returns true if the offer acceptance indicates a match between employer and employee
    //*/
    public boolean AcceptOffer(long WorkerProfileID , long EmployeeProfileID , OFFER_ACCEPTANCE_TYPE type) throws Exception {

        boolean ret = false;

        OpenDBConnection();

        String sql = "SELECT * FROM ACCEPTED WHERE WPID = " + Long.toString(WorkerProfileID) + " AND EPID = " +
                    Long.toString(EmployeeProfileID) + ";";
        results = stmt.executeQuery(sql);

        /* TODO : Count actual number of rows returned. It should be 1, but there may be an error in the database */
        if (results.next()) {
            /// Need to check if the offer has made a match
            long JID = results.getLong(1);
            boolean worker_accepts = (results.getInt(4) == 1);
            boolean employer_accepts = (results.getInt(5) == 1);
            if (((OFFER_ACCEPTANCE_TYPE.WORKER_ACCEPTS == type) && employer_accepts) ||
                ((OFFER_ACCEPTANCE_TYPE.EMPLOYER_ACCEPTS == type) && worker_accepts)) {
                /// Made a match, update the database to reflect the match
                sql = "UPDATE ACCEPTED SET WACCEPT = 1 , EACCEPT = 1 WHERE JID = '" +
                             Long.toString(JID) + "';";
                if (1 != stmt.executeUpdate(sql)) {
                    throw new Exception("Failed to update match on ACCEPTED table with JID = " + Long.toString(JID));
                }
                ret = true;
            }
        }
        else {
            /// Need to add new entry to ACCEPTED table
            String worker_accepts = (type == OFFER_ACCEPTANCE_TYPE.WORKER_ACCEPTS)?"1":"0";
            String employer_accepts = (type == OFFER_ACCEPTANCE_TYPE.EMPLOYER_ACCEPTS)?"1":"0";
            sql = "INSERT INTO ACCEPTED VALUES(NULL , " + Long.toString(WorkerProfileID) + " , " + Long.toString(EmployeeProfileID) +
                    " , " + worker_accepts + " , " + employer_accepts + ");";
            if (1 != stmt.executeUpdate(sql)) {
                throw new Exception("Failed to insert new offer into ACCEPTED table!");
            }
        }

        CloseDBConnection();

        return ret;
    }

    public long GetMatchingJobId(long WorkerID , long EmployeeID) throws SQLException {

        long ret = -1;

        OpenDBConnection();

        String sql = "SELECT JID FROM ACCEPTED WHERE WPID = " +
                      Long.toString(WorkerID) + " AND EPID = " + Long.toString(EmployeeID) + ";";

        results = stmt.executeQuery(sql);

        /* TODO : Check for more than one result, which would indicate an error */
        if (results.next()) {
            ret = results.getLong(1);
        }

        CloseDBConnection();

        return ret;
    }

    public ArrayList<Profile> GetMatchedJobs(long ProfileID , String job_type) throws SQLException {

        ArrayList<Profile> plist = new ArrayList<Profile>();

        OpenDBConnection();

        /// Fetch all jobs that have been accepted
        String sql = "SELECT * FROM ACCEPTED WHERE WAccept = 1 AND EAccept = 1";
        results = stmt.executeQuery(sql);

        int column = 3;
        if (job_type.equals("Worker")) {
            column = 2;
        }
        while (results.next()) {
            long PID = results.getLong(column);
            String sql2 = "SELECT * FROM PROFILE WHERE PID = " + Long.toString(PID) + ";";
            ResultSet rs = stmt2.executeQuery(sql2);
            while (rs.next()) {
                Profile p = GetProfileFromResults(rs);
                plist.add(p);
            }
        }

        CloseDBConnection();

        return plist;
    }

    public void AddChatMessage(long JobID , String message) throws Exception {
        OpenDBConnection();

        String sql = "SELECT WPID , EPID FROM ACCEPTED WHERE JID = " + Long.toString(JobID) + ";";

        results = stmt.executeQuery(sql);

        if (results.next()) {
            String WPID = Long.toString(results.getLong(1));
            String EPID = Long.toString(results.getLong(2));
            String sql2 = "INSERT INTO CHAT VALUES(NULL , " + Long.toString(JobID) + " , " + WPID + " , " + EPID + " , '" +
                            message + "');";
            if (1 != stmt.executeUpdate(sql)) {
                throw new Exception("Failed to add chat message!");
            }
        }
        else {
            throw new Exception("Failed to retrieve IDs from JobID");
        }

        CloseDBConnection();
    }


}
