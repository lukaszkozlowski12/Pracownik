package com.example.pracownik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PanelActivity2 extends AppCompatActivity {
    CardView btnLogout;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel2);



        btnLogout = findViewById(R.id.cardWyloguj);
        mAuth = FirebaseAuth.getInstance();
        ((TextView)findViewById(R.id.tvUser)).setText("Zalogowany: "+mAuth.getCurrentUser().getDisplayName().toString());

        btnLogout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(PanelActivity2.this, MainActivity.class));
        });



        if(mAuth.getCurrentUser().getDisplayName().toString().equals("admin")) {

            ((CardView) findViewById(R.id.cardZobaczPracownikow)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PanelActivity2.this, Userlist.class));


                }
            });


            ((CardView) findViewById(R.id.cardDodajOgloszenie)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PanelActivity2.this, Notatka.class));

                }
            });

            ((CardView) findViewById(R.id.cardZobaczOgloszenia)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(PanelActivity2.this, NotatkiList.class));
                }
            });

            ((CardView) findViewById(R.id.cardDodajZadanie)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PanelActivity2.this, DodajZadanie.class));

                }
            });


            ((CardView) findViewById(R.id.cardZobaczZadania)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PanelActivity2.this, ZobaczZadania.class));


                }
            });

            ((CardView) findViewById(R.id.cardZobaczMojeZadania)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(PanelActivity2.this, ZobaczMojeZadaniaList.class));
                }
            });


            ((CardView) findViewById(R.id.cardZobaczSprawozdania)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    startActivity(new Intent(PanelActivity2.this, ZobaczSprawozdania.class));


                }
            });

        } else {



            ((CardView) findViewById(R.id.cardZobaczPracownikow)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PanelActivity2.this, Userlist.class));


                }
            });


            ((CardView) findViewById(R.id.cardDodajOgloszenie)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PanelActivity2.this, Notatka.class));

                }
            });

            ((CardView) findViewById(R.id.cardZobaczOgloszenia)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(PanelActivity2.this, NotatkiList.class));
                }
            });

            ((CardView) findViewById(R.id.cardDodajZadanie)).setVisibility(View.GONE);

            ((CardView) findViewById(R.id.cardZobaczZadania)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(PanelActivity2.this, ZobaczZadania.class));


                }
            });

            ((CardView) findViewById(R.id.cardZobaczMojeZadania)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(PanelActivity2.this, ZobaczMojeZadaniaList.class));
                }
            });


            ((CardView) findViewById(R.id.cardZobaczSprawozdania)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    startActivity(new Intent(PanelActivity2.this, ZobaczSprawozdania.class));


                }
            });


        }



}
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(PanelActivity2.this, Login.class));
        }
    }

}