package com.example.bus_trail.Models;

import com.google.android.gms.maps.model.LatLng;

public class BusModel {

    String Name;
    LatLng source,destination;

    public BusModel(String name, LatLng source, LatLng destination) {
        Name = name;
        this.source = source;
        this.destination = destination;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public LatLng getSource() {
        return source;
    }

    public void setSource(LatLng source) {
        this.source = source;
    }

    public LatLng getDestination() {
        return destination;
    }

    public void setDestination(LatLng destination) {
        this.destination = destination;
    }
}
