package com.shekhar.app.ipl.model.match;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shekhar on 17/03/17.
 */

public class ScheduledMatch implements Parcelable {

    private String date;
    private String time;
    private String match;
    private String vanue;

    public ScheduledMatch(Parcel in) {
        date = in.readString();
        time = in.readString();
        match = in.readString();
        vanue = in.readString();
    }

    public ScheduledMatch() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(match);
        dest.writeString(vanue);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ScheduledMatch> CREATOR = new Creator<ScheduledMatch>() {
        @Override
        public ScheduledMatch createFromParcel(Parcel in) {
            return new ScheduledMatch(in);
        }

        @Override
        public ScheduledMatch[] newArray(int size) {
            return new ScheduledMatch[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getVanue() {
        return vanue;
    }

    public void setVanue(String vanue) {
        this.vanue = vanue;
    }
}
