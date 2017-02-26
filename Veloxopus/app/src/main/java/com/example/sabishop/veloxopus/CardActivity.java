package com.example.sabishop.veloxopus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class CardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        FloatingActionButton fabLike = (FloatingActionButton) findViewById(R.id.fabLike);
        fabLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: make like
                Snackbar.make(view, "Replace with like action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fabDislike = (FloatingActionButton) findViewById(R.id.fabDislike);
        fabDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: make dislike
                Snackbar.make(view, "Replace with dislike action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //TODO: fill with info from database
        Profile profile = new Profile("contactlaurac@gmail.com", "Pet Sitter", "Need sitter for Shih Tzu age 6 and Lhasa Apso age 9", "Child/Pet Care", "Job", 1);
        TextView nameView = (TextView)findViewById(R.id.textViewName);
        nameView.setText(profile.name);
        TextView descriptionView = (TextView)findViewById(R.id.textViewDescription);
        descriptionView.setText(profile.description);
        TextView categoryView = (TextView)findViewById(R.id.textViewTitle);
        categoryView.setText(profile.category);

    }

}
