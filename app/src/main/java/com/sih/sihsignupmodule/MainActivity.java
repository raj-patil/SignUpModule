package com.sih.sihsignupmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnSIgnUpNext, btnSignUp, btnLogin;
    String firstName, middleName, lastName, rationCardNumber, adharCardNumber, panCardNumber, email, contact , password , confrimpassword;
    EditText editTextfirstName, editTextmiddleName, editTextlastName, editTextContact, editTextrationCardNumber, editTextadharCardNumber,editTextPassword,editTextConfirmPassword, editTextpanCardNumber, editTextemail;
   ActivitiyOtpVerification dataPassObj=new ActivitiyOtpVerification();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registration_user);


        btnSIgnUpNext = (Button) findViewById(R.id.btnsignupnext);
        btnSignUp = (Button) findViewById(R.id.btnsignup);
        final LinearLayout linearLayout = findViewById(R.id.signuplinnerlayout);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        btnSIgnUpNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "btnclick", Toast.LENGTH_SHORT).show();
                editTextfirstName = findViewById(R.id.editTextSignUpFirst);
                editTextmiddleName = findViewById(R.id.editTextSignUpMiddle);
                editTextlastName = findViewById(R.id.editTextSignUpLast);
                editTextemail = findViewById(R.id.editTextSignupEmail);
                editTextContact = findViewById(R.id.editTextSignUpContact);
                editTextrationCardNumber = findViewById(R.id.editTextSignupRationCardNumber);
                editTextadharCardNumber = findViewById(R.id.editTextAdharCardNumber);
                editTextPassword=findViewById(R.id.editTextPassword);
                editTextConfirmPassword=findViewById(R.id.editTextConfirmPassword);


                firstName = editTextfirstName.getText().toString().trim();
                middleName = editTextmiddleName.getText().toString().trim();
                lastName = editTextlastName.getText().toString().trim();
                rationCardNumber = editTextrationCardNumber.getText().toString().trim();
                adharCardNumber = editTextadharCardNumber.getText().toString().trim();
                email = editTextemail.getText().toString().trim();
                contact = editTextContact.getText().toString().trim();
                password=editTextPassword.getText().toString().trim();
                confrimpassword=editTextConfirmPassword.getText().toString().trim();


                dataPassObj.dataPass(firstName,middleName,lastName,email,contact,rationCardNumber,adharCardNumber,panCardNumber);

                Toast.makeText(MainActivity.this, password + " " +confrimpassword, Toast.LENGTH_SHORT).show();

                Map<String, String> record = new HashMap<>();
                record.put("FirstName", firstName);
                record.put("MiddleName", middleName);
                record.put("LastName", lastName);
                record.put("ContactNumber", contact);
                record.put("RationCardNumber", rationCardNumber);
                record.put("AdharCardNumber", adharCardNumber);
                record.put("Email", email);


                if(password.equals(confrimpassword)) {
                    db.collection("Users").document(rationCardNumber).set(record).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MainActivity.this, "sucuss", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, ActivitiyOtpVerification.class);
                            intent.putExtra("phoneNo", contact);
                            startActivity(intent);
                        }
                    });
                }
                else
                {

                        editTextConfirmPassword.setError(" Password dosent match...");
                        editTextConfirmPassword.requestFocus();
                        return;
                }

            }
        });



    }
}