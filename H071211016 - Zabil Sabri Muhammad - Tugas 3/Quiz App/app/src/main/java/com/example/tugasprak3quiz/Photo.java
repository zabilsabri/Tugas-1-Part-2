package com.example.tugasprak3quiz;

import android.net.Uri;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class Photo implements Parcelable {
    private Uri FotoUri;
    public Photo() {}

    public Uri getFotoUri() {
        return FotoUri;
    }

    public void setFotoUri(Uri fotoUri) {
        this.FotoUri = fotoUri;
    }

    protected Photo(android.os.Parcel in) {
        FotoUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(android.os.Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull android.os.Parcel parcel, int i) {
        parcel.writeParcelable(FotoUri, i);
    }
}
