package com.example.terramars.api
import android.util.Log
import com.example.terramars.api.retrofitInstance
import com.example.terramars.api.marsPic.MarsPic
import com.example.terramars.api.marsPic.MarsPhotosResponse
import com.example.terramars.api.picoftheDay
import com.example.terramars.api.earthPic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class callAPI {

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
                onResult(null)
            }
        })
    }


}