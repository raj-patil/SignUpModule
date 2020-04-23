package com.sih.sihsignupmodule;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UserKycActivity extends AppCompatActivity {


    Button btnChooseRationCard , btnChooseAdharCard , btnChoosePanCard,btnUploadRationCard,btnUploadAdharCard ,btnUploadPanCard,btnSignUp;
    private final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath , filePathRationCard=null ,filePathAdharCard=null , filePathPanCard=null ;
    String fileName , fileNameRationCard=null, fileNameAdharCard=null , fileNamePanCard=null;
    FirebaseStorage storage;
    int ruploadsts=0,auploadsts=0,puploadsts=0;
    StorageReference storageReference;
    static int docType=0;
    TextView rationcard,adharcard,pancard;

    String strfirstname,strmiddlename,strlastname,stremail,strcontact,strrationcardnumber,stradharcardnumber,strpancardnumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registration_kyc);
        storageReference=FirebaseStorage.getInstance().getReference("Images");

        btnChooseRationCard=findViewById(R.id.btnchooserationcard);
        btnChooseAdharCard=findViewById(R.id.btnchooseadharcard);
        btnChoosePanCard=findViewById(R.id.btnchoosepancard);
        btnUploadRationCard=findViewById(R.id.btnuploadrationcard);
        btnUploadAdharCard=findViewById(R.id.btnuploadadharcard);
        btnUploadPanCard=findViewById(R.id.btnuploadpancard);
        btnSignUp=findViewById(R.id.btnsignup);
        rationcard=findViewById(R.id.rationcardname);
        adharcard=findViewById(R.id.adharcardname);
        pancard=findViewById(R.id.pancardname);

        btnChooseRationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docType=1;
                chooseFile();
            }
        });
        btnChooseAdharCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docType=2;
                chooseFile();
            }
        });
        btnChoosePanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docType=3;
                chooseFile();
            }
        });

        btnUploadRationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docType=1;
               uploadFile();
            }
        });
        btnUploadAdharCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docType=2;
                uploadFile();
            }
        });
        btnUploadPanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docType=3;
                uploadFile();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(ruploadsts==1&&auploadsts==1&&puploadsts==1)
                    {
                        Intent intent = new Intent(UserKycActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }
            }
        });

    }


    private void chooseFile() {

        Intent intent = new Intent();
        intent.setType("image/*|application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);

    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {

            if(docType==1)
            {
                filePathRationCard = data.getData();

                Cursor returnCursor =
                        getContentResolver().query(filePathRationCard, null, null, null, null);

                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);

                returnCursor.moveToFirst();

                fileNameRationCard=returnCursor.getString(nameIndex);
                rationcard.setVisibility(View.VISIBLE);
                rationcard.setText(fileNameRationCard);

                Toast.makeText(UserKycActivity.this,fileNameRationCard,Toast.LENGTH_LONG).show();
            }
            if(docType==2)
            {

                filePathAdharCard = data.getData();
                Cursor returnCursor =
                        getContentResolver().query(filePathAdharCard, null, null, null, null);

                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                returnCursor.moveToFirst();
                fileNameAdharCard=returnCursor.getString(nameIndex);
                adharcard.setVisibility(View.VISIBLE);
                adharcard.setText(fileNameAdharCard);
                Toast.makeText(UserKycActivity.this,fileNameAdharCard,Toast.LENGTH_LONG).show();
            }
            if(docType==3)
            {

                filePathPanCard= data.getData();
                Cursor returnCursor =
                        getContentResolver().query(filePathPanCard, null, null, null, null);

                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                returnCursor.moveToFirst();
                fileNamePanCard=returnCursor.getString(nameIndex);
                pancard.setVisibility(View.VISIBLE);
                pancard.setText(fileNamePanCard);
                Toast.makeText(UserKycActivity.this,fileNamePanCard,Toast.LENGTH_LONG).show();
            }
//            filePath = data.getData();
//
//            Cursor returnCursor =
//                    getContentResolver().query(filePath, null, null, null, null);
//
//            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//
//            returnCursor.moveToFirst();
//
//            fileName=returnCursor.getString(nameIndex);
//
////           fileName = filePath.getLastPathSegment().toString();
////            File f1=new File(String.valueOf(filePath));
////
////             fileName=f1.getName();
//            btnChooseRationCard.setText(fileName);
        //    Toast.makeText(UserKycActivity.this,fileName,Toast.LENGTH_LONG).show();
        }
    }

    ///
    private void uploadFile() {


        if(docType==1)
        {
            if(filePathRationCard != null)
            {
                Toast.makeText(UserKycActivity.this,fileNameRationCard,Toast.LENGTH_LONG).show();
                final StorageReference ref = storageReference.child("file/RationCard/"+fileNameRationCard );

                ref.putFile(filePathRationCard)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(UserKycActivity.this, "RationCardUploaded", Toast.LENGTH_SHORT).show();
                                ruploadsts=1;
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(UserKycActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                        .getTotalByteCount());
                            }
                        });
            }
        }

        if(docType==2)
        {
            if(filePathAdharCard != null)
            {
                Toast.makeText(UserKycActivity.this,fileNameAdharCard,Toast.LENGTH_LONG).show();
                final StorageReference ref = storageReference.child("file/AdharCard/"+fileNameAdharCard );

                ref.putFile(filePathAdharCard)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(UserKycActivity.this, "AdharCardUploaded", Toast.LENGTH_SHORT).show();
                                auploadsts=1;
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(UserKycActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                        .getTotalByteCount());
                            }
                        });
            }

        }
        if(docType==3)
        {
            if(filePathPanCard != null)
            {
                Toast.makeText(UserKycActivity.this,fileNamePanCard,Toast.LENGTH_LONG).show();
                final StorageReference ref = storageReference.child("file/PanCard/"+fileNamePanCard );

                ref.putFile(filePathPanCard)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(UserKycActivity.this, "PanCardUploaded", Toast.LENGTH_SHORT).show();
                                puploadsts=1;
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(UserKycActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                        .getTotalByteCount());
                            }
                        });
            }

        }




//        if(filePath != null)
//        {
//            Toast.makeText(UserKycActivity.this,fileName,Toast.LENGTH_LONG).show();
////            final ProgressDialog progressDialog = new ProgressDialog(this);
////            progressDialog.setTitle("Uploading...");
////            progressDialog.show();
//
////            StorageReference ref = storageReference.child("file/"+subName+"/"+fileName );
//            final StorageReference ref = storageReference.child("file/"+fileName );
//            //
//            ref.putFile(filePath)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                           /// progressDialog.dismiss();
//                            Toast.makeText(UserKycActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
//
////
////                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
////
////                                @Override
////                                public void onSuccess(Uri uri) {
////                                    Uri downloadUrl = uri;
////                                    Toast.makeText(MainActivity.this, "Upload Done", Toast.LENGTH_LONG).show();
////                                    //After upload Complete we have to store the Data to firestore.
////                                    Map<String, Object> file = new HashMap<>();
////                                    file.put("url", downloadUrl.toString());
////                                    db.collection("files").document(filePath.toString()).set(file);
////
////                                }
////                            });
//                            ;
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                          //  progressDialog.dismiss();
//                            Toast.makeText(UserKycActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
//                                    .getTotalByteCount());
//                         //   progressDialog.setMessage("Uploaded "+(int)progress+"%");
//                        }
//                    });
//        }
//        else
//        {
//            Toast.makeText(UserKycActivity.this,"error",Toast.LENGTH_LONG).show();
//        }
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
