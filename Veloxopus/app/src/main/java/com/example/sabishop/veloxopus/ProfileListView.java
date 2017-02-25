package com.example.sabishop.veloxopus;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
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
    }
}
