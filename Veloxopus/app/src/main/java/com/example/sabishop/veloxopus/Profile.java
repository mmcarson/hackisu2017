package com.example.sabishop.veloxopus;

/**
 * Created by marissacarson on 2/24/17.
 */

public class Profile {
    public String email, name, description, category, type;
    long profileID;
    public Profile(String email, String name, String description, String category, String type, long profileID){
        this.email = email;
        this.name = name;
        this.description = description;
        this.category = category;
        this.type = type;
        this.profileID = profileID;
    }
    public Profile(String email){
        this.email = email;
    }
}
