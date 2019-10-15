package com.empower.challenge.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.empower.challenge.presenter.EmpowerPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmpowerRepository {

    private static final String TAG = EmpowerRepository.class.getSimpleName();
    private final EmpowerEndpoint empowerEndpoint;
    private static final String BASE_URL = "https://yazam-api.azurewebsites.net/api/";

    /**
     * Constructor that also initializes the networking library
     */
    public EmpowerRepository() {
        Retrofit empowerRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        empowerEndpoint = empowerRetrofit.create(EmpowerEndpoint.class);
    }

    /**
     * Called by the Presenter with the search query and
     * callback to get a response from the API endpoint
     * and pass it back to the {@link EmpowerPresenter}
     *
     * @param callback
     */
    public void getCoordinates(@NonNull final EmpowerRepositoryCallback callback) {
        Call<List<CoordinatesResponse>> call = empowerEndpoint.fetchCoordinates();
        call.enqueue(new Callback<List<CoordinatesResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<CoordinatesResponse>> call, @NonNull Response<List<CoordinatesResponse>> response) {
                callback.handleResponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<List<CoordinatesResponse>> call, @NonNull Throwable t) {
                Log.e(TAG, "Error fetching response: " + t.getMessage());
                callback.handleError();
            }
        });
    }

    public interface EmpowerRepositoryCallback {
        void handleResponse(Response<List<CoordinatesResponse>> response);

        void handleError();
    }

}
