package com.example.terramars.api
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class callAPI {

    fun getApodData(onResult: (ApodData.ApodResponse?) -> Unit) {
        val apiKey = "hKAEhMktBDWsAx97o0roniKxhv2jMSKInUGjLh7V"
        val call = retrofitInstance.RetrofitInstance.api.getApod(apiKey)

        call.enqueue(object : Callback<ApodData.ApodResponse> {
            override fun onResponse(
                call: Call<ApodData.ApodResponse>,
                response: Response<ApodData.ApodResponse>
            ) {
                if (response.isSuccessful) {
                    val apodResponse: ApodData.ApodResponse? = response.body()
                    onResult(apodResponse)
                } else {
                    onResult(null)
                }
            }
            override fun onFailure(call: Call<ApodData.ApodResponse>, t: Throwable) {
                onResult(null)
            }
        })
    }
    fun getMarsPhotos(earthDate: String, onResult: (List<String>?) -> Unit) {
        val call = retrofitInstance.RetrofitInstance.api.getMarsPhotos(earthDate = earthDate)

        call.enqueue(object : Callback<marsPic.MarsPhotosResponse> {
            override fun onResponse(
                call: Call<marsPic.MarsPhotosResponse>,
                response: Response<marsPic.MarsPhotosResponse>
            ) {
                if (response.isSuccessful) {
                    val marsPhotosResponse: marsPic.MarsPhotosResponse? = response.body()
                    val imageUrls = mutableListOf<String>()

                    marsPhotosResponse?.photos?.forEach { marsPic ->
                        imageUrls.add(marsPic.imgSrc)
                    }

                    onResult(imageUrls)
                } else {
                    onResult(null)
                }
            }


            override fun onFailure(call: Call<marsPic.MarsPhotosResponse>, t: Throwable) {
                Log.e("MainActivity", "Erreur lors de l'appel à l'API : ${t.message}", t)
                onResult(null)
            }
        })
    }


    fun getEarthImage(latitude: Double, longitude: Double, date: String, onResult: (String?) -> Unit) {
        val call = retrofitInstance.RetrofitInstance.api.getEarthImage(latitude, longitude, date = date)

        call.enqueue(object : Callback<earthPic.EarthPic> {
            override fun onResponse(call: Call<earthPic.EarthPic>, response: Response<earthPic.EarthPic>) {
                if (response.isSuccessful) {
                    val earthImage: earthPic.EarthPic? = response.body()
                    onResult(earthImage?.imageUrl)
                } else {
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<earthPic.EarthPic>, t: Throwable) {
                Log.e("MainActivity", "Erreur lors de l'appel à l'API : ${t.message}", t)
                onResult(null)
            }
        })


    }


}