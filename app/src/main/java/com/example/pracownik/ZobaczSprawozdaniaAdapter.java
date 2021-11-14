package com.example.pracownik;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ZobaczSprawozdaniaAdapter extends RecyclerView.Adapter<ZobaczSprawozdaniaAdapter.MyViewHolder>  {

    Context context;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    FirebaseDatabase db;
    ArrayList<Sprawozdania> list;


    public ZobaczSprawozdaniaAdapter(Context context, ArrayList<Sprawozdania> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.wyglad_listysprawozdania,parent,false);




        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        mAuth = FirebaseAuth.getInstance();
        Sprawozdania zd = list.get(position);


        Glide.with(context).load(list.get(position).getUrl()).into(holder.imageView);
        holder.wykonawca.setText(zd.getWykonawca());
        holder.opis.setText(zd.getInformacja());
       // holder.imageView.setImageURI(Uri.parse(zd.getUrl()));




    /*    if(zd.getWykonawca().equals(mAuth.getCurrentUser().toString())){
            holder.itemView.setBackgroundColor(Color.GREEN)  ;;
        } else { holder.itemView.setBackgroundColor(Color.RED); }*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  if (list.get(position).getSpr().equals("brak")){
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
                            });

                        }
                    });
                    // dodanie przycisku anuluj
                    builder.setNegativeButton("Anuluj", null);
                    // create and show the alert dialog
                    android.app.AlertDialog dialog = builder.create();
                    dialog.show();

                }else {     Toast.makeText(context, "Sprawozdanie zostło wykonane!! ", Toast.LENGTH_SHORT).show();}

*/



                //------------------------------------------------------------------------------

                Toast.makeText(context, "Sprawozdanie zostło wykonane!! ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView wykonawca, opis;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            wykonawca= itemView.findViewById(R.id.idWykonawca);
            opis = itemView.findViewById(R.id.OpisSprawka);
            imageView = itemView.findViewById(R.id.imageCircle);




        }
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}