package com.empower.challenge.util;

import android.content.Context;
import android.util.Log;

import com.empower.challenge.model.CoordinatesResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Source for calculating distances
 * https://www.geeksforgeeks.org/haversine-formula-to-find-distance-between-two-points-on-a-sphere/
 */
public class DistanceCalculation {

    private static final String TAG = DistanceCalculation.class.getSimpleName();

    private static double getDistance(double lat1, double lon1, double lat2, double lon2) {

        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));

        return rad * c * 0.621;
    }

    public static List<CoordinatesResponse> fetchCoordinatesByDistance(Context context, String lat, String lon, String miles) {
        List<CoordinatesResponse> coordinateList = CacheUtil.readJson(context);
        List<CoordinatesResponse> newCoordinateList = new ArrayList<>();
        Log.i(TAG, "List size is: " + coordinateList.size()
                + "Lat is: " + Double.valueOf(lat)
                + " and lon is: " + Double.valueOf(lon));
        for (CoordinatesResponse response : coordinateList) {
            double distanceInMiles = getDistance(Double.valueOf(lat), Double.valueOf(lon),
                    Double.valueOf(response.getLatitude()),
                    Double.valueOf(response.getLongitude()));
            Log.i(TAG, "distanceInMiles is: " + distanceInMiles);

            if (distanceInMiles <= Double.valueOf(miles)) {
                newCoordinateList.add(response);
            }
        }
        Log.i(TAG, "New size is: " + newCoordinateList.size());
        return newCoordinateList;
    }

}
