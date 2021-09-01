package com.example.minihos;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.minihos.Util.ToastUtil;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_sureregi, btn_verity;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //标题栏返回按钮
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        btn_sureregi=(Button)findViewById(R.id.btn_sure_regi);
        btn_verity=(Button)findViewById(R.id.btn_verify);

        btn_sureregi.setOnClickListener(this);
        btn_verity.setOnClickListener(this);



    }

    //标题栏返回按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sure_regi:

                //加入数据库



                ToastUtil.showPicMsg(this,"注册完成~",R.drawable.verify_48px);
                break;

            case R.id.btn_verify:


                //短信验证实现


                //倒计时重发短信
                new Thread(){
                    @Override
                    public void run() {

                        for(int i=60;i>0;i--){
                            try {
                                Thread.sleep(1000);
                                Message message=new Message();
                                message.what=i;
                                handler.sendMessage(message);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Message message=new Message();
                        message.what=-1;
                        handler.sendMessage(message);

                    }
                }.start();


                handler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        if(msg.what!=-1)
                        {
                            super.handleMessage(msg);
                            btn_verity.setText("重新发送"+msg.what+"s");
                        }else {
                            btn_verity.setText("获取验证码");
                        }

                    }
                };

                ToastUtil.showMsg(this,"验证码已发送~");
                break;


        }
    }







}
