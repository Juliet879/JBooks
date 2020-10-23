package com.moringaschool.jbooks.network;

import com.moringaschool.jbooks.models.GoogleBooksSearchResponse;
import com.moringaschool.jbooks.models.GoogleBooksSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleApi {
    @GET("volumes")
    Call<GoogleBooksSearchResponse> getBooks(
            @Query("q")String q,
            @Query("key") String key

    );

}
