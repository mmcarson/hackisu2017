package com.example.sabishop.veloxopus;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by marissacarson on 2/25/17.
 */

public class ChatView extends LinearLayout {
    private Chat chat;
    private Profile self;
    private RelativeLayout.LayoutParams lp;
    private Context context;
    int screenWidth, screenHeight;
    public ChatView(Context context, Chat chat, Profile self) {
        super(context);
        this.chat = chat;
        this.self = self;
        setOrientation(HORIZONTAL);
        this.context = context;

        lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (self.profileID == chat.fromID) {
            formatFromMe();
        }
        else {
            formatToMe();
        }
        format();
    }

    private void format() {
        setGravity(Gravity.CENTER_VERTICAL);
        //get screen size
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        lp.height = screenHeight/6;
        lp.width = screenWidth;
    }

    private void formatToMe() {
        setBackground(new ColorDrawable(getResources().getColor(R.color.colorDarkPurple)));
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
    }

    private void formatFromMe() {
        setBackground(new ColorDrawable(getResources().getColor(R.color.colorMediumPurple));
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    }
}
