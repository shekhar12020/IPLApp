package com.shekhar.app.ipl.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by shekhar on 17/03/17.
 */

public class Team implements Parcelable {

    private String image_url;
    private String owner;
    private String team_name;

    private ArrayList<String> team_members;

    public Team(Parcel in) {
        image_url = in.readString();
        owner = in.readString();
        team_name = in.readString();
        team_members = in.createStringArrayList();
    }

    public Team() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image_url);
        dest.writeString(owner);
        dest.writeString(team_name);
        dest.writeStringList(team_members);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public ArrayList<String> getTeam_members() {
        return team_members;
    }

    public void setTeam_members(ArrayList<String> team_members) {
        this.team_members = team_members;
    }
}
