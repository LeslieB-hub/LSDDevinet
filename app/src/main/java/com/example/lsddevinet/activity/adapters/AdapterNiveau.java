package com.example.lsddevinet.activity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsddevinet.R;
import com.example.lsddevinet.model.Categorie;

import java.util.List;

public class AdapterNiveau extends RecyclerView.Adapter<AdapterNiveau.ViewHolder> {

    private List<Categorie> categories;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvNiveau;
        public ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNiveau = itemView.findViewById(R.id.tv_niveau);
            progressBar = itemView.findViewById(R.id.pb_progression);
        }
    }

    public AdapterNiveau(List<Categorie> myDataset){
        categories = myDataset;
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
        holder.tvNiveau.setText("Niveau "+ categories.get(position).getId()+" - "+ categories.get(position).getCategorie());
        holder.progressBar.setProgress(50);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
