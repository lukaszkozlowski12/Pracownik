package com.example.pracownik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;




public class ZobaczSprawozdania extends AppCompatActivity {

    RecyclerView recyclerView;

    DatabaseReference database;
    ZobaczSprawozdaniaAdapter myAdapter;
    ArrayList<Sprawozdania> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zobacz_sprawozdania);
        recyclerView = findViewById(R.id.sprawozdaniaList);
        database = FirebaseDatabase.getInstance().getReference("Sprawozdania");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new ZobaczSprawozdaniaAdapter(this,list);

        recyclerView.setAdapter(myAdapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                   Sprawozdania zd = dataSnapshot.getValue(Sprawozdania.class);

                        list.add(zd);




                }

                if(myAdapter.getItemCount()==0) {           Toast.makeText(ZobaczSprawozdania.this, "Brak zadań!", Toast.LENGTH_SHORT).show(); }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }}
