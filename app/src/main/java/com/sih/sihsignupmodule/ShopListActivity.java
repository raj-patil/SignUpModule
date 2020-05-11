package com.sih.sihsignupmodule;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sih.sihsignupmodule.Adapter.ExampleAdapter;
import com.sih.sihsignupmodule.Item.ExampleItem;

import java.util.ArrayList;
import java.util.List;

public class ShopListActivity extends AppCompatActivity {

    private RecyclerView mrecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ExampleItem> ShopList=new ArrayList<>();
    FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        mrecyclerView=findViewById(R.id.recyclerView);
        mrecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);


             // ShopList= dataFromFirebase();

        db= FirebaseFirestore.getInstance();
        db.collection("ShopList")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ExampleItem ex = new ExampleItem(R.mipmap.ic_ration, document.getString("ShopName"), document.getString("Licence"), document.getString("ShopAddress"));
                                ShopList.add(ex);
                                //ShopList.add(new ExampleItem(R.mipmap.ic_ration,document.getString("ShopName"),document.getString("Licence") ,document.getString("ShopAddress")));
                                Toast.makeText(ShopListActivity.this, ex.getmText1() + document.getString("Licence"), Toast.LENGTH_SHORT).show();
                            }
                            mAdapter=new ExampleAdapter((ArrayList<ExampleItem>) ShopList);
                            mrecyclerView.setLayoutManager(mLayoutManager);
                            mrecyclerView.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
                                @Override
                                public void OnItemClick(int position) {
                                    //ShopList.get(position).getmText2();
                                    Intent intent=new Intent(ShopListActivity.this,shopDetailActivity.class);
                                    intent.putExtra("ShopId", ShopList.get(position).getmText2());
                                    startActivity(intent);
                                    //  mAdapter.notifyItemChanged(position);
                                }
                            });
                        }
                    }
                });


//
//        ShopList.add(new ExampleItem(R.mipmap.ic_ration,"Pune Ration Shops","1001" ,"shivajiNagar"));
//        ShopList.add(new ExampleItem(R.mipmap.ic_ration,"Latur rations"," 1002","Railway Stationn"));
//        ShopList.add(new ExampleItem(R.mipmap.ic_ration,"Govt Shop,Latur","1003" , "ShahuNagar"));
//        ShopList.add(new ExampleItem(R.mipmap.ic_ration,"Pune Shop","1004" , "temp address"));
//        ShopList.add(new ExampleItem(R.mipmap.ic_ration,"Pune Ration Shops","1005", "temp address"));
//        ShopList.add(new ExampleItem(R.mipmap.ic_ration,"Latur rations","1006", "temp address"));
//        ShopList.add(new ExampleItem(R.mipmap.ic_ration,"Govt Shop,Latur","1007", "temp address"));
//        ShopList.add(new ExampleItem(R.mipmap.ic_ration,"Pune Shop","1008", "temp address"));
////


    }


//   final private List dataFromFirebase()
//    {
//        final List shop=new ArrayList();
//        db= FirebaseFirestore.getInstance();
//        db.collection("ShopList")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                                ExampleItem ex= new ExampleItem(R.mipmap.ic_ration,document.getString("ShopName"),document.getString("Licence") ,document.getString("ShopAddress"));
//                                shop.add(ex);
//                                // ShopList.add(new ExampleItem(R.mipmap.ic_ration,document.getString("ShopName"),document.getString("Licence") ,document.getString("ShopAddress")));
//                                Toast.makeText(ShopListActivity.this,ex.getmText1() + document.getString("Licence"),Toast.LENGTH_SHORT).show();
//                            }
//
//                    }
//                });
//
//        return shop;
//    }

}
