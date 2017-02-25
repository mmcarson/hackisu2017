package com.example.sabishop.veloxopus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class ChatActivity extends AppCompatActivity {

    private Profile me, other;
    private long chatID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        LinearLayout chatLayout = (LinearLayout)findViewById(R.id.chatLayout);
        chatID = getIntent().getLongExtra("chatID",0);
        //TODO: get chats from database and delete this sample
        if (chatID == 0){
            me = new Profile("mmcarson@gmail.com", "Pet Sitter", "Experienced with small and large dogs", "Child/Pet Care", "Worker", 0);
            other = new Profile("contactlaurac@gmail.com", "Pet Sitter", "Need sitter for Shih Tzu age 6 and Lhasa Apso age 9", "Child/Pet Care", "Job", 1);
        }
        else {
            // TODO
        }
    }
}
