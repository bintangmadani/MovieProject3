package com.example.movieproject3.listproject;

import android.os.Parcel;
import android.os.Parcelable;

public class WatchModel implements Parcelable {

    private String title;

    private String overview;

    private String poster;

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.poster);
    }

    private WatchModel(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.poster = (String) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<WatchModel> CREATOR = new Parcelable.Creator<WatchModel>(){
        @Override
        public WatchModel createFromParcel(Parcel source) {
            return new WatchModel(source);
        }

        @Override
        public WatchModel[] newArray(int size) {
            return new WatchModel[size];
        }
    };
}
