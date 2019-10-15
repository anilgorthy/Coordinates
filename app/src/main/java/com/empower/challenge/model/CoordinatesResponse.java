package com.empower.challenge.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CoordinatesResponse {

    @SerializedName("lon")
    @Expose
    private String longitude;

    @SerializedName("lat")
    @Expose
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

//    @Nullable
//    @Expose
//    private List<CoordinatesResponse> coordinatesResponseList = null;
//
//    @Nullable
//    public List<CoordinatesResponse> getCoordinatesList() {
//        return coordinatesResponseList;
//    }

}
