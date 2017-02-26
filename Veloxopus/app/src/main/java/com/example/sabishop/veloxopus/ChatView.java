package com.example.sabishop.veloxopus;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by marissacarson on 2/25/17.
 */

public class ChatView extends LinearLayout {
    private ChatLog chatLog;
    private Profile self;
    private RelativeLayout.LayoutParams lp;
    private Context context;
    int screenWidth, screenHeight;
    public ChatView(Context context, ChatLog chatLog, Profile self) {
        super(context);
        this.chatLog = chatLog;
        this.self = self;
        setOrientation(HORIZONTAL);
        this.context = context;

        lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (self.profileID == chatLog.fromID) {
            formatFromMe();
        }
        else {
            formatToMe();
        }
        format();

        TextView whoView = new TextView(context);
        if (chatLog.fromID == self.profileID){
            whoView.setText("Me: ");
        }
        else {
            whoView.setText("Them: ");
        }
        whoView.setTextColor(getResources().getColor(R.color.colorLightPurple));
        addView(whoView);

        TextView messageView = new TextView(context);
        messageView.setText(chatLog.message);
        messageView.setTextColor(getResources().getColor(R.color.colorWhite));
        addView(messageView);
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
        //lp.height = screenHeight/6;
        lp.width = screenWidth;
        setPadding(20,20,20,20);
    }

    private void formatToMe() {
        setBackground(new ColorDrawable(getResources().getColor(R.color.colorDarkPurple)));
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        setGravity(Gravity.LEFT);
    }

    private void formatFromMe() {
        setBackground(new ColorDrawable(getResources().getColor(R.color.colorMediumPurple)));
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        setGravity(Gravity.RIGHT);
    }
}
