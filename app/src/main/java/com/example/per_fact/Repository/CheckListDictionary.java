package com.example.per_fact.Repository;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
//체크리스트 담을 객체(직렬화Pacelable)
public class CheckListDictionary implements Parcelable {

    private String id;
    boolean isSelected;

    public CheckListDictionary(String id, boolean isSelected) {
        this.id = id;
        this.isSelected = isSelected;
    }

    protected CheckListDictionary(Parcel in) {
        id = in.readString();
        isSelected = in.readByte() != 0;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
    }
}
