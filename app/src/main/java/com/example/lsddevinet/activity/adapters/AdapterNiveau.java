package com.example.lsddevinet.activity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsddevinet.R;

public class AdapterNiveau extends RecyclerView.Adapter<AdapterNiveau.ViewHolder> {

    private String[] mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvNiveau;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNiveau = itemView.findViewById(R.id.tv_niveau);
        }
    }

    public AdapterNiveau(String[] myDataset){
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public AdapterNiveau.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater. from (parent.getContext()).inflate(R.layout.ligne_cardview_niveau , parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //modifit le contenu de la vue en remplissant une ligne de la vu
    @Override
    public void onBindViewHolder(@NonNull AdapterNiveau.ViewHolder holder, int position) {
        holder.tvNiveau.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
