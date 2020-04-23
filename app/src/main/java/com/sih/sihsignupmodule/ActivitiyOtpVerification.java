package com.sih.sihsignupmodule;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ActivitiyOtpVerification extends AppCompatActivity {


    Button verify_btn ;
    EditText phoneNoEnteredByTheUser;
    ProgressBar progressBar;
    String verificationCodeBySystem;
    NotificationManagerCompat notificationManager;
    NotificationCompat.Builder builder;
  static String strfirstname=null;
    static String strmiddlename=null;
    static String strlastname=null;
    static  String stremail=null;
    static String strcontact=null;
    static  String strrationcardnumber=null;
    static  String stradharcardnumber= null;
    static  String strpancardnumber=null;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitiy_otp_verification);

        session = new SessionManager(getApplicationContext());
          createNotificationChannel(); //For Oreo

          //Notification code for belove oreo Versions.
            builder = new NotificationCompat.Builder(this,"Notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Account Register Sucussfully")
                .setContentText("Thank You")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            notificationManager = NotificationManagerCompat.from(this);
            /////////////




        verify_btn = findViewById(R.id.verify_btn);
        phoneNoEnteredByTheUser = findViewById(R.id.verification_code_entered_by_user);
        progressBar = findViewById(R.id.progress_bar);

            progressBar.setVisibility(View.GONE);
        String phoneNo = getIntent().getStringExtra("phoneNo");
        sendVerificationCodeToUser(phoneNo);

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code = phoneNoEnteredByTheUser.getText().toString();

                if (code.isEmpty() || code.length() < 6) {
                    phoneNoEnteredByTheUser.setError("Wrong OTP...");
                    phoneNoEnteredByTheUser.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        });

    }

    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + phoneNo,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,   // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    String code = phoneAuthCredential.getSmsCode();
                   if (code != null) {
                       progressBar.setVisibility(View.VISIBLE);
                      verifyCode(code);
                   }
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(ActivitiyOtpVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    //Get the code in global variable
                    verificationCodeBySystem = s;
                }
            };
    private void verifyCode(String codeByUser) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
        signInTheUserByCredentials(credential);

    }


    //Methoon For Notification For Android 8+ version.
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "RegisterNotification";
            String description = "NotificationDescriptio";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Notification", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(ActivitiyOtpVerification.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(ActivitiyOtpVerification.this, "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();
                            //code for notification - Call NotifiMEthod-------------------
                            notificationManager.notify(1, builder.build());

                            //Adddata in session manager

                            session.createLoginSession(strfirstname,strmiddlename,strlastname,strcontact,stremail,strrationcardnumber,stradharcardnumber,strpancardnumber);
                            finish();
                            //Perform Your required action here to either let the user sign In or do something required
                            Intent intent = new Intent(getApplicationContext(), UserKycActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);




                        } else {
                            Toast.makeText(ActivitiyOtpVerification.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


   public void dataPass(String firstName, String middleName, String lastName, String email, String contact, String rationCardNumber, String adharCardNumber,String pancardnumber) {


        strfirstname=firstName;
        strmiddlename=middleName;
        strlastname=lastName;
        strcontact=contact;
        stremail=email;
        strrationcardnumber=rationCardNumber;
        stradharcardnumber=adharCardNumber;
        strpancardnumber=pancardnumber;

    }



}
