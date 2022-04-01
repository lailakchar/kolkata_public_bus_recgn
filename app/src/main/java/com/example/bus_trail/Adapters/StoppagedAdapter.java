package com.example.bus_trail.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bus_trail.Models.StoppageModel;
import com.example.bus_trail.R;

import java.util.ArrayList;

public class StoppagedAdapter extends RecyclerView.Adapter<StoppagedAdapter.StoppageViewHolder> {

    ArrayList<StoppageModel> list;
    String source="",dest="";

    public StoppagedAdapter(ArrayList<StoppageModel> list) {
        this.list = list;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDest(String dest) {
        this.dest = dest;
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

        if (model.getisBusPresent()){
            holder.lastSeen.setVisibility(View.VISIBLE);
            holder.status.setBackgroundResource(R.drawable.circle_green);
            holder.lastSeen.setText(model.getLastSpotted());
        }
        else {
            holder.lastSeen.setVisibility(View.GONE);
            holder.status.setBackgroundResource(R.drawable.red_circle);
        }

//        if(source.equals(model.getId())) {
//            Log.d("ROUTE",Integer.toString(list.size()));
//            holder.upline.setVisibility(View.GONE);
//        }
//        if(dest.equals(model.getId())) {
//            Log.d("ROUTE",Integer.toString(list.size()));
//            holder.downline.setVisibility(View.GONE);
//        }

        holder.stoppageName.setText(model.getStoppageName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StoppageViewHolder extends RecyclerView.ViewHolder {
        TextView stoppageName,lastSeen;
        View status,upline,downline;
        public StoppageViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.status);
            stoppageName = itemView.findViewById(R.id.stopage_name);
            lastSeen = itemView.findViewById(R.id.last_spotted);
            upline = itemView.findViewById(R.id.lineup);
            downline = itemView.findViewById(R.id.linedown);
        }
    }

}
