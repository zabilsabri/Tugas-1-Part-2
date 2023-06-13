package com.example.tgsprak5fragment;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {

    private Uri image;
    private String capt;

    public Post(Uri image, String capt) {
        this.image = image;
        this.capt = capt;
    }
    public String getCapt() {
        return capt;
    }

    public Uri getImage() {
        return image;
    }

    protected Post(Parcel in) {
        this.capt = in.readString();
        this.image = (Uri) in.readParcelable(Uri.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.capt);
        dest.writeParcelable(this.image, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}

