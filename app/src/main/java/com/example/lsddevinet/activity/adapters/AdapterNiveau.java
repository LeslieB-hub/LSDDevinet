package com.example.lsddevinet.activity.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsddevinet.R;
import com.example.lsddevinet.ViewModel.MotViewModel;
import com.example.lsddevinet.activity.SelectionNiveauActivity;
import com.example.lsddevinet.model.Categorie;
import com.example.lsddevinet.model.Mot;

import java.util.List;

public class AdapterNiveau extends RecyclerView.Adapter<AdapterNiveau.ViewHolder> {
    private IOnClickNiveau interfaceClickNiveau;
    private List<Categorie> categories;
    private OnClicSurItem action;
    private List<Integer> progression;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvNiveau;
        public ProgressBar progressBar;
        public Button buttonRaz;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNiveau = itemView.findViewById(R.id.tv_niveau);
            progressBar = itemView.findViewById(R.id.pb_progression);
            buttonRaz = itemView.findViewById(R.id.btn_reinitialiser);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            action.onInteraction(categories.get(ViewHolder.this.getAdapterPosition()));
        }
    }

    public void setInterfaceClickNiveau(IOnClickNiveau interfaceClickNiveau) {
        this.interfaceClickNiveau = interfaceClickNiveau;
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
    public AdapterNiveau(List<Categorie> myDataset, OnClicSurItem activite, List<Integer> myProgression){
        categories = myDataset;
        action = activite;
        progression = myProgression;
    }

    @NonNull
    @Override
    public AdapterNiveau.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ligne_cardview_niveau, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //modifit le contenu de la vue en remplissant une ligne de la vu
    @Override
    public void onBindViewHolder(@NonNull AdapterNiveau.ViewHolder holder, final int position) {
        holder.tvNiveau.setText("Niveau "+ (categories.get(position).getId())+" - "+ categories.get(position).getCategorie());
        holder.progressBar.setProgress(progression.get(position));
        holder.buttonRaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceClickNiveau.onClickReinitialise(categories.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public interface IOnClickNiveau{
       void onClickReinitialise(Categorie categorie);
    }
}
