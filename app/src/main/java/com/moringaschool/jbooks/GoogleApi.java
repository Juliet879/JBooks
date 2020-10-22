package com.moringaschool.jbooks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleApi {
    @GET("books/")
    Call<GoogleBookSearchResponse> getBooks(
            @Query("book") String book,
            @Query("term") String term
    );


}
