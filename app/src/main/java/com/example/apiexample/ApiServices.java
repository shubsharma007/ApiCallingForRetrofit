package com.example.apiexample;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiServices {
    @GET("fact")
    Call<Fact> getFact();





//    @POST
//
//    @PUT
//
//    @PATCH
//
//    @DELETE
//
}
