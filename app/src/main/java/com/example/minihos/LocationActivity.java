package com.example.minihos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.minihos.DataDao.DataDao;
import com.example.minihos.DataDao.HosDataDao;
import com.example.minihos.Model.CityModel;
import com.example.minihos.Util.DBUtil;
import com.example.minihos.Util.HosDBUtil1;
import com.example.minihos.Util.HosDBUtil2;
import com.example.minihos.Util.ToastUtil;
import com.example.minihos.fragment.Fragment_Hospick;
import com.example.minihos.widget.GridAdapter;
import com.example.minihos.widget.LinearAdapter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LocationActivity extends AppCompatActivity {

    Connection con=null;

    DBUtil dbUtil=new DBUtil();

    DataDao dataDao=new DataDao();




    private RecyclerView rvhotcity,rvallcity;
    private Button btnlosearch;


    private Handler handler=null;

    int num;
    List<CityModel> allcitylist= new ArrayList<CityModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        handler=new Handler();


        //标题栏返回按钮
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        btnlosearch=(Button)findViewById(R.id.btn_location_search);
        btnlosearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置搜索地点




            }
        });

        createGrid();


        final Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");
        int num=bundle.getInt("num");
//      ToastUtil.showMsg(this,name+num);


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











    private void createGrid(){
        //热门城市grid
        String[] a={"上海市","北京市","杭州","广州","成都","苏州","深圳","南京","天津","重庆","厦门","武汉"};
        List<String> hotcitylist= Arrays.asList(a);
        rvhotcity=(RecyclerView) findViewById(R.id.rv_city_grid1);
        rvhotcity.setLayoutManager(new GridLayoutManager(LocationActivity.this,3));
        rvhotcity.setAdapter(new GridAdapter(LocationActivity.this, new GridAdapter.OnItemClickListener() {
            @Override
            public void onClick(Context context, int pos, String cityname) {

                //点击事件
               // Toast.makeText(LocationActivity.this, cityname, Toast.LENGTH_SHORT).show();

                Intent intent=new Intent();
                Bundle bundle1=new Bundle();
                bundle1.putString("cityname",cityname);
                intent.putExtras(bundle1);
                setResult(Activity.RESULT_OK,intent);
                finish();


            }
        },12,hotcitylist));


        //全部城市grid
        allcitylist.clear();
        rvallcity=(RecyclerView) findViewById(R.id.rv_city_grid2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("test1","test1");
                con=dbUtil.getcon();
                try {
                    num=dataDao.getAllCityNum(con);
                    dataDao.getCityData(con,allcitylist);
                    Log.d("1号城市",allcitylist.get(1).getCityName());
                    Log.d("读取城市成功","读取城市成功");

                } catch (SQLException e) {
                    e.printStackTrace();
                    Log.d("读取城市失败","读取城市失败");
                }finally {
                    dbUtil.closeCon(con);
                }
                handler.post(udpUIRunnable);
            }
        }).start();
        rvallcity.setLayoutManager(new GridLayoutManager(LocationActivity.this,3));

    }


    // 构建Runnable对象，并在runnable中更新UI

    Runnable udpUIRunnable=new Runnable(){
        @Override
        public void run() {
            //更新UI
            rvallcity.setAdapter(new GridAdapter(LocationActivity.this, new GridAdapter.OnItemClickListener() {
                @Override
                public void onClick(Context context,int pos,String cityname) {

                    //点击事件
                    Toast.makeText(LocationActivity.this, cityname, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context,MainActivity.class);
                    startActivity(intent);
                }
            },num,allcitylist,0));


        }
    };



}
