package com.example.minihos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.minihos.widget.ProgressDialog;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn_register,btn_login;
    private CheckBox cb_remember,cb_auto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //标题栏返回按钮
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        btn_login=(Button)findViewById(R.id.btn_login);
        btn_register=(Button)findViewById(R.id.btn_register);
        cb_remember=(CheckBox)findViewById(R.id.cb_login_remenber);
        cb_auto=(CheckBox)findViewById(R.id.cb_login_auto);

        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);

        cb_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //记住密码SharedPreference操作



                Toast.makeText(LoginActivity.this,isChecked?"'记住密码'选中":"'记住密码'未选中",Toast.LENGTH_SHORT).show();
            }
        });

        cb_auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //自动登录SharedPreference操作



                Toast.makeText(LoginActivity.this,isChecked?"'自动登录'选中":"'自动登录'未选中",Toast.LENGTH_SHORT).show();

            }
        });


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
            case R.id.btn_login:
                ProgressDialog progressDialog=new ProgressDialog(this );
                progressDialog.setCancelable(false);
                progressDialog.show();


                break;
            case R.id.btn_register:
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;

        }
    }
}
