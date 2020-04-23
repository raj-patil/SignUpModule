package com.sih.sihsignupmodule;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity {

    TextView firstname,middlename,lastname,email,contact,ration,adhar,pan;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        session = new SessionManager(getApplicationContext());

        firstname=findViewById(R.id.firstname);
        middlename=findViewById(R.id.middlename);
        lastname=findViewById(R.id.lastname);
        contact=findViewById(R.id.contacr);
        email=findViewById(R.id.email);
        ration=findViewById(R.id.rationcard);
        adhar=findViewById(R.id.adharcard);
        pan=findViewById(R.id.pancard);

        HashMap<String, String> user = session.getUserDetails();
        String strfname = user.get(SessionManager.USERFIRSTNAME);
        String strmname = user.get(SessionManager.USERMIDDLENAME);
        String strlname = user.get(SessionManager.USERLASTNAME);
        String stremail = user.get(SessionManager.USEREMAIL);
        String strcontact = user.get(SessionManager.USERCONTACT);
        String strration = user.get(SessionManager.USERRATIONCARDNUMBER);
        String  stradhar= user.get(SessionManager.USERADHARCARDNUMBER);
        String strpan = user.get(SessionManager.USERPANCARDNUMBER);

        Toast.makeText(DashboardActivity.this,strfname,Toast.LENGTH_SHORT).show();
        Toast.makeText(DashboardActivity.this,strmname,Toast.LENGTH_SHORT).show();
        Toast.makeText(DashboardActivity.this,strlname,Toast.LENGTH_SHORT).show();
        Toast.makeText(DashboardActivity.this,strcontact,Toast.LENGTH_SHORT).show();
        firstname.setText(strfname);
        middlename.setText(strmname);
        lastname.setText(strlname);
        contact.setText(strcontact);
        email.setText(stremail);
        ration.setText(strration);
        adhar.setText(stradhar);
        pan.setText(strpan);


    }
}
