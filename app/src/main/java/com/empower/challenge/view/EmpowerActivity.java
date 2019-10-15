package com.empower.challenge.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.empower.challenge.R;
import com.empower.challenge.model.CoordinatesResponse;
import com.empower.challenge.model.EmpowerRepository;
import com.empower.challenge.presenter.EmpowerAdapter;
import com.empower.challenge.presenter.EmpowerPresenter;
import com.empower.challenge.presenter.EmpowerPresenterContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmpowerActivity extends AppCompatActivity implements EmpowerViewContract {

    private static final String TAG = EmpowerActivity.class.getSimpleName();
    private EmpowerAdapter empowerAdapter;
    private LinearLayoutManager layoutManager;

    @BindView(R.id.lat)
    EditText latitude;

    @BindView(R.id.lon)
    EditText longitude;

    @BindView(R.id.search)
    Button search;

    @BindView(R.id.coordinateRV)
    RecyclerView coordinateRV;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empower);
        ButterKnife.bind(this);

        EmpowerRepository empowerRepository = new EmpowerRepository();
        final EmpowerPresenterContract empowerPresenterContract = new EmpowerPresenter(this, empowerRepository);
        empowerPresenterContract.getAllCoordinates();
        progressBar.setVisibility(View.VISIBLE);

        layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        coordinateRV.setLayoutManager(layoutManager);
        empowerAdapter = new EmpowerAdapter();
        coordinateRV.setAdapter(empowerAdapter);
    }

    @Override
    public void displayFilteredCoordinates(@NonNull List<CoordinatesResponse> coordinatesResponseList) {
        progressBar.setVisibility(View.GONE);
        empowerAdapter.updateResults(coordinatesResponseList);
    }

    @Override
    public void displayError() {

    }

}
