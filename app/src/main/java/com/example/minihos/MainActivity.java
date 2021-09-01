package com.example.minihos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minihos.DataDao.DataDao;
import com.example.minihos.Util.DBUtil;
import com.example.minihos.Util.ToastUtil;
import com.example.minihos.fragment.Fragment_Hospick;
import com.example.minihos.fragment.Fragment_Like;
import com.example.minihos.fragment.Fragment_Message;
import com.example.minihos.fragment.Fragment_Order;
import com.example.minihos.widget.ServiceDialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Connection con=null;

    DBUtil dbUtil=new DBUtil();

    DataDao dataDao=new DataDao();


    private Fragment fragment_hospick,fragment_order,fragment_like,fragment_message;


    int i,j;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        tvcheck=(TextView)findViewById(R.id.tv_check);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("test1","test1");
//                con=dbUtil.getcon();
//                try {
//                    i=dataDao.getAllCityNum(con);
//                    j=dataDao.getAllUserNum(con);
//                    Log.d("用户个数",Integer.toString(j));
//                    Log.d("城市个数",Integer.toString(i));
//                    Log.d("读取城市个数成功","读取城市个数成功");
//
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    Log.d("读取城市失败","读取城市失败");
//
//                }finally {
//                    tvcheck.setText(Integer.toString(i));
//                    dbUtil.closeCon(con);
//                }
//            }
//        }).start();




//        //主界面根据Bottom导航切换
//
//        //实例化Fragment
//        fragment_hospick=new Fragment_Hospick();
//
//        //把Fragment_hospick添加到Activity中
//        getSupportFragmentManager().beginTransaction().add(R.id.frag_container,fragment_hospick).commitAllowingStateLoss();


        //bottomNav
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





    }

    //
    @Override
    protected void onResume() {
        super.onResume();
        //主界面根据Bottom导航切换

        //实例化Fragment
        fragment_hospick=new Fragment_Hospick();

        //把Fragment_hospick添加到Activity中
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container,fragment_hospick).commitAllowingStateLoss();

    }

    //获取LocationActivity返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        Fragment_Hospick.cityname=data.getExtras().getString("cityname");
        ToastUtil.showMsg(this,"当前城市："+data.getExtras().getString("cityname"));

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


//    //获取LocationActivity返回的数据
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        ToastUtil.showMsg(MainActivity.this,data.getExtras().getString("title"));
//    }






    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent=null;
        if (id == R.id.nav_account) {
            intent=new Intent(this,MyAccount.class);
            startActivity(intent);

        } else if (id == R.id.nav_like) {
            if(fragment_like==null){
                fragment_like=new Fragment_Like();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragment_like).commitAllowingStateLoss();

        } else if (id == R.id.nav_order) {
            intent=new Intent(this,OrderActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            intent=new Intent(this,SettingActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_service) {
            ServiceDialog customDialog=new ServiceDialog(this);
            customDialog.setTittle("反馈").setMessage("请描述您遇到的问题").setCancle("取消", new ServiceDialog.IOnCancelListener() {
                @Override
                public void onCancel(ServiceDialog dialog) {
                    ToastUtil.showMsg(MainActivity.this,"取消成功");
                }
            }).setEnsure("确认", new ServiceDialog.IOnConfirmListener() {
                @Override
                public void onConfirm(ServiceDialog dialog) {
                    ToastUtil.showMsg(MainActivity.this,"确认成功");

                }
            }).show();

        } else if (id == R.id.nav_send) {
            intent=new Intent(this,MyAccount.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    //BottomNav
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:


                    fragment_hospick=new Fragment_Hospick();

                    ToastUtil.showMsg(MainActivity.this,"hospick");

                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragment_hospick).commitAllowingStateLoss();
                    return true;

                case R.id.navigation_like:

                    fragment_like=new Fragment_Like();

                    ToastUtil.showMsg(MainActivity.this,"like");

                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragment_like).commitAllowingStateLoss();
                    return true;

                case R.id.navigation_order:



                    fragment_order=new Fragment_Order();

                    ToastUtil.showMsg(MainActivity.this,"order");

                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragment_order).commitAllowingStateLoss();
                    return true;

                case R.id.navigation_message:



                    fragment_message=new Fragment_Message();

                    ToastUtil.showMsg(MainActivity.this,"mess");

                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragment_message).commitAllowingStateLoss();

                    return true;
                case R.id.navigation_forum:


                    if(fragment_message==null){
                        fragment_message=new Fragment_Message();
                    }
                    ToastUtil.showMsg(MainActivity.this,"forum");

                    return true;
            }
            return false;
        }
    };





}
