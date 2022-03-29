package com.example.bus_trail.Adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bus_trail.Models.StoppageModel;
import com.example.bus_trail.R;

import java.util.ArrayList;

public class StoppageAdapter extends RecyclerView.Adapter<StoppageAdapter.StoppageViewHolder> {

    ArrayList<StoppageModel> list;

    public StoppageAdapter(ArrayList<StoppageModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public StoppageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_stoppage,parent,false);
        return new StoppageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoppageViewHolder holder, int position) {

        StoppageModel model = list.get(position);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(holder.constraintLayout);

        if (model.getisBusPresent()){
            holder.icon.setVisibility(View.VISIBLE);
            holder.lastSeen.setVisibility(View.VISIBLE);
            holder.status.setBackgroundResource(R.drawable.circle_green);
            holder.lastSeen.setText("Last Spotted "+model.getLastSpotted()+" ago.");
        }
        else {
            holder.icon.setVisibility(View.GONE);
            holder.lastSeen.setVisibility(View.GONE);
            holder.status.setBackgroundResource(R.drawable.red_circle);
        }

        holder.stoppageName.setText(model.getStoppageName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StoppageViewHolder extends RecyclerView.ViewHolder {
        TextView stoppageName,lastSeen;
        View status,icon,line;
        ConstraintLayout constraintLayout;
        public StoppageViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.status);
            stoppageName = itemView.findViewById(R.id.stopage_name);
            lastSeen = itemView.findViewById(R.id.last_spotted);
            icon = itemView.findViewById(R.id.bus_icon);
            line = itemView.findViewById(R.id.view3);
            constraintLayout = itemView.findViewById(R.id.parentLayout);

        }
    }

}
