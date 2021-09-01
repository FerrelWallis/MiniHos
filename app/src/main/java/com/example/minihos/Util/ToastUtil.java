package com.example.minihos.Util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minihos.R;

public class ToastUtil {
    public static Toast mtoast;

    public static void showMsg(Context context, String msg){
        if(mtoast==null)
            mtoast=Toast.makeText(context,msg,Toast.LENGTH_LONG);
        else
            mtoast.setText(msg);
        mtoast.show();

    }

    public static void showPicMsg(Context context, String msg, int picId){
        if(mtoast==null){
            mtoast=new Toast(context);
            mtoast.setGravity(Gravity.CENTER,0,0);
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            View view=layoutInflater.inflate(R.layout.layout_toast_ok,null);
            ImageView imageView=view.findViewById(R.id.iv_toast);
            TextView textView=view.findViewById(R.id.tv_toast);
            imageView.setImageResource(picId);
            textView.setText(msg);
            mtoast.setView(view);
            mtoast.setDuration(Toast.LENGTH_LONG);
            mtoast.show();
        }
        else{
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            mtoast.setGravity(Gravity.CENTER,0,0);
            View view=layoutInflater.inflate(R.layout.layout_toast_ok,null);
            ImageView imageView=view.findViewById(R.id.iv_toast);
            TextView textView=view.findViewById(R.id.tv_toast);
            imageView.setImageResource(picId);
            textView.setText(msg);
            mtoast.setView(view);
            mtoast.setDuration(Toast.LENGTH_LONG);
            mtoast.show();
        }








    }

}
