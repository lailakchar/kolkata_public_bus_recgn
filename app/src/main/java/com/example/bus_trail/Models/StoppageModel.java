package com.example.bus_trail.Models;

import java.util.Comparator;

public class StoppageModel {

    String StoppageName,id, LastSpotted;
    Boolean isBusPresent;
    int index;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public StoppageModel(String stoppageName, String id, String lastSpotted, Boolean isBusPresent, int index) {
        StoppageName = stoppageName;
        this.id = id;
        LastSpotted = lastSpotted;
        this.isBusPresent = isBusPresent;
        this.index = index;
    }

    public StoppageModel() {
    }

    public String getStoppageName() {
        return StoppageName;
    }

    public void setStoppageName(String stoppageName) {
        StoppageName = stoppageName;
    }

    public String getLastSpotted() {
        return LastSpotted;
    }

    public void setLastSpotted(String lastSpotted) {
        LastSpotted = lastSpotted;
    }

    public Boolean getisBusPresent() {
        return isBusPresent;
    }

    public void setisBusPresent(Boolean busPresent) {
        isBusPresent = busPresent;
    }

    public static Comparator<StoppageModel> getAscendingComparator() {
        return AscendingComparator;
    }

    public static void setAscendingComparator(Comparator<StoppageModel> ascendingComparator) {
        AscendingComparator = ascendingComparator;
    }

    public static Comparator<StoppageModel> getDescendingComparator() {
        return DescendingComparator;
    }

    public static void setDescendingComparator(Comparator<StoppageModel> descendingComparator) {
        DescendingComparator = descendingComparator;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static Comparator<StoppageModel> AscendingComparator = new Comparator<StoppageModel>() {

        public int compare(StoppageModel s1, StoppageModel s2)
        {
            return s1.getIndex() - s2.getIndex();
        }
    };

    public static Comparator<StoppageModel> DescendingComparator = new Comparator<StoppageModel>() {

        public int compare(StoppageModel s1, StoppageModel s2)
        {
            return s2.getIndex() - s1.getIndex();
        }
    };



}
