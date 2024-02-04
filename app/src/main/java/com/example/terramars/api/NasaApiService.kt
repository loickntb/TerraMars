package com.example.terramars.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NasaApiService {


    @GET("planetary/apod")
    fun getApod(
        @Query("api_key") apiKey: String = "hKAEhMktBDWsAx97o0roniKxhv2jMSKInUGjLh7V",
    ): Call<ApodData.ApodResponse>

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getMarsPhotos(
        @Query("earth_date") earthDate: String,
        @Query("sol") sol: Int? = null,
        @Query("api_key") apiKey: String = "hKAEhMktBDWsAx97o0roniKxhv2jMSKInUGjLh7V"
    ): Call<marsPic.MarsPhotosResponse>


    @GET("planetary/earth/imagery")
    fun getEarthImage(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("dim") dim: Double = 0.15,
        @Query("date") date: String,
        @Query("api_key") apiKey: String = "hKAEhMktBDWsAx97o0roniKxhv2jMSKInUGjLh7V"
    ): Call<earthPic.EarthPic>

}
