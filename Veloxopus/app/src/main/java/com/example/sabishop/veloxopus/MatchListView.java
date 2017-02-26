package com.example.sabishop.veloxopus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by marissacarson on 2/24/17.
 */

public class MatchListView extends LinearLayout {
    protected Context context;

    int screenWidth, screenHeight;
    static int PADDING_HORIZONTAL = 10, PADDING_VERTICAL = 10, PADDING_INIT = 40;
    Profile profile;
    Activity parent;

    public MatchListView(Context context, final Profile profile, final Activity parent) {
        super(context);
        setOrientation(HORIZONTAL);
        this.context = context;
        this.profile = profile;

        //get screen size
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        this.setBackground(new ColorDrawable(getResources().getColor(R.color.colorMediumPurple)));

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        setGravity(Gravity.CENTER_VERTICAL);

        setPadding(PADDING_HORIZONTAL,PADDING_VERTICAL,PADDING_HORIZONTAL,PADDING_VERTICAL);
        lp.height = screenHeight/6;
        lp.width = screenWidth;
        TextView nameView = new TextView(context);
        nameView.setTextColor(Color.WHITE);
        nameView.setTypeface(Typeface.create("HELVETICA", Typeface.BOLD));
        nameView.setText(profile.type + ": " + profile.name);
        this.addView(nameView);
        nameView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: go to a detailed view of the matched profile
                Intent intent = new Intent(getContext(), ProfileDetailActivity.class);
                intent.putExtra("profileID", profile.profileID);
                parent.startActivity(intent);
            }
        });

        ImageView chat = new ImageView(context);
        chat.setImageDrawable(getResources().getDrawable(R.drawable.icons_white_speech));
        chat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: find chatID by both profileIDs, pass through the intent to chat
                Intent intent = new Intent(getContext(), ChatActivity.class);
                parent.startActivity(intent);
            }
        });
        chat.setMaxHeight(screenHeight/6);
        chat.setMaxWidth(screenHeight/6);
        chat.setPadding(PADDING_HORIZONTAL, PADDING_VERTICAL, PADDING_HORIZONTAL, PADDING_VERTICAL);
        addView(chat);

        ImageView close = new ImageView(context);
        close.setImageDrawable(getResources().getDrawable(R.drawable.icons_white_close));
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Make the popup happen for closing a profile
            }
        });
        close.setMaxHeight(screenHeight/6);
        close.setMaxWidth(screenHeight/6);
        close.setPadding(PADDING_HORIZONTAL, PADDING_VERTICAL, PADDING_HORIZONTAL, PADDING_VERTICAL);
        addView(close);
    }
}
