package com.example.pracownik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PanelActivity extends AppCompatActivity {
    Button btnLogOut;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);



        btnLogOut = findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();
        ((TextView)findViewById(R.id.textView)).setText("Zalogowany: "+mAuth.getCurrentUser().getDisplayName().toString());

        btnLogOut.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(PanelActivity.this, MainActivity.class));

        });




        ((Button)findViewById(R.id.btnZobacz)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PanelActivity.this,Userlist.class));

            }
        });


((Button)findViewById(R.id.btnNotatka)).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(PanelActivity.this,Notatka.class));

    }
});

        ((Button)findViewById(R.id.btnZobaczOgloszenia)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(PanelActivity.this,NotatkiList.class));
            }
        });

        ((Button)findViewById(R.id.btnDodajZadaniePanel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PanelActivity.this,DodajZadanie.class));

            }
        });


        ((Button)findViewById(R.id.btnZobaczZadania)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PanelActivity.this,ZobaczZadania.class));


            }
        });

        ((Button)findViewById(R.id.btnZobaczMojezadania)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(PanelActivity.this,ZobaczMojeZadaniaList.class));
            }
        });


        ((Button)findViewById(R.id.btnZobaczSprawka)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(PanelActivity.this,ZobaczSprawozdania.class));



            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(PanelActivity.this, Login.class));
        }
    }
}