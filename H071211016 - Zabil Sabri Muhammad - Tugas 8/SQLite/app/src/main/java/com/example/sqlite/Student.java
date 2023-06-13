package com.example.sqlite;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Student implements Parcelable {
    private int id;
    private String name, nim, prodi;
    public Student() {
    }
    public Student(int id, String name, String nim, String prodi) {
        this.id = id;
        this.name = name;
        this.nim = nim;
        this.prodi = prodi;
    }
    protected Student(Parcel in) {
        id = in.readInt();
        name = in.readString();
        nim = in.readString();
        prodi = in.readString();
    }
    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }
        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNim() {
        return nim;
    }
    public void setNim(String nim) {
        this.nim = nim;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(nim);
        parcel.writeString(prodi);
    }
}
