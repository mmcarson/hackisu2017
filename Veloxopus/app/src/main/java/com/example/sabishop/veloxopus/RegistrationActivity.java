package com.example.sabishop.veloxopus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        String email = getIntent().getStringExtra("email");
        EditText emailView = (EditText) findViewById(R.id.email);
        emailView.setText(email);

        Button createAccountButton = (Button)findViewById(R.id.button);
        createAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.button)){
            String email = ((EditText)findViewById(R.id.email)).getText().toString();
            String password = ((EditText)findViewById(R.id.password)).getText().toString();
            String name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
            User user = new User(email, password, name);
            try {
                MySQLDatabase database = new MySQLDatabase();
                database.AddUser(email, name, password);
                Intent intent = new Intent(getApplicationContext(), ProfilesActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
