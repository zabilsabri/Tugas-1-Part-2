package com.example.loopj;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DataResponse {
    @SerializedName("data")
    private ArrayList<UserResponse> data;

    public ArrayList<UserResponse> getData() {
        return data;
    }

}
