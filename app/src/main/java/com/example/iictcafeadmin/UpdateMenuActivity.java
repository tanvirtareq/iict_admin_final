package com.example.iictcafeadmin;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UpdateMenuActivity extends AppCompatActivity implements View.OnClickListener{

    private Button chooseImageButton, saveImageButton ,  updateMenuButton;
    private ImageView imageView;
    private ProgressBar progressBar;
    private EditText editText,priceText;
    private Uri imageUri;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    StorageTask uploadTask;

    private  static  final int IMAGE_REQUEST  = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_menu);
        chooseImageButton = findViewById(R.id.chooseImageButton);
        saveImageButton= findViewById(R.id.saveImageButton);
        updateMenuButton =findViewById(R.id.updateMenuButton);

        imageView= findViewById(R.id.imageViewId);
        progressBar=findViewById(R.id.progressbarid);

        databaseReference= FirebaseDatabase.getInstance().getReference("food");
        storageReference= FirebaseStorage.getInstance().getReference("upload");
        editText= findViewById(R.id.textid);
        priceText=findViewById(R.id.priceid);
        chooseImageButton.setOnClickListener(this);
        saveImageButton.setOnClickListener(this);
        loadImageButton.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.chooseImageButton:
                openFileChooser();
                break;
            case R.id.saveImageButton:
                if (uploadTask!=null && uploadTask.isInProgress()){
                    Toast.makeText(getApplicationContext(), "Please Wait", Toast.LENGTH_LONG).show();
                }
                else {
                    saveData();
                }
                break;
            case R.id.updateMenuButton:
//                Intent intent = new Intent(UpdateMenuActivity.this, ImageActivity.class);
//                startActivity(intent);
                break;
        }
    }
    void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == IMAGE_REQUEST && resultCode==RESULT_OK && data != null && data.getData()!= null){
            imageUri=data.getData();
            Picasso.get().load(imageUri).into(imageView);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public  String getFileExtension(Uri imageUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap= MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }
    private void saveData(){
        final String imageName= editText.getText().toString().trim();
        final String imagePrice=priceText.getText().toString().trim();
        if(imageName.isEmpty()){
            editText.setError("Enter Image Name");
            editText.requestFocus();
            return ;
        }
        StorageReference ref= storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));
        ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Toast.makeText(getApplicationContext(),"Image is stored Successfully",Toast.LENGTH_LONG).show();

                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();

                        while (!urlTask.isSuccessful());
                        Uri downloadUrl=urlTask.getResult();
                        //Upload upload= new Upload(imageName, downloadUrl.toString(),imagePrice);
                        Upload upload= new Upload();
                        upload.setAvaibality(true);
                        upload.setImage_url(downloadUrl.toString());
                        upload.setPrice(Integer.parseInt(imagePrice));
                        upload.setItem_name(imageName);

                        String uploadId = databaseReference.push().getKey();

                        upload.setItem_id(uploadId);

                        databaseReference.child(uploadId).setValue(upload);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(getApplicationContext(),"Image is not stored",Toast.LENGTH_LONG).show();

                    }
                });

    }
}