package com.sih.sihsignupmodule;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RatingActivity extends AppCompatActivity {


    Dialog dialog;
    Button ratingsubmit;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        dialog=new Dialog(this);

                dialog.setContentView(R.layout.dialog_sucussfull);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


        ratingsubmit=findViewById(R.id.ratingSubmit);
        ratingsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RatingActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });


    }


}
