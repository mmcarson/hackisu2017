package com.example.sabishop.veloxopus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {
    TextView categoryView, descriptionView, nameView;
    ArrayList<Profile> profiles;
    int profileNumber = 0;
    long profileID;
    Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        profileID = getIntent().getLongExtra("profileID",0);

        FloatingActionButton fabLike = (FloatingActionButton) findViewById(R.id.fabLike);
        fabLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MySQLDatabase database = new MySQLDatabase();
                    Profile employer, worker, other = profiles.get(profileNumber%profiles.size());
                    MySQLDatabase.OFFER_ACCEPTANCE_TYPE offer_acceptance_type;
                    if (profile.type.equals("Worker")){
                        worker = profile;
                        employer = other;
                        offer_acceptance_type = MySQLDatabase.OFFER_ACCEPTANCE_TYPE.WORKER_ACCEPTS;
                    }
                    else {
                        worker = other;
                        employer = profile;
                        offer_acceptance_type = MySQLDatabase.OFFER_ACCEPTANCE_TYPE.EMPLOYER_ACCEPTS;
                    }
                    database.AcceptOffer(worker.profileID, employer.profileID, offer_acceptance_type);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                nextView();
            }
        });

        FloatingActionButton fabDislike = (FloatingActionButton) findViewById(R.id.fabDislike);
        fabDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextView();
            }
        });

        //TODO: fill with info from database

        MySQLDatabase database = null;
        try {
            database = new MySQLDatabase();
            profile = database.GetProfile(profileID);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        nameView = (TextView)findViewById(R.id.textViewName);
        descriptionView = (TextView)findViewById(R.id.textViewDescription);
        categoryView = (TextView)findViewById(R.id.textViewTitle);

        profiles = new ArrayList<>();

        try {
            ArrayList<Profile> allProfiles = database.GetAllProfiles();
            for (int i=0; i<allProfiles.size();i++) {
                if (allProfiles.get(i).profileID != profileID
                        && !allProfiles.get(i).type.equals(profile.type)
                        && allProfiles.get(i).category.equals(profile.category)) {
                    profiles.add(allProfiles.get(i));
                }
            }
            updateViews();
        } catch (SQLException e) {
            e.printStackTrace();
            profiles = new ArrayList<>();
        }
        //Profile profile = new Profile("contactlaurac@gmail.com", "Pet Sitter", "Need sitter for Shih Tzu age 6 and Lhasa Apso age 9", "Child/Pet Care", "Job", 1);


    }

    private void nextView() {
        profileNumber++;
        updateViews();
    }

    private void updateViews() {
        if (!profiles.isEmpty()) {
            nameView.setText(profiles.get(profileNumber % profiles.size()).name);
            descriptionView.setText(profiles.get(profileNumber % profiles.size()).description);
            categoryView.setText(profiles.get(profileNumber % profiles.size()).category);
        }
        else {
            categoryView.setText("");
            descriptionView.setText(R.string.no_profiles_to_match);
            nameView.setText(R.string.try_again);
        }
    }

}
