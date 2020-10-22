package com.moringaschool.jbooks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleApi {
    @GET("volumes")
    Call<GoogleBookSearchResponse> getBooks(
            @Query("q")String q,
            @Query("key") String key

    );

}
