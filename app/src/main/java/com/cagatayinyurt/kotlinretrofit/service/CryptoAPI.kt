package com.cagatayinyurt.kotlinretrofit.service

import com.cagatayinyurt.kotlinretrofit.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    // GET, POST, UPDATE, DELETE
    // https://api.nomics.com/v1/
    // prices?key=2cf802e0d549fd6abfb795a3c2f72284453d4af0

    @GET("prices?key=2cf802e0d549fd6abfb795a3c2f72284453d4af0")
    fun getData(): Call<List<CryptoModel>>
}