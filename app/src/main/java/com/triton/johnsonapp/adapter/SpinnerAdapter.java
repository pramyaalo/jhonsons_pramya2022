package com.triton.johnsonapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;
import com.triton.johnsonapp.R;
import com.triton.johnsonapp.activity.SpinnerModel;

import java.util.ArrayList;

public class SpinnerAdapter extends RecyclerView.Adapter<SpinnerAdapter.ViewHolder> {
    private ArrayList<SpinnerModel> SpinnerModelArraylist;
    private Context context;

    public SpinnerAdapter(ArrayList<SpinnerModel> SpinnerModelArraylist, Context context) {
        this.SpinnerModelArraylist = SpinnerModelArraylist;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_rv_item, parent, false);
        return new ViewHolder(view);
     }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpinnerModel modal = SpinnerModelArraylist.get(position);
        holder.Storebookname.setText(modal.getStoreroomname());

    }

    @Override
    public int getItemCount() {
        return SpinnerModelArraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Storebookname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Storebookname = itemView.findViewById(R.id.Storebookname);
        }
    }
}
