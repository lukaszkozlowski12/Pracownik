package com.example.pracownik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DodajZadanie extends AppCompatActivity {
    String nazwa_zadania, opis_zadania, miejsce, wykonawca, data_publikacji, data_przyjecia;
    Double wynagrodzenie;
    EditText nazwaZadaniaet, opiset,miejsceet,wynagrodzenieet;
    FirebaseDatabase db;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zadanie);
        mAuth = FirebaseAuth.getInstance();

        nazwaZadaniaet=((EditText)findViewById(R.id.etNazwaZadania));
        opiset=((EditText)findViewById(R.id.etOpiszadania));
        miejsceet=((EditText)findViewById(R.id.etMiejscezadania));

        wynagrodzenieet=((EditText)findViewById(R.id.etWynagrodzenie));
// pobanei danych z aktynosci:
        nazwa_zadania= nazwaZadaniaet.getText().toString();
        opis_zadania=opiset.getText().toString();
        miejsce=miejsceet.getText().toString();
        wykonawca="Brak";
        data_publikacji=getDateTime();
        data_przyjecia="Brak";
        wynagrodzenie= Double.parseDouble(wynagrodzenieet.getText().toString());
        System.out.println("Nazwa zadania"+nazwa_zadania+"opis"+opis_zadania+"miejsce"+miejsce+"wykonawca"+wykonawca+"Wynagrodzenie "+wynagrodzenie+"data pub"+data_publikacji);




        ((Button)findViewById(R.id.btnDodajZadanie)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nazwa_zadania= nazwaZadaniaet.getText().toString();
                opis_zadania=opiset.getText().toString();
                miejsce=miejsceet.getText().toString();
                wykonawca="Brak";
                data_publikacji=getDateTime();
                data_przyjecia="Brak";
                wynagrodzenie= Double.parseDouble(wynagrodzenieet.getText().toString());
                Zadania zadanie=new Zadania(nazwa_zadania, opis_zadania, miejsce, wykonawca, data_publikacji, data_przyjecia,wynagrodzenie,"brak");

                db = FirebaseDatabase.getInstance();
                reference = db.getReference("Zadania");
                reference.child(nazwa_zadania).setValue(zadanie).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {


                        Toast.makeText(DodajZadanie.this,"Successfuly Updated",Toast.LENGTH_SHORT).show();
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