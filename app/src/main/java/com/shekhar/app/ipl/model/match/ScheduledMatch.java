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
    private String matchName;

    private String teams;
    private String team1;
    private String team2;
    private String team1Img;
    private String team2Img;
    private String abvteam1;
    private String abvteam2;


    public ScheduledMatch(Parcel in) {
        date = in.readString();
        time = in.readString();
        match = in.readString();
        vanue = in.readString();
        status = in.readString();
        winner = in.readString();
        match_id = in.readString();
        matchName = in.readString();
        teams = in.readString();
        team1 = in.readString();
        team2 = in.readString();
        team1Img = in.readString();
        team2Img = in.readString();
        abvteam1 = in.readString();
        abvteam2 = in.readString();
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
        dest.writeString(matchName);
        dest.writeString(teams);
        dest.writeString(team1);
        dest.writeString(team2);
        dest.writeString(team1Img);
        dest.writeString(team2Img);
        dest.writeString(abvteam1);
        dest.writeString(abvteam2);
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

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getTeams() {
        return teams;
    }

    public void setTeams(String teams) {
        this.teams = teams;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam1Img() {
        return team1Img;
    }

    public void setTeam1Img(String team1Img) {
        this.team1Img = team1Img;
    }

    public String getTeam2Img() {
        return team2Img;
    }

    public void setTeam2Img(String team2Img) {
        this.team2Img = team2Img;
    }

    public String getAbvteam1() {
        return abvteam1;
    }

    public void setAbvteam1(String abvteam1) {
        this.abvteam1 = abvteam1;
    }

    public String getAbvteam2() {
        return abvteam2;
    }

    public void setAbvteam2(String abvteam2) {
        this.abvteam2 = abvteam2;
    }

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
