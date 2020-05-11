package com.sih.sihsignupmodule.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sih.sihsignupmodule.Item.CartItem;
import com.sih.sihsignupmodule.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private ArrayList<CartItem> mCartList;
    private CartAdapter.OnItemClickListener mlistener;


    private RecyclerView.OnItemTouchListener mlistner;

    public  interface OnItemClickListener{
        void OnItemClick(int position);
    }


    public void setOnItemClickListener(CartAdapter.OnItemClickListener listener){
        mlistener=listener;
    }


    public static class CartViewHolder extends  RecyclerView.ViewHolder{
        public ImageView mItemIcon;
        public TextView mItemName,mItemPrice,mItemQuantity;




        public CartViewHolder(@NonNull View itemView, final CartAdapter.OnItemClickListener listener) {
            super(itemView);
            mItemIcon=itemView.findViewById(R.id.cartitemproducticon);
            mItemName=itemView.findViewById(R.id.cartitemproductname);
            mItemPrice=itemView.findViewById(R.id.cartitemproductprice);
            mItemQuantity=itemView.findViewById(R.id.cartitemQuantity);

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


    public CartAdapter(ArrayList<CartItem> cartList){
        mCartList=cartList;
    }
    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem_layout,parent,false);
        CartAdapter.CartViewHolder evh=new CartAdapter.CartViewHolder(v,mlistener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        CartItem currentItem=mCartList.get(position);

        holder.mItemIcon.setImageResource(currentItem.getCartItemImg());
        holder.mItemName.setText(currentItem.getCartItemName());
        holder.mItemPrice.setText(currentItem.getCartItemPrice());
        holder.mItemQuantity.setText(currentItem.getCartItemQuanity());
    }

    @Override
    public int getItemCount() {
        return mCartList.size();
    }
}
