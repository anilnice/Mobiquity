package com.app.mobiquity.apiservice

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitCall {
//    val BaseUrl="http://api.openweathermap.org/data/2.5/weather?lat=0&lon=0&appid=fae7190d7e6433ec3a45285ffcf55c86"
    val BaseUrl="http://api.openweathermap.org/data/2.5/"

    var logging:HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    var client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service =retrofit.create(ApiService::class.java)

}