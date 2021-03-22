package com.example.test

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Builder {
    lateinit var api : Service
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkhttpClient())
            .build()

        api =retrofit.create(Service::class.java)
    }

   private fun createOkhttpClient(): OkHttpClient{
       val builder = OkHttpClient.Builder()
       val interceptor = HttpLoggingInterceptor()
       interceptor.level = HttpLoggingInterceptor.Level.BODY
       builder.addInterceptor(interceptor)
       return builder.build()
   }

}