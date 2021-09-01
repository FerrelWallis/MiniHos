package com.example.minihos.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.minihos.Model.MiniHosModel;
import com.example.minihos.R;

import java.util.List;


public class LinearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mcontext;

    private List<MiniHosModel> hoslist;

    private List<String> testlist;

    private OnItemClickListener mlistener;

    int num;

    private View view;


    public LinearAdapter(Context context, OnItemClickListener listener, List<MiniHosModel> li, int n) {
        this.mlistener=listener;
        this.mcontext=context;
        this.hoslist=li;
        this.num=n;
    }


    public LinearAdapter(Context context, OnItemClickListener listener, List<String> li, int n,int miss) {
        this.mlistener=listener;
        this.mcontext=context;
        this.testlist=li;
        this.num=n;
    }


   // public LinearAdapter(Context context, List<String> a){ this.mcontext=context; list=a;}


    //连接LinearRecycler和LinearItem,i指viewtype,从getItemViewType获得数值
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new LinearViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.layout_hos_item,viewGroup,false));


    }


    @Override
    public int getItemViewType(int position) {

        return 0;
    }

    //Item属性设置
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final int position;
        position=i;


        ((LinearViewHolder)viewHolder).hosname.setText(hoslist.get(i).getHname());
        ((LinearViewHolder)viewHolder).hoslevel.setText(hoslist.get(i).getLevel());
        ((LinearViewHolder)viewHolder).hosgreat.setText(hoslist.get(i).getGreat());
        Glide.with(view).load("http://192.168.137.1:8018/"+hoslist.get(i).getHname()+".jpg").into(((LinearViewHolder)viewHolder).hospic);


        //设置短按事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.onClick(mcontext,position,hoslist.get(i).getHname());


            }
        });





    }

    //item个数
    @Override
    public int getItemCount() {

        return num;
    }



    //创建viewholder属性，绑定layout_linear_item控件
    class LinearViewHolder extends RecyclerView.ViewHolder
    {

        private TextView hosname,hoslevel,hosgreat;
        private ImageView hospic;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);

            hosname=itemView.findViewById(R.id.tv_hos_name);
            hoslevel=itemView.findViewById(R.id.tv_hos_level);
            hosgreat=itemView.findViewById(R.id.tv_hos_great);
            hospic=itemView.findViewById(R.id.iv_hospic);
            view=itemView;

        }

    }


    public interface OnItemClickListener{
        void onClick(Context context,int pos,String name);
    }



}
