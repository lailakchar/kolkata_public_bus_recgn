package com.example.bus_trail.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bus_trail.Adapters.StoppagedAdapter;
import com.example.bus_trail.Helper_Classes.BusDetails;
import com.example.bus_trail.Models.BusModel;
import com.example.bus_trail.Models.StoppageModel;
import com.example.bus_trail.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class BottomSheetFragment extends BottomSheetDialogFragment {


    TextView BusName, Destination;
    RecyclerView recyclerView;
    StoppagedAdapter adapter;
    ArrayList<StoppageModel> stoppageList;
    DatabaseReference reference;
    ProgressBar bar;

    Context context;
    BusModel model;

    public BottomSheetFragment(Context context) {
        this.context = context;
    }

    public BottomSheetFragment(Context context, BusModel model) {
        this.context = context;
        this.model = model;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.bottom_sheet_fragment,container,false);

        BusName = view.findViewById(R.id.bus_no);
        Destination = view.findViewById(R.id.busDestination);
        recyclerView = view.findViewById(R.id.recyclerView);
        stoppageList = new ArrayList<>();
        adapter = new StoppagedAdapter(stoppageList);
        reference = FirebaseDatabase.getInstance().getReference();
        bar = view.findViewById(R.id.progressbar);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter.setSource(encryptLatLang(model.getSource()));
        adapter.setDest(encryptLatLang(model.getDestination()));

        getBusLocations(model.getName(),encryptLatLang(model.getDestination()));

        BusName.setText(model.getName());
        Destination.setText(BusDetails.giveLatLangName(model.getDestination()));

        return view;
    }


    private void getBusLocations(String busName, String busDest) {
        bar.setVisibility(View.VISIBLE);
        reference.child("Bus").child("Routes").child(busName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                stoppageList.clear();
                for (DataSnapshot ds:snapshot.getChildren()
                ) {
                    stoppageList.add(ds.getValue(StoppageModel.class));
                }

                Collections.sort(stoppageList,StoppageModel.AscendingComparator);

                if(stoppageList.get(0).getId().equals(busDest)) {
                    Collections.sort(stoppageList, StoppageModel.DescendingComparator);
                }
                bar.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private String encryptLatLang(LatLng latLng) {
        return BusDetails.LatLngToString(latLng).replace(".",":");
    }

    @Override
    public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;
    }
}
