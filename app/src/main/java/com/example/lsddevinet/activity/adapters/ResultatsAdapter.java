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

    public ResultatsAdapter(List<Categorie> categories) {
        categories=categories;
    }

    @NonNull
    @Override
    public ResultatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater. from (parent.getContext()).inflate(R.layout.ligne_recycleview_resultats , parent, false);
        ResultatsAdapter.ViewHolder vh = new ViewHolder(view);
        return vh;

    }



    @Override
    public void onBindViewHolder(@NonNull ResultatsAdapter.ViewHolder holder, int position) {
        holder.tvNiveau.setText("Niveau "+ (categories.get(position).getId()-3)+" - "+ categories.get(position).getCategorie());
        //   holder.progressBar.setProgress(30);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNiveau;
        public ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNiveau = itemView.findViewById(R.id.tv_niveau);
            progressBar = itemView.findViewById(R.id.pb_progression);
        }
    }


}

//public static class MyViewHolder extends RecyclerView.ViewHolder {
//    // each data item is just a string in this case
//    public TextView textView;
//    public MyViewHolder(TextView v) {
//        super(v);
//        textView = v;
//    }
//}
//
//    // Provide a suitable constructor (depends on the kind of dataset)
//    public MyAdapter(String[] myDataset) {
//        mDataset = myDataset;
//    }
//
//    // Create new views (invoked by the layout manager)
//    @Override
//    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
//                                                     int viewType) {
//        // create a new view
//        TextView v = (TextView) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.my_text_view, parent, false);
//        ...
//        MyViewHolder vh = new MyViewHolder(v);
//        return vh;
//    }
//
//    // Replace the contents of a view (invoked by the layout manager)
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//        // - get element from your dataset at this position
//        // - replace the contents of the view with that element
//        holder.textView.setText(mDataset[position]);
//
//    }
//

//}