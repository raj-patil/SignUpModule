package com.sih.sihsignupmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sih.sihsignupmodule.Adapter.CartAdapter;
import com.sih.sihsignupmodule.Item.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView mrecyclerView;
    private CartAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<CartItem> cartItemList=new ArrayList<>();
    Button chekout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        chekout=findViewById(R.id.checkout);
        chekout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartActivity.this,receiptActivity.class);
                startActivity(intent);
            }
        });
        mrecyclerView=findViewById(R.id.cartListRecyclerView);
        mrecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);

        cartItemList.add(new CartItem(R.mipmap.ic_ration,"Rice","100" ,"5"));
        cartItemList.add(new CartItem(R.mipmap.ic_ration,"RICE","150" ,"4"));

        mAdapter=new CartAdapter((ArrayList<CartItem>) cartItemList);
        mrecyclerView.setLayoutManager(mLayoutManager);
        mrecyclerView.setAdapter(mAdapter);



    }
}
