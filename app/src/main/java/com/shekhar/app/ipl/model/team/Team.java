package com.shekhar.app.ipl.model.team;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shekhar on 17/03/17.
 */

public class Team implements Parcelable {

    private String team_id;
    private String team_name;
    private String short_name;
    private String image_url;

    private String owner;
    private String captain;
    private String coach;
    private String home_ground;


    public Team(Parcel in) {
        team_id = in.readString();
        team_name = in.readString();
        short_name = in.readString();
        image_url = in.readString();
        owner = in.readString();
        captain = in.readString();
        coach = in.readString();
        home_ground = in.readString();
    }

    public Team() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(team_id);
        dest.writeString(team_name);
        dest.writeString(short_name);
        dest.writeString(image_url);
        dest.writeString(owner);
        dest.writeString(captain);
        dest.writeString(coach);
        dest.writeString(home_ground);
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

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

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

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getHome_ground() {
        return home_ground;
    }

    public void setHome_ground(String home_ground) {
        this.home_ground = home_ground;
    }
}
