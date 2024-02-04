package com.example.terramars.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class retrofitInstance {
    object RetrofitInstance {
        private const val BASE_URL = "https://api.nasa.gov/"

        val api: NasaApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NasaApiService::class.java)
        }
    }

}