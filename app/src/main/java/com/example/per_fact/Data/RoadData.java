package com.example.per_fact.Data;

public class RoadData {

    public int busCount;
    public int subwayCount;
    public int subwayBusCount;
    public int totalTime;
    public int pathType;
    public String firstStartStation;
    public String lastEndStation;

    public RoadData(int busCount, int subwayCount, int subwayBusCount, int totalTime, int pathType, String firstStartStation, String lastEndStation) {
        this.busCount = busCount;
        this.subwayCount = subwayCount;
        this.subwayBusCount = subwayBusCount;
        this.totalTime = totalTime;
        this.pathType = pathType;
        this.firstStartStation = firstStartStation;
        this.lastEndStation = lastEndStation;
    }
}
