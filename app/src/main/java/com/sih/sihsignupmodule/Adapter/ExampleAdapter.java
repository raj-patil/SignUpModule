package com.sih.sihsignupmodule.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sih.sihsignupmodule.Item.ExampleItem;
import com.sih.sihsignupmodule.R;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mlistener;


    private RecyclerView.OnItemTouchListener mlistner;

    public  interface OnItemClickListener{
        void OnItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener=listener;
    }


    public static class ExampleViewHolder extends  RecyclerView.ViewHolder{
        public ImageView mimageView;
        public TextView mtextView1,mtextView2,mtextView3;




        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mimageView=itemView.findViewById(R.id.imgShopicon);
            mtextView1=itemView.findViewById(R.id.shopName);
           mtextView2=itemView.findViewById(R.id.shopId);
            mtextView3=itemView.findViewById(R.id.shopAddres);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);

                        }
                    }
                }
            });
        }
    }


    public ExampleAdapter(ArrayList<ExampleItem> exampleList){
        mExampleList=exampleList;
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.shopitem_layout,parent,false);
        ExampleViewHolder evh=new ExampleViewHolder(v,mlistener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem=mExampleList.get(position);

        holder.mimageView.setImageResource(currentItem.getmImageResource());
        holder.mtextView1.setText(currentItem.getmText1());
        holder.mtextView2.setText(currentItem.getmText2());
        holder.mtextView3.setText(currentItem.getmText3());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
