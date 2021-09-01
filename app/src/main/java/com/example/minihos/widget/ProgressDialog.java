package com.example.minihos.widget;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.minihos.R;

public class ProgressDialog extends Dialog {
    public ProgressDialog(@NonNull Context context) {
        super(context);
    }

    public ProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    private RoundProgressBar progressBar;
    public static int flag=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_progress);
        //设置宽度
        WindowManager m=getWindow().getWindowManager();
        Display d=m.getDefaultDisplay();
        WindowManager.LayoutParams p=getWindow().getAttributes();
        Point size=new Point();
        d.getSize(size);
        p.width= (int) (size.x * 0.8); //设置dialog宽度为当前手机屏幕宽度*0.8
        getWindow().setAttributes(p);

        progressBar = findViewById(R.id.progress_bar);
        ObjectAnimator.ofInt(progressBar,"progress",0,100).setDuration(3000).start();


    }


    private final Handler handler = new Handler();

    //这里用到了handler的定时器效果 延迟4秒dismiss
    @Override
    protected void onStart() {
        super.onStart();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, 4000);



    }





}
