package com.empower.challenge.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.empower.challenge.R;
import com.empower.challenge.model.CoordinatesResponse;
import com.empower.challenge.model.EmpowerRepository;
import com.empower.challenge.presenter.EmpowerAdapter;
import com.empower.challenge.presenter.EmpowerPresenter;
import com.empower.challenge.presenter.EmpowerPresenterContract;
import com.empower.challenge.util.CacheUtil;
import com.empower.challenge.util.DistanceCalculation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmpowerActivity extends AppCompatActivity
            implements EmpowerViewContract, View.OnClickListener {

    private static final String TAG = EmpowerActivity.class.getSimpleName();
    private EmpowerAdapter empowerAdapter;
    private LinearLayoutManager layoutManager;
    List<CoordinatesResponse> filteredCoordinateList = new ArrayList<>();

    @BindView(R.id.lat)
    EditText latitude;

    @BindView(R.id.lon)
    EditText longitude;

    @BindView(R.id.miles)
    EditText distanceInMiles;

    @BindView(R.id.search)
    Button search;

    @BindView(R.id.coordinateRV)
    RecyclerView coordinateRV;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private String latStr;
    private String lonStr;
    private String miles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empower);
        ButterKnife.bind(this);

        EmpowerRepository empowerRepository = new EmpowerRepository();
        final EmpowerPresenterContract empowerPresenterContract = new EmpowerPresenter(this, empowerRepository);
        empowerPresenterContract.getAllCoordinates();

        search.setOnClickListener(this);

        layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        coordinateRV.setLayoutManager(layoutManager);
        empowerAdapter = new EmpowerAdapter();
        coordinateRV.setAdapter(empowerAdapter);
        coordinateRV.setVisibility(View.GONE);
    }

    @Override
    public void fetchCoordinates(@NonNull List<CoordinatesResponse> coordinatesResponseList) {
        progressBar.setVisibility(View.GONE);
        empowerAdapter.updateResults(coordinatesResponseList);
        CacheUtil.writeJson(coordinatesResponseList, this);
    }

    @Override
    public void displayError() {
        Toast.makeText(this, this.getResources().getString(R.string.search_error_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        latStr = latitude.getText().toString();
        lonStr = longitude.getText().toString();
        miles = distanceInMiles.getText().toString();
        Log.i(TAG, "Lat is: " + latStr + " , lon is: " + lonStr + " and miles is: " + miles);
        hideKeyboard(this);

        filteredCoordinateList = DistanceCalculation.fetchCoordinatesByDistance(this, latStr, lonStr, miles);
        if (filteredCoordinateList.size() < 1) {
            Toast.makeText(this, this.getResources().getString(R.string.search_error_message), Toast.LENGTH_SHORT).show();
        }
        empowerAdapter.updateResults(filteredCoordinateList);
        coordinateRV.setVisibility(View.VISIBLE);
        coordinateRV.setAdapter(empowerAdapter);
    }

    private void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

}
