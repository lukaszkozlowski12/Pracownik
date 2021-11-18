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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotatkiAdapter extends RecyclerView.Adapter<NotatkiAdapter.MyViewHolder> {

    Context context;
    FirebaseDatabase db;
    DatabaseReference reference;
    ArrayList<Informacje> list;
    FirebaseAuth mAuth;



    public NotatkiAdapter(Context context, ArrayList<Informacje> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemnotatki,parent,false);

        System.out.println("Witaj w create view .....");
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        System.out.println("Witaj w bindViewholder .................");
        mAuth = FirebaseAuth.getInstance();
        Informacje info = list.get(position);
        System.out.println("Tytulbind = "+info.getTytul());
        holder.autor.setText(info.getUserName());
        holder.tytul.setText(info.getTytul());
        holder.tresc.setText(info.getNotatka());
if(mAuth.getCurrentUser().getDisplayName().equals("admin")){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, "Hello, clicked " + list.get(position).getTytul(), Toast.LENGTH_SHORT).show();

                // AlertDialog dialog = new AlertDialog();
                // setup the alert builder

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setTitle("Usuwanie danych"); // tytuł okna dialogowego
                builder.setMessage("Czy chcesz usunąć to zadanie: " + list.get(position).getTytul() + "?");
                // dodanie i obsługa przycisku Tak
                builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        db = FirebaseDatabase.getInstance();
                        reference = db.getReference("Ogłoszenia");
                        reference.child(list.get(position).getTytul()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {

                            @Override
                            public void onSuccess(Void aVoid) {  System.out.println("usunieto .................");
                                Toast.makeText(context, "Usunięto: " + list.get(position).getTytul(), Toast.LENGTH_SHORT).show();
                                list.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());

                                notifyItemRangeChanged(holder.getAdapterPosition(), list.size());








                            }
                        });

                    }
                });
                // dodanie przycisku anuluj
                builder.setNegativeButton("Anuluj", null);
                // create and show the alert dialog
                android.app.AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
}

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView autor, tytul, tresc ;
        RecyclerView recyclerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            autor = itemView.findViewById(R.id.etAutor);
            tytul = itemView.findViewById(R.id.etTytulnotatki);
            tresc = itemView.findViewById(R.id.etTresc);
recyclerView=itemView.findViewById(R.id.notatkiList);
        }
    }

}