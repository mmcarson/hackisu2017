package com.example.sabishop.veloxopus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EditProfileActivity extends AppCompatActivity {

    private Profile profile;
    private boolean isNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //TODO: replace this with database query getting profile based on id
        long profileID;
        profileID = getIntent().getLongExtra("profileID", 0);
        if (profileID == 0){
            profile = new Profile("Email", "Profile Name", "Description (skills, price, etc.)", "Child/Pet Care", "Worker", 0);
        }
        isNew = getIntent().getBooleanExtra("isNew", false);


        profile.email = getIntent().getStringExtra("email");

        //fill spinners
        Spinner categorySpinner = (Spinner)findViewById(R.id.spinnerCategory);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        categorySpinner.setAdapter(adapter);
        int pos = adapter.getPosition(profile.category);
        categorySpinner.setSelection(pos);


        Spinner typeSpinner = (Spinner)findViewById(R.id.spinnerType);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this,
                R.array.types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        typeSpinner.setAdapter(adapter);
        pos = adapter.getPosition(profile.type);
        typeSpinner.setSelection(pos);

        //fill EditTexts
        EditText name = (EditText)findViewById(R.id.editTextName);
        name.setText(profile.name);
        EditText description = (EditText)findViewById(R.id.editTextDescription);
        description.setText(profile.description);



        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile.type = ((Spinner)findViewById(R.id.spinnerType)).getSelectedItem().toString();
                profile.category = ((Spinner)findViewById(R.id.spinnerCategory)).getSelectedItem().toString();
                profile.description = ((EditText)findViewById(R.id.editTextDescription)).getText().toString();
                profile.name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
                if (isNew){
                    try {
                        MySQLDatabase database = new MySQLDatabase();
                        database.AddProfile(profile.email, profile.name, profile.category, profile.description, profile.type);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    //TODO: Edit profile in database
                }
                Intent intent = new Intent(getApplicationContext(), ProfilesActivity.class);
                intent.putExtra("email", profile.email);
                startActivity(intent);
            }
        });
    }

}
