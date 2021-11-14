package com.example.pracownik;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ZobaczMojeZadaniaAdapter extends RecyclerView.Adapter<ZobaczMojeZadaniaAdapter.MyViewHolder>  {

    Context context;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    FirebaseDatabase db;
    ArrayList<Zadania> list;


    public ZobaczMojeZadaniaAdapter(Context context, ArrayList<Zadania> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.wyglad_listy_zadania,parent,false);


/*
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("nazwa zadania="+list.get(viewType).getNazwa_zadania());
                Toast.makeText(context,"Hello, clicked "+list.get(viewType).getNazwa_zadania(),Toast.LENGTH_SHORT).show();
                System.out.println("Publikacja="+list.get(viewType).getData_publikacji());
                // v.setBackgroundColor(Color.parseColor("#FF03DAC5"));



            }
        });     */


        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        mAuth = FirebaseAuth.getInstance();
        Zadania zd = list.get(position);


        holder.nazwa_zadania.setText(zd.getNazwa_zadania());
        holder.opis_zadania.setText(zd.getOpis_zadania());
        holder.miejsce.setText(zd.getMiejsce());
        holder.data_publikacji.setText(zd.getData_publikacji());
        holder.wynagrodzenie.setText(zd.getWynagrodzenie().toString());
        if(zd.getSpr().equals("wykonane")){
            holder.itemView.setBackgroundColor(Color.RED);
        }


    /*    if(zd.getWykonawca().equals(mAuth.getCurrentUser().toString())){
            holder.itemView.setBackgroundColor(Color.GREEN)  ;;
        } else { holder.itemView.setBackgroundColor(Color.RED); }*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.get(position).getSpr().equals("brak")){
                    Toast.makeText(context, "Wybrano:  " + list.get(position).getNazwa_zadania(), Toast.LENGTH_SHORT).show();

                // Toast.makeText(context, "Hello, clicked " + list.get(position).getNazwa_zadania(), Toast.LENGTH_SHORT).show();

                // AlertDialog dialog = new AlertDialog();
                // setup the alert builder

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setTitle("Sprawozdanie"); // tytuł okna dialogowego
                builder.setMessage("Czy chcesz dodać sprawozdania z zadania: " + list.get(position).getNazwa_zadania() + "?");
                // dodanie i obsługa przycisku Tak
                builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Successfuly click", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, SprawozdanieActivity.class);
                        intent.putExtra("nazwa_zadania", list.get(position).getNazwa_zadania());
                        intent.putExtra("opis_zadania", list.get(position).getOpis_zadania());
                        intent.putExtra("miejsce", list.get(position).getMiejsce());
                        intent.putExtra("data_publikacji",list.get(position).getData_publikacji());
                        intent.putExtra("data_przyjecia",list.get(position).getData_przyjecia());
                        Double w =list.get(position).getWynagrodzenie();
                        String wynagro = Double.toString(w);
                        intent.putExtra("wynagrodzenie", wynagro);

                        context.startActivity(intent);


                        /*   Zadania zadanie = new Zadania(list.get(position).getNazwa_zadania(), list.get(position).getOpis_zadania(), list.get(position).getMiejsce(),list.get(position).getWykonawca(), list.get(position).getData_publikacji(), getDateTime(), list.get(position).getWynagrodzenie(),"Tak");


                            db = FirebaseDatabase.getInstance();
                            reference = db.getReference("Zadania");
                            reference.child(list.get(position).getNazwa_zadania()).setValue(zadanie).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    holder.itemView.setBackgroundColor(Color.RED);

                                    Toast.makeText(context, "Successfuly Updated", Toast.LENGTH_SHORT).show();

                                }
                            });    */

                    }
                });
                // dodanie przycisku anuluj
                builder.setNegativeButton("Anuluj", null);
                // create and show the alert dialog
                android.app.AlertDialog dialog = builder.create();
                dialog.show();

            }else {     Toast.makeText(context, "Sprawozdanie zostło wykonane!! ", Toast.LENGTH_SHORT).show();}





                //------------------------------------------------------------------------------


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nazwa_zadania, opis_zadania, miejsce, data_publikacji,wynagrodzenie;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            nazwa_zadania= itemView.findViewById(R.id.nazwa_zadania);
            opis_zadania = itemView.findViewById(R.id.opis_zadania);
            miejsce = itemView.findViewById(R.id.miejsce);
            data_publikacji = itemView.findViewById(R.id.data_publikacji);
            wynagrodzenie = itemView.findViewById(R.id.wynagrodzenie);



        }
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}