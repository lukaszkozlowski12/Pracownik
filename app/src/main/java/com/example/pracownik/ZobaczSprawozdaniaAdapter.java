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