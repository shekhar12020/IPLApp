package com.shekhar.app.ipl.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by shekhar on 21/03/17.
 */
public class User implements Parcelable {

    private String name;
    private String email;
    private String photo_url;
    private String user_id;
    private ArrayList<MatchPrediction> predictions;

    private String total_point;

    public User() {
    }

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        photo_url = in.readString();
        user_id = in.readString();
        predictions = in.createTypedArrayList(MatchPrediction.CREATOR);
        total_point = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(photo_url);
        dest.writeString(user_id);
        dest.writeTypedList(predictions);
        dest.writeString(total_point);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getTotal_point() {
        return total_point;
    }

    public void setTotal_point(String total_point) {
        this.total_point = total_point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public ArrayList<MatchPrediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(ArrayList<MatchPrediction> predictions) {
        this.predictions = predictions;
    }
}