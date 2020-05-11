package com.sih.sihsignupmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sih.sihsignupmodule.Adapter.ReceiptAdapter;
import com.sih.sihsignupmodule.Item.ReceiptItem;

import java.util.ArrayList;
import java.util.List;

public class receiptActivity extends AppCompatActivity {

    private RecyclerView mrecyclerView;
    private ReceiptAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ReceiptItem> receiptItemList=new ArrayList<>();
    Button payment;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        payment=findViewById(R.id.receiptPayment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(receiptActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });

        mrecyclerView=findViewById(R.id.reciptrecycleview);
        mrecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);

       // receiptItemList.add(new ReceiptItem(1,"Rice","100" ,"5"));
        receiptItemList.add(new ReceiptItem(2,"RICE","150" ,"4"));
        receiptItemList.add(new ReceiptItem(3,"RICE","150" ,"4"));

        mAdapter=new ReceiptAdapter((ArrayList<ReceiptItem>) receiptItemList);
        mrecyclerView.setLayoutManager(mLayoutManager);
        mrecyclerView.setAdapter(mAdapter);





    }
}
