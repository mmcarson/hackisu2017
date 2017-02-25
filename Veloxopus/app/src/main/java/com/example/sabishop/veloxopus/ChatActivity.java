package com.example.sabishop.veloxopus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private Profile me, other;
    private long chatID;
    ChatConversation conversation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatID = getIntent().getLongExtra("chatID",0);
        //TODO: get chats from database and delete this sample
        if (chatID == 0){
            me = new Profile("mmcarson@gmail.com", "Pet Sitter", "Experienced with small and large dogs", "Child/Pet Care", "Worker", 0);
            other = new Profile("contactlaurac@gmail.com", "Pet Sitter", "Need sitter for Shih Tzu age 6 and Lhasa Apso age 9", "Child/Pet Care", "Job", 1);
            conversation = new ChatConversation(chatID, me.profileID, other.profileID, new ArrayList<>());
            conversation.addToChatList(new ChatLog(chatID, me.profileID, other.profileID, 0, "Do you go on walks?"));
            conversation.addToChatList(new ChatLog(chatID, other.profileID, me.profileID, 1, "Yes, absolutely!"));
        }
        else {
            // TODO
        }

        //fill layouts
        LinearLayout chatLayout = (LinearLayout)findViewById(R.id.chatLayout);
        for (int i=0; i<conversation.getChatList().size(); i++){
            chatLayout.addView(new ChatView(getApplicationContext(), conversation.getChatList().get(i), me));
        }

        String middleOfTitle;
        if (me.type == "Job"){
            middleOfTitle = " working for ";
        }
        else {
            middleOfTitle = " hiring ";
        }

        TextView titleView = (TextView)findViewById(R.id.profile_names);
        titleView.setText(me.name + middleOfTitle + other.name);

    }
}
