package com.example.sabishop.veloxopus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileDetailActivity extends AppCompatActivity {


    TextView categoryView, descriptionView, nameView;
    long profileID;
    Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        profileID = getIntent().getLongExtra("profileID", 0);
        nameView = (TextView)findViewById(R.id.textViewName);
        descriptionView = (TextView)findViewById(R.id.textViewDescription);
        categoryView = (TextView)findViewById(R.id.textViewTitle);

        try {
            MySQLDatabase database = new MySQLDatabase();
            profile = database.GetProfile(profileID);
            nameView.setText(profile.name);
            categoryView.setText(profile.category);
            descriptionView.setText(profile.description);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
