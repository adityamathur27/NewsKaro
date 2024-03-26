package com.example.newskaro

import com.example.newskaro.Data.data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET("top-headlines")
    fun getData(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ):Call<data>

    @GET("everything")
    fun getAllData(
        @Query("q") q : String,
        @Query("apiKey") apiKey: String
    ):Call<data>
}