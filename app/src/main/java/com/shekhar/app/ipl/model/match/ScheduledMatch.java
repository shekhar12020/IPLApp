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

    private String status = "";
    private String winner = "";

    private String match_id;

    public ScheduledMatch(Parcel in) {
        date = in.readString();
        time = in.readString();
        match = in.readString();
        vanue = in.readString();
        status = in.readString();
        winner = in.readString();
        match_id = in.readString();
    }

    public ScheduledMatch() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(match);
        dest.writeString(vanue);
        dest.writeString(status);
        dest.writeString(winner);
        dest.writeString(match_id);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

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
