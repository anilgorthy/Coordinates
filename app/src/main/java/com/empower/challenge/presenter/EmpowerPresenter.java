package com.empower.challenge.presenter;

import android.util.Log;

import com.empower.challenge.model.CoordinatesResponse;
import com.empower.challenge.model.EmpowerRepository;
import com.empower.challenge.view.EmpowerViewContract;

import java.util.List;

import retrofit2.Response;

public class EmpowerPresenter implements EmpowerPresenterContract, EmpowerRepository.EmpowerRepositoryCallback {

    private static final String TAG = EmpowerPresenter.class.getSimpleName();
    private final EmpowerViewContract empowerViewContract;
    private final EmpowerRepository empowerRepository;

    /**
     * Constructor
     */
    public EmpowerPresenter(EmpowerViewContract empowerViewContract, EmpowerRepository empowerRepository) {
        this.empowerViewContract = empowerViewContract;
        this.empowerRepository = empowerRepository;
    }

    @Override
    public void getAllCoordinates() {
        empowerRepository.getCoordinates(this);
    }

    @Override
    public void handleResponse(Response<List<CoordinatesResponse>> response) {
        if (response.isSuccessful()) {
            List<CoordinatesResponse> coordinatesResponseList = response.body();
            if (coordinatesResponseList != null) {
                Log.i(TAG, "Results count is: " + coordinatesResponseList.size());
                empowerViewContract.displayFilteredCoordinates(coordinatesResponseList);
            } else {
                empowerViewContract.displayError();
            }
        }
    }

    @Override
    public void handleError() {
        empowerViewContract.displayError();
    }
}
