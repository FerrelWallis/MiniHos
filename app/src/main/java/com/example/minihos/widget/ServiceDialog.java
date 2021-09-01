package com.example.minihos.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.minihos.R;

public class ServiceDialog extends Dialog implements View.OnClickListener {

    private TextView tvtittle,tvmessage,tvcancle,tvensure;

    private String tittle,message,cancle,ensure;

    private IOnCancelListener cancelListener;
    private IOnConfirmListener confirmListener;

    public ServiceDialog setTittle(String tittle) {
        this.tittle = tittle;
        return this;
    }

    public ServiceDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public ServiceDialog setCancle(String cancle,IOnCancelListener listener) {
        this.cancle = cancle;
        this.cancelListener=listener;
        return this;
    }

    public ServiceDialog setEnsure(String ensure,IOnConfirmListener listener) {
        this.ensure = ensure;
        this.confirmListener=listener;
        return this;
    }



    public ServiceDialog(@NonNull Context context) {
        super(context);
    }

    public ServiceDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ServiceDialog(@NonNull Context context, boolean cancelable, @NonNull OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_service_dialoge);
        //设置宽度
        WindowManager m=getWindow().getWindowManager();
        Display d=m.getDefaultDisplay();
        WindowManager.LayoutParams p=getWindow().getAttributes();
        Point size=new Point();
        d.getSize(size);
        p.width= (int) (size.x * 0.8); //设置dialog宽度为当前手机屏幕宽度*0.8
        getWindow().setAttributes(p);



        tvtittle=(TextView) findViewById(R.id.tv_title3);
        tvmessage=(TextView) findViewById(R.id.tv_message);
        tvcancle=(TextView) findViewById(R.id.tv_cancle);
        tvensure=(TextView) findViewById(R.id.tv_ensure);
        //如果标题不为空，
        if(!TextUtils.isEmpty(tittle)){
            tvtittle.setText(tittle);
        }
        if(!TextUtils.isEmpty(message)){
            tvmessage.setText(message);
        }
        if(!TextUtils.isEmpty(cancle)){
            tvcancle.setText(cancle);
        }
        if(!TextUtils.isEmpty(ensure)){
            tvensure.setText(ensure);
        }

        tvcancle.setOnClickListener(this);
        tvensure.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_cancle:
                if(cancelListener!=null)
                    cancelListener.onCancel(this);
                dismiss();
                break;
            case  R.id.tv_ensure:
                if(confirmListener!=null)
                    confirmListener.onConfirm(this);
                dismiss();
                break;
        }
    }


    public interface IOnCancelListener{
        void onCancel(ServiceDialog dialog);
    }

    public interface IOnConfirmListener{
        void onConfirm(ServiceDialog dialog);
    }

}
