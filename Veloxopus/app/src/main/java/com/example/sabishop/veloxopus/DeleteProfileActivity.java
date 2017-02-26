package com.example.sabishop.veloxopus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteProfileActivity extends AppCompatActivity implements View.OnClickListener {

    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_profile);
        email = getIntent().getStringExtra("email");

        //TODO: fill with info from database
        Profile profile = new Profile("contactlaurac@gmail.com", "Pet Sitter", "Need sitter for Shih Tzu age 6 and Lhasa Apso age 9", "Child/Pet Care", "Job", 1);
        TextView nameView = (TextView)findViewById(R.id.textViewName);
        nameView.setText(profile.name);
        TextView descriptionView = (TextView)findViewById(R.id.textViewDescription);
        descriptionView.setText(profile.description);

        Button yesButton = (Button)findViewById(R.id.button_yes);
        yesButton.setOnClickListener(this);
        Button noButton = (Button)findViewById(R.id.button_no);
        noButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.button_yes)){
            //TODO: delete profile from database
            Toast.makeText(getApplicationContext(), "Profile deleted", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(getApplicationContext(), ProfilesActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
