package com.shekhar.app.ipl.model.user;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shekhar on 21/03/17.
 */

class MatchPrediction implements Parcelable {

    private String match_date;
    private String match_id;
    private String selected_team_id;
    private String initiated_point;
    private boolean result;

    protected MatchPrediction(Parcel in) {
        match_date = in.readString();
        match_id = in.readString();
        selected_team_id = in.readString();
        initiated_point = in.readString();
        result = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(match_date);
        dest.writeString(match_id);
        dest.writeString(selected_team_id);
        dest.writeString(initiated_point);
        dest.writeByte((byte) (result ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MatchPrediction> CREATOR = new Creator<MatchPrediction>() {
        @Override
        public MatchPrediction createFromParcel(Parcel in) {
            return new MatchPrediction(in);
        }

        @Override
        public MatchPrediction[] newArray(int size) {
            return new MatchPrediction[size];
        }
    };

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getSelected_team_id() {
        return selected_team_id;
    }

    public void setSelected_team_id(String selected_team_id) {
        this.selected_team_id = selected_team_id;
    }

    public String getInitiated_point() {
        return initiated_point;
    }

    public void setInitiated_point(String initiated_point) {
        this.initiated_point = initiated_point;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
