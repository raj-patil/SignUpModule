package com.sih.sihsignupmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sih.sihsignupmodule.Adapter.ShopItemAdapter;
import com.sih.sihsignupmodule.Item.shopItem;

import java.util.ArrayList;

public class shopDetailActivity extends AppCompatActivity {

    private RecyclerView mrecyclerView;
    private ShopItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FirebaseFirestore db;
    DocumentReference documentReference;
    String ShopId;
    TextView distributerName,shopLocation,shopPinCode,shopContact,shopName;
    Button proceedtocart;
    String  strdistributerName,strshopLocation,strshopPinCode,strshopContact,strshopName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        Intent intent = getIntent();
         ShopId = intent.getStringExtra("ShopId");

        distributerName=findViewById(R.id.Distributername);
        shopLocation=findViewById(R.id.Location);
        shopContact=findViewById(R.id.shopContact);
        shopPinCode=findViewById(R.id.Pincode);
        shopName=findViewById(R.id.shopName);
        proceedtocart=findViewById(R.id.proceedtocart);


       db = FirebaseFirestore.getInstance();

        documentReference = db.document("Distributer/" + ShopId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()) {

                    strdistributerName=documentSnapshot.getString("FirstName")+ documentSnapshot.getString("MiddleName")+documentSnapshot.getString("LastName");
                    strshopLocation=documentSnapshot.getString("Location");
                    strshopPinCode=documentSnapshot.getString("Pincode");
                    strshopContact=documentSnapshot.getString("Contact");
                }

                distributerName.setText(strdistributerName);
                shopLocation.setText(strshopLocation);
                shopContact.setText(strshopContact);
                shopPinCode.setText(strshopPinCode);

            }
        });

        documentReference = db.document("ShopList/" + ShopId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()) {

                    strshopName=documentSnapshot.getString("ShopName");

                }

                shopName.setText(strshopName);


            }
        });


        final ArrayList<shopItem> ProductList=new ArrayList<>();
        ProductList.add(new shopItem("RICE","100" ));
        ProductList.add(new shopItem("RICE","100" ));
        ProductList.add(new shopItem("RICE","100" ));
        ProductList.add(new shopItem("RICE","100" ));


        mrecyclerView=findViewById(R.id.productsListRecyclerView);
        mrecyclerView.setHasFixedSize(true);
        mLayoutManager=new GridLayoutManager(this,2);
        mAdapter=new ShopItemAdapter(ProductList);
        mrecyclerView.setLayoutManager(mLayoutManager);
        mrecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ShopItemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                // ProductList.get(position).getmText2();
                // Intent intent=new Intent(ProductListActivity.this,TransDetails.class);
                ///startActivity(intent);
                //  mAdapter.notifyItemChanged(position);
            }
        });

        proceedtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(shopDetailActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });

    }
}
