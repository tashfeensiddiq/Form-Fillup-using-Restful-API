package com.rk.dataentry.api;

import com.rk.dataentry.model.PostModel;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST(HttpParams.API_POST_INFORMATION)
    Call<PostModel> postInformation(
            @Query("name") String name,
            @Query("sales_id") String sales,
            @Query("mobile") String mobile,
            @Query("age") String age,
            @Query("gender") String gender,
            @Query("address") String address,
            @Query("who_are_you_applying_for") String who_are_you_applying_for
    );

}
