package com.example.bus_trail.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bus_trail.Activities.BottomSheetFragment;
import com.example.bus_trail.Helper_Classes.BusDetails;
import com.example.bus_trail.Models.BusModel;
import com.example.bus_trail.R;

import java.util.ArrayList;

public class SearchedBusAdapter extends RecyclerView.Adapter<SearchedBusAdapter.SearchedBusViewHolder>{


    ArrayList<BusModel> list;
    Context context;
    FragmentManager manager;

    public SearchedBusAdapter(ArrayList<BusModel> list, Context context, FragmentManager manager) {
        this.list = list;
        this.context = context;
        this.manager = manager;
    }

    @NonNull
    @Override
    public SearchedBusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_searched_bus,parent,false);
        return new SearchedBusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedBusViewHolder holder, int position) {

        BusModel model = list.get(position);

        holder.BusDest.setText("To "+BusDetails.giveLatLangName(model.getDestination()));
        holder.BusName.setText(model.getName());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(context,model);
                bottomSheetFragment.show(manager,"TAG");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchedBusViewHolder extends RecyclerView.ViewHolder {
        TextView BusName,BusDest;
        CardView layout;
        public SearchedBusViewHolder(@NonNull View itemView) {
            super(itemView);
            BusName = itemView.findViewById(R.id.busName);
            BusDest = itemView.findViewById(R.id.busDest);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
