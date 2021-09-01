package com.example.minihos.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.minihos.Model.CityModel;
import com.example.minihos.R;

import java.util.List;


public class GridAdapter extends RecyclerView.Adapter<GridAdapter.LinearViewHolder> {

    private Context mcontext;

    private List<String> list;

    private List<CityModel> allcitylist;

    private OnItemClickListener mlistener;

    private int number;

    private int flag;


    public GridAdapter(Context context, OnItemClickListener listener, int num, List<String> li) {
        this.mlistener=listener;
        this.mcontext=context;
        this.number=num;
        this.list=li;
        flag=0;
    }

    public GridAdapter(Context context, OnItemClickListener listener, int num, List<CityModel> li,int miss) {
        this.mlistener=listener;
        this.mcontext=context;
        this.number=num;
        this.allcitylist=li;
        flag=1;
    }



    // public LinearAdapter(Context context, List<String> a){ this.mcontext=context; list=a;}

    //连接LinearRecycler和LinearItem
    @Override
    public GridAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new LinearViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.layout_grid_item,viewGroup,false));

    }

    //Item属性设置
    @Override
    public void onBindViewHolder(@NonNull final GridAdapter.LinearViewHolder viewHolder, final int i) {
        final int position;
        position=i;


        if(flag==0){
            //设置热门城市的名称
            viewHolder.textView.setText(list.get(i));
            //设置短按事件
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mlistener.onClick(mcontext,position,list.get(i));
                }
            });
        }else if(flag==1){
            //设置全部城市的名称
            viewHolder.textView.setText(allcitylist.get(i).getCityName());
            //设置短按事件
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mlistener.onClick(mcontext,position,allcitylist.get(i).getCityName());
                }
            });
        }










    }

    //item个数
    @Override
    public int getItemCount() {

        return number;
    }


    //创建viewholder属性，绑定layout_linear_item控件
    class LinearViewHolder extends RecyclerView.ViewHolder
    {

        private TextView textView;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.tv_gridtitle);

        }
    }


    public interface OnItemClickListener{
        void onClick(Context context,int pos,String cityname);
    }



}
