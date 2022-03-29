package com.example.bus_trail.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bus_trail.Adapters.StoppageAdapter;
import com.example.bus_trail.Helper_Classes.BusDetails;
import com.example.bus_trail.Models.StoppageModel;
import com.example.bus_trail.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ShowBusLocation extends AppCompatActivity {

    Button back;
    TextView StoppageName, Destination;
    RecyclerView recyclerView;
    StoppageAdapter adapter;
    ArrayList<StoppageModel> stoppageList;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bus_location);

        back = findViewById(R.id.btn_back);
        StoppageName = findViewById(R.id.bus_no);
        Destination = findViewById(R.id.bus_destination);
        recyclerView = findViewById(R.id.recyclerView);
        stoppageList = new ArrayList<>();
        adapter = new StoppageAdapter(stoppageList);
        reference = FirebaseDatabase.getInstance().getReference();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        String BusName = "AC12",
                BusDest="22:5692N&88:5091E";
//                BusDest="22:5839N&88:3434E";


        getBusLocations(BusName,BusDest);

        StoppageName.setText(BusName);
        Destination.setText("Towards "+BusDest);

    }

//    private void tryIt(){
//
//        stoppageList.add(new StoppageModel("Mandirtala","5 Mins",false,1));
//        stoppageList.add(new StoppageModel("Howrah","2 Mins",false,0));
//        stoppageList.add(new StoppageModel("Tramdipo","1 Mins",false,5));
//        stoppageList.add(new StoppageModel("Beltala","4 Minutes",true,2));
//        stoppageList.add(new StoppageModel("Exide","2 Mins",false,4));
//        stoppageList.add(new StoppageModel("Newtown","10 Mins",false,3));
//
//
//        for(int i=0;i<stoppageList.size();i++){
//            Log.d("ROUTE",stoppageList.get(i).getStoppageName());
//        }
//
//        Log.d("ROUTE","\n\nSorting...\n\n");
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Collections.sort(stoppageList,StoppageModel.DescendingComparator);
//        }
//
//        for(int i=0;i<stoppageList.size();i++){
//            Log.d("ROUTE",stoppageList.get(i).getStoppageName());
//        }
//
//    }

    private void getBusLocations(String busName, String busDest) {
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

                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }
}