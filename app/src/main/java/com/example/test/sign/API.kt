package com.example.test.sign

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface API {
    @POST("/signin")
        fun login(
        @Body userData : Users

    ): Call<UserData>
}