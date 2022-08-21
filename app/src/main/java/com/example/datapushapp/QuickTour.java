package com.example.datapushapp;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuickTour extends AppCompatActivity {

    CircleImageView img;
    EditText artist, descri, accno,aname,pincode;
    String locationAddress;
    TextView lat;
    Button submit,view;
    FirebaseDatabase rootNode;
    private Uri ImageUri;
    String museumname;
    StorageReference storageReference;
    public static final int GalleryPick = 1;
    ProgressDialog loadingbar;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_tour);
        img=findViewById(R.id.img);
        loadingbar=new ProgressDialog(this);
        artist= findViewById(R.id.artist);
        lat=findViewById(R.id.Dept);
        museumname= getIntent().getStringExtra("name");

        pincode=findViewById(R.id.pincode);
        descri=findViewById(R.id.descri);
        accno=findViewById(R.id.accno);
        submit=findViewById(R.id.submit);
        aname=findViewById(R.id.aname);
        view=findViewById(R.id.view);

        storageReference = FirebaseStorage.getInstance().getReference("Images");

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("QuickTour");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuickTour.this,ArtViewActivity.class);
                i.putExtra("pass","QuickTour");
                i.putExtra("name",museumname);
                startActivity(i);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StoreProductInformation();
            }
        });



    }
    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            lat.setText(locationAddress+"");
        }
    }
    private void StoreProductInformation()
    {
        loadingbar.setMessage("Please Wait");
        loadingbar.setTitle("Adding New Product");
        loadingbar.setCancelable(false);
        loadingbar.show();
        UploadImage();


    }

    public void UploadImage() {

        if (ImageUri != null) {

            loadingbar.setTitle("Image is Uploading...");
            loadingbar.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(ImageUri));
            storageReference2.putFile(ImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                            loadingbar.dismiss();
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                            storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url = uri.toString();
                                    String ImageUploadId = reference.push().getKey();
                                    Vendoritemmodel addnewUser = new Vendoritemmodel(museumname,descri.getText().toString(),accno.getText().toString(),aname.getText().toString(),artist.getText().toString(),url,pincode.getText().toString());
                                    reference.child(museumname).child(ImageUploadId).setValue(addnewUser);

//                                    Intent intent=new Intent(NewActivity.this,LoginActivity.class)
//                                    startActivity(intent);
                                }
                            });


//                            @SuppressWarnings("VisibleForTests")
//
//                            uploadinfo imageUploadInfo = new uploadinfo(TempImageName,TempImageDescription,TempImagePrice, taskSnapshot.getUploadSessionUri().toString());
//                            String ImageUploadId = databaseReference.push().getKey();
//                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                        }
                    });
        }
        else {

            Toast.makeText(QuickTour.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GalleryPick && resultCode==RESULT_OK && data!=null)
        {
            ImageUri=data.getData();
            img.setImageURI(ImageUri);
        }
    }









    private void OpenGallery()
    {
        Intent galleryintent=new Intent();
        galleryintent.setAction(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        startActivityForResult(galleryintent,GalleryPick);
    }
}