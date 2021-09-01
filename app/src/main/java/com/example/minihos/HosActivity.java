package com.example.minihos;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.minihos.DataDao.DataDao;
import com.example.minihos.Model.MiniHosModel;
import com.example.minihos.Util.DBUtil;
import com.example.minihos.Util.ToastUtil;
import com.example.minihos.widget.LinearAdapter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HosActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvname,tvgreat,tvlevel,tvweb,tvphone,tvposition,tvhowto,tvdetail;
    private ImageView ivhospic;

    private String hosname;

    private MiniHosModel hos= new MiniHosModel();

    private Handler handler=null;


    Connection con=null;

    DBUtil dbUtil=new DBUtil();

    DataDao dataDao=new DataDao();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hos);

        //标题栏返回按钮
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        handler=new Handler();

        tvname=(TextView)findViewById(R.id.tv_hospagename);
        tvgreat=(TextView)findViewById(R.id.tv_hospagegreat);
        tvlevel=(TextView)findViewById(R.id.tv_hospagelevel);
        tvweb=(TextView)findViewById(R.id.tv_hospageweb);
        tvphone=(TextView)findViewById(R.id.tv_hospagephone);
        tvposition=(TextView)findViewById(R.id.tv_hospageposition);
        tvhowto=(TextView)findViewById(R.id.tv_hospagehowto);
        tvdetail=(TextView)findViewById(R.id.tv_hospagedetail);
        ivhospic=(ImageView) findViewById(R.id.iv_hospagepic);



        final Bundle bundle=getIntent().getExtras();
        hosname=bundle.getString("hosname");
        ToastUtil.showMsg(this,hosname);



        setListeners();
        setTexts();


    }


    private void setTexts(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                con=dbUtil.getcon();
                try {
                    dataDao.getHosDataByName(con,hos,hosname);
                    Log.d("1号医院",hos.getHowto());
                    Log.d("读取医院成功","读取医院成功");

                } catch (SQLException e) {
                    e.printStackTrace();
                    Log.d("读取医院失败","读取医院失败");
                }finally {
                    dbUtil.closeCon(con);
                    handler.post(udpUIRunnable);
                }

            }
        }).start();


    }


    // 构建Runnable对象，并在runnable中更新UI

    Runnable udpUIRunnable=new Runnable(){
        @Override
        public void run() {
            //更新UI

            tvname.setText(hos.getHname());
            tvgreat.setText(hos.getGreat());
            tvlevel.setText(hos.getLevel());
            tvweb.setText(hos.getWeb());
            tvphone.setText(hos.getPhone());
            tvposition.setText(hos.getPosition());
            tvhowto.setText(hos.getHowto());
            tvdetail.setText(hos.getIntro());
            Glide.with(HosActivity.this).load("http://192.168.137.1:8018/"+hos.getHname()+".jpg").into(ivhospic);


        }
    };




    private void setListeners(){
        tvphone.setOnClickListener(this);

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

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_hospagephone:


                // 检查是否获得了权限（Android6.0运行时权限）
                if (ContextCompat.checkSelfPermission(HosActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    // 没有获得授权，申请授权
                    if (ActivityCompat.shouldShowRequestPermissionRationale(HosActivity.this,Manifest.permission.CALL_PHONE)) {
                        // 返回值:如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
                        // 如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
                        // 如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                        // 弹窗需要解释为何需要该权限，再次请求授权
                        ToastUtil.showMsg(this, "请授权！");
                        // 帮跳转到该应用的设置界面，让用户手动授权
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }else{
                        // 不需要解释为何需要该权限，直接请求授权
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                }else {
                    // 已经获得授权，可以打电话
                    CallPhone();
                }







            break;
            case R.id.tv_hospageweb:
                break;
        }
    }




    private void CallPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        //url:统一资源定位符
        //uri:统一资源标示符（更广）
        intent.setData(Uri.parse("tel:" + tvphone.getText()));
        //开启系统拨号器
        startActivity(intent);
    }





}
