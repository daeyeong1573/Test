package com.example.test

import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("users/daeyeong1573")
    fun getUserInfo(

    ): Call<UserInfo>

}