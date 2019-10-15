package com.empower.challenge.view;

import android.support.annotation.NonNull;

import com.empower.challenge.model.CoordinatesResponse;

import java.util.List;

public interface EmpowerViewContract {

    void fetchCoordinates(@NonNull List<CoordinatesResponse> coordinatesResponseList);

    void displayError();
}
