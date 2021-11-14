package com.example.pracownik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotatkiAdapter extends RecyclerView.Adapter<NotatkiAdapter.MyViewHolder> {

    Context context;

    ArrayList<Informacje> list;


    public NotatkiAdapter(Context context, ArrayList<Informacje> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemnotatki,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Informacje info = list.get(position);
        holder.autor.setText(info.getUserName());
        holder.tytul.setText(info.getTytul());
        holder.tresc.setText(info.getNotatka());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView autor, tytul, tresc ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            autor = itemView.findViewById(R.id.etAutor);
            tytul = itemView.findViewById(R.id.etTytulnotatki);
            tresc = itemView.findViewById(R.id.etTresc);

        }
    }

}