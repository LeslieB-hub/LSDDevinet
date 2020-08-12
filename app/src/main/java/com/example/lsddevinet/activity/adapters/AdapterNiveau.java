package com.example.lsddevinet.activity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsddevinet.R;
import com.example.lsddevinet.model.Categorie;

import java.util.List;

public class AdapterNiveau extends RecyclerView.Adapter<AdapterNiveau.ViewHolder> {

    private List<Categorie> categories;
    private OnClicSurItem action;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvNiveau;
        public ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNiveau = itemView.findViewById(R.id.tv_niveau);
            progressBar = itemView.findViewById(R.id.pb_progression);
            itemView.setOnClickListener(this);
            progressBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    action.onInteraction(categories.get(ViewHolder.this.getAdapterPosition()));
                }
            });

        }

        @Override
        public void onClick(View view) {
            action.onInteraction(categories.get(ViewHolder.this.getAdapterPosition()));

        }
    }

    /**
     * Interface implémentée par l'activité
     * @param <T>
     */
    public interface OnClicSurItem<T>{
        void onInteraction(T info);
    }

    /**
     * Constructeur qui attend les données
     * @param myDataset
     */
    public AdapterNiveau(List<Categorie> myDataset, OnClicSurItem activite){
        categories = myDataset;
        action = activite;
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
        holder.tvNiveau.setText("Niveau "+ (categories.get(position).getId())+" - "+ categories.get(position).getCategorie());
     //   holder.progressBar.setProgress(30);



    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
