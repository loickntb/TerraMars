package com.example.terramars.api
import com.example.terramars.api.retrofitInstance
import com.example.terramars.api.marsPic
import com.example.terramars.api.picoftheDay
import com.example.terramars.api.earthPic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class callAPI {

    fun getMarsPhotos(earthDate: String, onResult: (List<String>?) -> Unit) {
        val call = retrofitInstance.RetrofitInstance.api.getMarsPhotos(earthDate = earthDate)

        call.enqueue(object : Callback<List<marsPic.MarsPic>> {
            override fun onResponse(call: Call<List<marsPic.MarsPic>>, response: Response<List<marsPic.MarsPic>>) {
                if (response.isSuccessful) {
                    val marsPhotos: List<marsPic.MarsPic>? = response.body()
                    val imageUrls = mutableListOf<String>()

                    marsPhotos?.forEach {
                        imageUrls.add(it.imgSrc)
                    }

                    onResult(imageUrls)
                } else {
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<List<marsPic.MarsPic>>, t: Throwable) {
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