package com.example.tgsprak5fragment;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(int imageProfile) {
        this.imageProfile = imageProfile;
    }

    String fullName, userName;
    private int imageProfile;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    private  Post post;

    public User(String fullName, String userName, int imageProfile, Post post) {
        this.post = post;
        this.fullName = fullName;
        this.userName = userName;
        this.imageProfile = imageProfile;
    }

    protected User(Parcel in) {
        this.fullName = in.readString();
        this.userName = in.readString();
        this.imageProfile = in.readInt();
        this.post = in.readParcelable(Post.class.getClassLoader());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(fullName);
        parcel.writeString(userName);
        parcel.writeInt(imageProfile);
        parcel.writeParcelable(post, i);
    }
}
