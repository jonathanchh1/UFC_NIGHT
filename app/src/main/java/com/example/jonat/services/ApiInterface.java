package com.example.jonat.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jonat on 10/8/2017.
 */

public interface ApiInterface {
    @GET("v1/us/{sort_by}")
    Call<List<UFCContent>> getEvents(@Path("sort_by") String mSortBy);

    @GET("movie/{id}")
    Call<List<UFCContent>> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);


}