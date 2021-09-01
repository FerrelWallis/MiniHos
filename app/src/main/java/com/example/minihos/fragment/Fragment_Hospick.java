package com.example.minihos.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minihos.DataDao.DataDao;
import com.example.minihos.HosActivity;
import com.example.minihos.LocationActivity;
import com.example.minihos.LoginActivity;
import com.example.minihos.MainActivity;
import com.example.minihos.Model.CityModel;
import com.example.minihos.Model.MiniHosModel;
import com.example.minihos.R;
import com.example.minihos.Util.DBUtil;
import com.example.minihos.Util.ToastUtil;
import com.example.minihos.widget.GridAdapter;
import com.example.minihos.widget.LinearAdapter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fragment_Hospick extends Fragment implements View.OnClickListener {

    private TextView tv_location;
    private RecyclerView rvhos;


    List<MiniHosModel> hoslist= new ArrayList<MiniHosModel>();

    private Handler handler=null;


    Connection con=null;

    DBUtil dbUtil=new DBUtil();

    DataDao dataDao=new DataDao();

    int num;

    public static String cityname="上海市";


    View view=null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        handler=new Handler();
        view=inflater.inflate(R.layout.fragment_hospick,container,false);
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_location=view.findViewById(R.id.tv_location);

        tv_location.setOnClickListener(this);
        rvhos=(RecyclerView) view.findViewById(R.id.rv_hos);




        createLinear(cityname);


    }


    // 构建Runnable对象，并在runnable中更新UI

    Runnable udpUIRunnable=new Runnable(){
        @Override
        public void run() {
            //更新UI

            LinearAdapter adapter=new LinearAdapter(getActivity(), new LinearAdapter.OnItemClickListener() {
                @Override
                public void onClick(Context context,int pos, String name) {
                    //点击事件
                    //数据传递
                    HosActivity hosActivity=new HosActivity();
                    Intent intent=new Intent(getActivity(),hosActivity.getClass());
                    Bundle bundle=new Bundle();
                    bundle.putString("hosname",name);
                    intent.putExtras(bundle);
                    //向HosActivity传输数据
                    startActivityForResult(intent,0);

                }
            },hoslist,num);
            rvhos.setAdapter(adapter);

        }
    };

    private void createLinear(final String cityname){


        //医院列表
        hoslist.clear();

        new Thread(new Runnable() {
            @Override
            public void run() {
                con=dbUtil.getcon();
                try {
                    num=dataDao.getHosNum(con,cityname);
                    dataDao.getHosData(con,hoslist,cityname);
                    Log.d("1号医院",hoslist.get(1).getHname());
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
        rvhos.setLayoutManager(new LinearLayoutManager(getActivity()));
        //list的分割线
        rvhos.addItemDecoration(new MyDecoration());

    }





    class MyDecoration extends RecyclerView.ItemDecoration
    {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelSize(R.dimen.dividerHeight));

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_location:
//                Intent intent=new Intent(getActivity(), LocationActivity.class);
//                getActivity().startActivity(intent);

                //数据传递
                Intent intent2=new Intent(getActivity(),LocationActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("name","Ferrel");
                bundle.putInt("num",88);
                intent2.putExtras(bundle);
                //startActivity(intent);
                //向LocationActivity传输数据
                startActivityForResult(intent2,0);

                break;


        }
    }





}
