package com.example.pracownik;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.model.Model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SprawozdanieActivity extends AppCompatActivity {
    private Button uploadBtn;

    private ImageView imageView;
    private ProgressBar progressBar;
    private EditText etInfoToSzef;
    FirebaseAuth mAuth;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Sprawozdania");
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    FirebaseDatabase db;
    DatabaseReference refdb;
    public String urrr;


    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprawozdanie);
        Intent intent = getIntent();
        uploadBtn = findViewById(R.id.btnWyslij);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        imageView = findViewById(R.id.imageViewupload);
        mAuth = FirebaseAuth.getInstance();
        String data_publikacji, data_przyjecia;
        data_publikacji=intent.getStringExtra("data_publikacji");
        data_przyjecia=intent.getStringExtra("data_przyjecia");
        String nazwa_zadania = intent.getStringExtra("nazwa_zadania");
        String  opis_zadania = intent.getStringExtra("opis_zadania");
        String  miejsce = intent.getStringExtra("miejsce");
        String  wynagrodzenie = intent.getStringExtra("wynagrodzenie");
        ((TextView)findViewById(R.id.tvNazwaZadania)).setText(nazwa_zadania);
        ((TextView)findViewById(R.id.tvOpis)).setText(opis_zadania);
        ((TextView)findViewById(R.id.tvMiejsce)).setText(miejsce);
        ((TextView)findViewById(R.id.tvWynagrodzenie)).setText(wynagrodzenie);


         etInfoToSzef= ((EditText)findViewById(R.id.etInfodlaSzefa));
        System.out.println("Dane="+nazwa_zadania+" "+opis_zadania+" "+miejsce+" "+wynagrodzenie);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent , 2);
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null && etInfoToSzef.getText().toString().length() >1 ){
                    System.out.println("Dane22="+nazwa_zadania+" "+opis_zadania+" "+miejsce+" "+wynagrodzenie);
                 Zadania zd= new Zadania(nazwa_zadania, opis_zadania,miejsce,mAuth.getCurrentUser().getDisplayName().toString(), data_publikacji, data_przyjecia, Double.parseDouble(wynagrodzenie),"wykonane");


                    db = FirebaseDatabase.getInstance();
                    refdb = db.getReference("Zadania");
                    refdb.child(nazwa_zadania).setValue(zd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                          //  Toast.makeText(SprawozdanieActivity.this,"Successfuly add!",Toast.LENGTH_SHORT).show();

                        }
                    });


          uploadToFirebase(imageUri,nazwa_zadania, opis_zadania,miejsce,mAuth.getCurrentUser().getDisplayName(), etInfoToSzef.getText().toString(),Double.parseDouble(wynagrodzenie) );

                    StorageReference pathReference = reference.child("images/"+nazwa_zadania+".jpeg");
                    System.out.println("Zdjecie spr = "+pathReference.getDownloadUrl());
                    System.out.println("LINK== "+imageUri.toString() );

                  Sprawozdania spr= new Sprawozdania(nazwa_zadania, opis_zadania,miejsce, mAuth.getCurrentUser().getDisplayName(), etInfoToSzef.getText().toString(),imageUri.toString() ,Double.parseDouble(wynagrodzenie));


                    db = FirebaseDatabase.getInstance();
                    refdb = db.getReference("Sprawozdania");
                    refdb.child(nazwa_zadania).setValue(spr).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                               finish();
                         startActivity(new Intent(SprawozdanieActivity.this, PanelActivity2.class));
                            //  Toast.makeText(SprawozdanieActivity.this,"Successfuly add!",Toast.LENGTH_SHORT).show();

                        }
                    });













                }else{
                    Toast.makeText(SprawozdanieActivity.this, "Dodaj zdjęcie!!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==2 && resultCode == RESULT_OK && data != null){

            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        }
    }

    private void uploadToFirebase(Uri uri, String name, String opis_zadania, String miejsce, String autor, String infodlaszefa, Double wynagrodzenie ){
String url="";

        //   final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        StorageReference fileRef = reference.child(name);
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Model2 model2 = new Model2 (uri.toString());
                        String modelId = root.push().getKey();
                        Sprawozdania spr= new Sprawozdania(name, opis_zadania,miejsce,autor,infodlaszefa,model2.getUrl(),wynagrodzenie);





                       urrr=modelId.toString();
                        System.out.println("Wynik url = "+model2.getUrl()+" lub "+modelId.toString());
                        root.child(name).setValue(spr);




                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(SprawozdanieActivity.this, "Uploaded Successfully"+modelId, Toast.LENGTH_SHORT).show();
                        imageView.setImageResource(R.drawable.ic_launcher_background);






                  }
                });
            }
        } ).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progressBar.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(SprawozdanieActivity.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }
}