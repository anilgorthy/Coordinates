package com.empower.challenge.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmpowerEndpoint {
    @GET("code-challenge?code=hy8vKkc2sp7bvXfCZphqa6VcoQR6RBe0RyWL077GijfVqEJgXPdr8w==")
    Call<List<CoordinatesResponse>> fetchCoordinates();
}
