package com.shekhar.app.ipl.model.team;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shekhar on 29/03/17.
 */

public class Squad implements Parcelable {

    private String pName;
    private String pType;
    private String pImg;

    private String country;
    private String bats;
    private String bowls;

    public Squad(Parcel in) {
        pName = in.readString();
        pType = in.readString();
        pImg = in.readString();
        country = in.readString();
        bats = in.readString();
        bowls = in.readString();
    }

    public Squad() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pName);
        dest.writeString(pType);
        dest.writeString(pImg);
        dest.writeString(country);
        dest.writeString(bats);
        dest.writeString(bowls);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Squad> CREATOR = new Creator<Squad>() {
        @Override
        public Squad createFromParcel(Parcel in) {
            return new Squad(in);
        }

        @Override
        public Squad[] newArray(int size) {
            return new Squad[size];
        }
    };

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getpImg() {
        return pImg;
    }

    public void setpImg(String pImg) {
        this.pImg = pImg;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBats() {
        return bats;
    }

    public void setBats(String bats) {
        this.bats = bats;
    }

    public String getBowls() {
        return bowls;
    }

    public void setBowls(String bowls) {
        this.bowls = bowls;
    }
}
