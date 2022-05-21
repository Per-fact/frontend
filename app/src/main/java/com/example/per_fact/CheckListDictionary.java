package com.example.per_fact;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class CheckListDictionary implements Parcelable {

    private String id;
//    private String English;
//    private String Korean;여기

    protected CheckListDictionary(Parcel in) {
        id = in.readString();
    }

    public static final Creator<CheckListDictionary> CREATOR = new Creator<CheckListDictionary>() {
        @Override
        public CheckListDictionary createFromParcel(Parcel in) {
            return new CheckListDictionary(in);
        }

        @Override
        public CheckListDictionary[] newArray(int size) {
            return new CheckListDictionary[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getEnglish() {
//        return English;
//    }
//
//    public void setEnglish(String english) {
//        English = english;
//    }

//    public String getKorean() {
//        return Korean;
//    }여기

//    public void setKorean(String korean) {
//        Korean = korean;
//    }여기

    public CheckListDictionary(String id) {
        this.id = id;
//        English = english;
//        Korean = korean;여기
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);

    }
}
