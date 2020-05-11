package com.sih.sihsignupmodule.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sih.sihsignupmodule.Item.ReceiptItem;
import com.sih.sihsignupmodule.R;

import java.util.ArrayList;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ReceiptViewHolder> {

    private ArrayList<ReceiptItem> mReceiptList;
    private ReceiptAdapter.OnItemClickListener mlistener;


    private RecyclerView.OnItemTouchListener mlistner;

    public  interface OnItemClickListener{
        void OnItemClick(int position);
    }


    public void setOnItemClickListener(ReceiptAdapter.OnItemClickListener listener){
        mlistener=listener;
    }


    public static class ReceiptViewHolder extends  RecyclerView.ViewHolder{
        public ImageView mItemIcon;
        public TextView mItemName,mItemPrice,mItemQuantity;




        public ReceiptViewHolder(@NonNull View itemView, final ReceiptAdapter.OnItemClickListener listener) {
            super(itemView);
          //  mItemIcon=itemView.findViewById(R.id.re);
            mItemName=itemView.findViewById(R.id.receiptitemname);
            mItemPrice=itemView.findViewById(R.id.receiptitenprice);
            mItemQuantity=itemView.findViewById(R.id.receiptitemquantity);

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


    public ReceiptAdapter(ArrayList<ReceiptItem> receiptList){
        mReceiptList=receiptList;
    }
    @NonNull
    @Override
    public ReceiptAdapter.ReceiptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.receiptitem_layout,parent,false);
        ReceiptAdapter.ReceiptViewHolder evh=new ReceiptAdapter.ReceiptViewHolder(v,mlistener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptAdapter.ReceiptViewHolder holder, int position) {
        ReceiptItem currentItem=mReceiptList.get(position);

       // holder.mItemIcon.setImageResource(currentItem.getReceiptItemImg());
        holder.mItemName.setText(currentItem.getReceiptItemName());
        holder.mItemPrice.setText(currentItem.getReceiptItemPrice());
        holder.mItemQuantity.setText(currentItem.getReceiptItemQuantity());
    }

    @Override
    public int getItemCount() {
        return mReceiptList.size();
    }
}

