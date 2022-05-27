package com.example.per_fact.Data;

public class RoadData {

    public int busCount;
    public int subwayCount;
    public int subwayBusCount;
    public int totalTime;
    public String firstStartStation;
    public String lastEndStation;

    public RoadData(int busCount, int subwayCount, int subwayBusCount, int totalTime, String firstStartStation, String lastEndStation) {
        this.busCount = busCount;
        this.subwayCount = subwayCount;
        this.subwayBusCount = subwayBusCount;
        this.totalTime = totalTime;
        this.firstStartStation = firstStartStation;
        this.lastEndStation = lastEndStation;
    }
}

