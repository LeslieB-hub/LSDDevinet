package com.example.lsddevinet.activity.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lsddevinet.R;
import com.example.lsddevinet.model.Mot;

import java.util.List;

public class AdapterResultatDetail extends RecyclerView.Adapter<AdapterResultatDetail.ViewHolder> {

    private List<Mot> mots;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public EditText editTextMot;
        public EditText editTextMotPropose;
        public CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            editTextMot = itemView.findViewById(R.id.ed_mot);
            editTextMotPropose = itemView.findViewById(R.id.ed_motpropose);
            checkBox = itemView.findViewById(R.id.cb_bonmot);

        }

    }

    /**
     * Constructeur de l'adapter
     * @param mesMots
     */
    public AdapterResultatDetail(List<Mot> mesMots){
            mots = mesMots;
    }


    /**
     * Création de la view
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public AdapterResultatDetail.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.style_ligne_resultat_detail, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterResultatDetail.ViewHolder holder, int position) {
        Uri imgUri = Uri.parse("android.resource://com.example.lsddevinet/drawable/" + mots.get(position).getMot());
        holder.imageView.setImageURI(imgUri);
        holder.editTextMot.setText(mots.get(position).getMot());
        holder.editTextMotPropose.setText(mots.get(position).getProposition());
        if (mots.get(position).getMot().equals(mots.get(position).getProposition())){
            holder.checkBox.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return mots.size();
    }

}
