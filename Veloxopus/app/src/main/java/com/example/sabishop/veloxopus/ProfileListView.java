package com.example.sabishop.veloxopus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

public class ProfileListView extends LinearLayout {
    protected Context context;

    int screenWidth, screenHeight;
    static int PADDING_HORIZONTAL = 20, PADDING_VERTICAL = 0, PADDING_INIT = 40;
    Profile profile;
    Activity parent;

    public ProfileListView(Context context, final Profile profile, final Activity parent) {
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
        this.setBackground(new ColorDrawable(getResources().getColor(R.color.colorDarkPurple)));

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        setGravity(Gravity.CENTER_VERTICAL);

        setPadding(screenWidth / 7, screenWidth / 12, screenWidth / 12, screenWidth / 12);
        lp.height = screenHeight/6;
        lp.width = screenWidth;
        TextView nameView = new TextView(context);
        nameView.setTextColor(Color.WHITE);
        nameView.setTypeface(Typeface.create("HELVETICA", Typeface.BOLD));
        nameView.setText(profile.type + ": " + profile.name);
        this.addView(nameView);

        // icons
        ImageView cards = new ImageView(context);
        cards.setImageDrawable(getResources().getDrawable(R.drawable.icons_white_cards));
        cards.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        cards.setMaxHeight(screenHeight/6);
        cards.setMaxWidth(screenHeight/6);
        cards.setPadding(PADDING_INIT, PADDING_VERTICAL, PADDING_HORIZONTAL, PADDING_VERTICAL);
        addView(cards);

        ImageView chat = new ImageView(context);
        chat.setImageDrawable(getResources().getDrawable(R.drawable.icons_white_speech));
        chat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                Toast.makeText(getContext(), "This will go to chat", Toast.LENGTH_LONG).show();
            }
        });
        chat.setMaxHeight(screenHeight/6);
        chat.setMaxWidth(screenHeight/6);
        chat.setPadding(PADDING_HORIZONTAL, PADDING_VERTICAL, PADDING_HORIZONTAL, PADDING_VERTICAL);
        addView(chat);

        ImageView edit = new ImageView(context);
        edit.setImageDrawable(getResources().getDrawable(R.drawable.icons_white_pencil));
        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                intent.putExtra("profileID", profile.profileID);
                parent.startActivity(intent);
            }
        });
        edit.setMaxHeight(screenHeight/6);
        edit.setMaxWidth(screenHeight/6);
        edit.setPadding(PADDING_HORIZONTAL, PADDING_VERTICAL, PADDING_HORIZONTAL, PADDING_VERTICAL);
        addView(edit);

        ImageView close = new ImageView(context);
        close.setImageDrawable(getResources().getDrawable(R.drawable.icons_white_close));
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        close.setMaxHeight(screenHeight/6);
        close.setMaxWidth(screenHeight/6);
        close.setPadding(PADDING_HORIZONTAL, PADDING_VERTICAL, PADDING_HORIZONTAL, PADDING_VERTICAL);
        addView(close);
    }
}
