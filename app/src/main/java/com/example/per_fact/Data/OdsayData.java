package com.example.per_fact.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

public class OdsayData
{
    @SerializedName("result")
    public Result resultList;

    public static class Result{
        @SerializedName("path")
        public List<String> path;
    }

    public static class Path {
        @SerializedName("pathType")
        public String pathType;

        public String getPathType() {
            return pathType;
        }

        public void setPathType(String pathType) {
            this.pathType = pathType;
        }

        public Info getInfoList() {
            return infoList;
        }

        public void setInfoList(Info infoList) {
            this.infoList = infoList;
        }

        @SerializedName("info")
        public Info infoList;
    }

    private static class Info {
        @SerializedName("totalWalk")
        private int totalWalk;
    }
}
