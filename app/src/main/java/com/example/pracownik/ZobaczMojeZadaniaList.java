package com.example.pracownik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ZobaczMojeZadaniaList extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseAuth mAuth;
    DatabaseReference database;
    ZobaczMojeZadaniaAdapter myAdapter;
    ArrayList<Zadania> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zobacz_moje_zadania_list);
        recyclerView = findViewById(R.id.listaZadan);
        database = FirebaseDatabase.getInstance().getReference("Zadania");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAuth = FirebaseAuth.getInstance();
        list = new ArrayList<>();
        myAdapter = new ZobaczMojeZadaniaAdapter(this,list);

        recyclerView.setAdapter(myAdapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Zadania zd = dataSnapshot.getValue(Zadania.class);
                    if(!zd.getWykonawca().equals("Brak")) {

                        if(zd.getWykonawca().equals(mAuth.getCurrentUser().getDisplayName()))
                        list.add(zd);


                    }



                }
                if(myAdapter.getItemCount()==0) {           Toast.makeText(ZobaczMojeZadaniaList.this, "Brak !", Toast.LENGTH_SHORT).show(); }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }}
