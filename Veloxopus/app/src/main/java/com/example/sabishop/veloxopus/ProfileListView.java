package com.example.sabishop.veloxopus;

import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by marissacarson on 2/24/17.
 */

public class ProfileListView extends RelativeLayout {
    protected Context context;

    int screenWidth, screenHeight;
    Profile profile;

    public ProfileListView(Context context, Profile profile) {
        super(context);
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
        nameView.setText(profile.name);
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
        addView(cards);

        ImageView chat = new ImageView(context);
        chat.setImageDrawable(getResources().getDrawable(R.drawable.icons_white_speech));
        cards.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        chat.setMaxHeight(screenHeight/6);
        chat.setMaxWidth(screenHeight/6);
        addView(chat);

        ImageView edit = new ImageView(context);
        edit.setImageDrawable(getResources().getDrawable(R.drawable.icons_white_pencil));
        cards.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        edit.setMaxHeight(screenHeight/6);
        edit.setMaxWidth(screenHeight/6);
        addView(edit);

        ImageView close = new ImageView(context);
        close.setImageDrawable(getResources().getDrawable(R.drawable.icons_white_close));
        cards.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
        close.setMaxHeight(screenHeight/6);
        close.setMaxWidth(screenHeight/6);
        addView(close);
    }
}
