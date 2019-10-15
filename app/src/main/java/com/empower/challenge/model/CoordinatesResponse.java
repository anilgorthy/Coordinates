package com.empower.challenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

}
