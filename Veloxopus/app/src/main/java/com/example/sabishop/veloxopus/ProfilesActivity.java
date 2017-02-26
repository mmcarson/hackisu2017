package com.example.sabishop.veloxopus;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class ProfilesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //View decorView = getWindow().getDecorView();
        // Hide the status bar.
        //int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        //decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //ActionBar actionBar = getActionBar();
        //actionBar.hide();
        //getActionBar().hide();

        String email = getIntent().getStringExtra("email");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditProfileActivity.class);
                // TODO: add blank profile to database and pass its id through the intent
                startActivity(intent);
            }
        });

        // test
        Profile profile = new Profile("mmcarson@gmail.com", "Pet Sitter", "Experienced with small and large dogs", "Child/Pet Care", "Worker", 0);
        ProfileListView profileListView = new ProfileListView(getApplicationContext(), profile, this);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.profiles_list);
        linearLayout.addView(profileListView);
    }

}
