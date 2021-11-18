package com.example.pracownik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
  // ogłoszenie
public class Notatka extends AppCompatActivity {
EditText etTytul, etNotatka;
    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    String tytul,notatka,data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notatka);
        mAuth = FirebaseAuth.getInstance();

        etTytul=((EditText)findViewById(R.id.etTytul));
        etNotatka=((EditText)findViewById(R.id.etNotatka));


        ((Button)findViewById(R.id.btnZapisz)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notatka=etNotatka.getText().toString();
                tytul=etTytul.getText().toString();

data=getDateTime();
String username= mAuth.getCurrentUser().getDisplayName().toString();



               // System.out.println("Dodawanie ");
            //    System.out.println("data="+new Date().toString());


                Informacje info=new Informacje(tytul,notatka,data,username);

                db = FirebaseDatabase.getInstance();
                reference = db.getReference("Ogłoszenia");
                reference.child(tytul).setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        Toast.makeText(Notatka.this,"Successfuly Updated",Toast.LENGTH_SHORT).show();
finish();
                    }
                });






            }
        });




    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


  }