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

public class ResultatsAdapter extends RecyclerView.Adapter<ResultatsAdapter.ViewHolder> {

    private List<Categorie> categories;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNiveau;
        public ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNiveau = itemView.findViewById(R.id.tv_niveau);
            progressBar = itemView.findViewById(R.id.pb_progression);
        }
    }

    public ResultatsAdapter(List<Categorie> mCategories) {
        categories=mCategories;
    }

    @NonNull
    @Override
    public ResultatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ligne_recycleview_resultats , parent, false);
        ResultatsAdapter.ViewHolder vh = new ViewHolder(view);
        return vh;

    }



    @Override
    public void onBindViewHolder(@NonNull ResultatsAdapter.ViewHolder holder, int position) {
        holder.tvNiveau.setText("Niveau "+ (categories.get(position).getId())+" - "+ categories.get(position).getCategorie());
        //   holder.progressBar.setProgress(30);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }




}
