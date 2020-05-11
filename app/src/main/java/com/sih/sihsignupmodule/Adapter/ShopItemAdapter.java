package com.sih.sihsignupmodule.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sih.sihsignupmodule.Item.shopItem;
import com.sih.sihsignupmodule.R;

import java.util.ArrayList;

public class ShopItemAdapter  extends RecyclerView.Adapter<ShopItemAdapter.ProductViewHolder> {

    private ArrayList<shopItem> mExampleList;
    private ShopItemAdapter.OnItemClickListener mlistener;


    private RecyclerView.OnItemTouchListener mlistner;

    public  interface OnItemClickListener{
        void OnItemClick(int position);
    }


    public void setOnItemClickListener(ShopItemAdapter.OnItemClickListener listener){
        mlistener=listener;
    }


    public static class ProductViewHolder extends  RecyclerView.ViewHolder{
        public ImageView mimageView;
        public TextView mtextView1,mtextView2;




        public ProductViewHolder(@NonNull View itemView, final ShopItemAdapter.OnItemClickListener listener) {
            super(itemView);
           // mimageView=itemView.findViewById(R.id.imgproducticon);
            mtextView1=itemView.findViewById(R.id.txtproductname);
            mtextView2=itemView.findViewById(R.id.txtproductcost);


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


    public ShopItemAdapter(ArrayList<shopItem> exampleList){
        mExampleList=exampleList;
    }
    @NonNull
    @Override
    public ShopItemAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.productsitsm_layout,parent,false);
        ShopItemAdapter.ProductViewHolder evh=new ShopItemAdapter.ProductViewHolder(v,mlistener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopItemAdapter.ProductViewHolder holder, int position) {
        shopItem currentItem=mExampleList.get(position);

        //holder.mimageView.setImageResource(currentItem.getmImageResource());
        holder.mtextView1.setText(currentItem.getproductname());
        holder.mtextView2.setText(currentItem.getproductprice());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
