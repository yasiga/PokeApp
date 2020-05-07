package com.example.pokemon.Controller.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokemon.Controller.Interface.iClickListener;
import com.example.pokemon.Model.Evolution;
import com.example.pokemon.R;
import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;

import java.util.ArrayList;
import java.util.List;

public class PokeEvolutionAdapter extends RecyclerView.Adapter<PokeEvolutionAdapter.MyViewHolder> {
    Context context;
    List<Evolution> evolutions;

    public PokeEvolutionAdapter(Context context, List<Evolution> evolutions) {
        this.context = context;
        if(evolutions!=null)
            this.evolutions = evolutions; else this.evolutions = new ArrayList<>();
    }

    @NonNull
    @Override
    public PokeEvolutionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,parent,false);
        return new PokeEvolutionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokeEvolutionAdapter.MyViewHolder holder, int position) {
        holder.chip.setChipText(evolutions.get(position).getName());


        holder.setItemClickListener(new iClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return evolutions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Chip chip;
        iClickListener itemClickListener;

        public void setItemClickListener(iClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public MyViewHolder(final View itemView) {
            super(itemView);
            chip = (Chip)itemView.findViewById(R.id.chip);
            chip.setOnChipClickListener(new OnChipClickListener() {
                @Override
                public void onChipClick(View v) {
                    itemClickListener.onClick(v,getAdapterPosition());
                }
            }); }}}