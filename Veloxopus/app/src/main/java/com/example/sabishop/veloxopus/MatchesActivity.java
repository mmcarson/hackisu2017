package com.example.sabishop.veloxopus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import java.sql.SQLException;
import java.util.ArrayList;

public class MatchesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        long profileID = getIntent().getLongExtra("profileID",0);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.hide();

        ArrayList<Profile> profiles = new ArrayList<>();

        //TODO: fill array list with matches
        try {
            MySQLDatabase database = new MySQLDatabase();
            profiles = database.GetAllProfiles();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.profiles_list);

        for (int i=0; i<profiles.size(); i++) {
            MatchListView matchListView = new MatchListView(getApplicationContext(), profiles.get(i), this);
            linearLayout.addView(matchListView);
        }

        // test
        //Profile profile = new Profile("contactlaurac@gmail.com", "Pet Sitter", "Need sitter for Shih Tzu age 6 and Lhasa Apso age 9", "Child/Pet Care", "Job", 1);
        //ProfileListView profileListView = new ProfileListView(getApplicationContext(), profile, this);
    }

}
