package com.empower.challenge.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.empower.challenge.R;
import com.empower.challenge.model.CoordinatesResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmpowerAdapter extends RecyclerView.Adapter<EmpowerAdapter.EmpowerViewHolder> {

    private static final String TAG = EmpowerAdapter.class.getSimpleName();
    private List<CoordinatesResponse> coordinatesResponseList = new ArrayList<>();

    @NonNull
    @Override
    public EmpowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
        final View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coordinate_item, parent, false);
        return new EmpowerViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpowerViewHolder holder, int position) {
        holder.bind(coordinatesResponseList.get(position));
    }

    @Override
    public int getItemCount() {
        return coordinatesResponseList.size();
    }


    public void updateResults(@NonNull List<CoordinatesResponse> results) {
        this.coordinatesResponseList = results;
        notifyDataSetChanged();
    }

    static class EmpowerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.latValTV)
        TextView latValTV;

        @BindView(R.id.lonValTV)
        TextView lonValTV;


        EmpowerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(CoordinatesResponse searchResult) {
            latValTV.setText(searchResult.getLatitude());
            lonValTV.setText(searchResult.getLongitude());
        }
    }
}
