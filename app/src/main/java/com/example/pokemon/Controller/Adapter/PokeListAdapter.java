package com.example.pokemon.Controller.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokemon.Controller.Common.Constant;
import com.example.pokemon.Controller.Interface.iClickListener;
import com.example.pokemon.Model.Pokemon;
import com.example.pokemon.R;

import java.util.List;

public class PokeListAdapter extends RecyclerView.Adapter<PokeListAdapter.MyViewHolder>{
    Context context;
    List<Pokemon> pokemonList ;

    public PokeListAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(pokemonList.get(position).getImg()).into(holder.pokemon_image);
        holder.pokemon_name.setText(pokemonList.get(position).getName());
        holder.setiItemClickListener(new iClickListener() {
            @Override
            public void onClick(View view, int position) {
               Toast.makeText(context,"Hey,it's :"+pokemonList.get(position).getName(), Toast.LENGTH_LONG).show();
                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(new Intent(Constant.KEY_ENABLE_HOME).putExtra("num",pokemonList.get(position).getNum()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView pokemon_image;
        TextView pokemon_name;

        iClickListener iItemClickListener;

        public void setiItemClickListener(iClickListener iItemClickListener) {
            this.iItemClickListener = iItemClickListener;
        }

        public MyViewHolder(View itemView) {
            super(itemView);
            pokemon_image = (ImageView)itemView.findViewById(R.id.pokemon_image);
            pokemon_name = (TextView)itemView.findViewById(R.id.txt_pokemon_name);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            iItemClickListener.onClick(view, getAdapterPosition());
        }
    }
}
