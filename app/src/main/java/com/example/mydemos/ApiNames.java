package com.example.mydemos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiNames {

    @GET("fetch.php")
    Call<List<String>> getNames(@Query("query") String Query);
}
