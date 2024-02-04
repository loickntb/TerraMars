package com.example.terramars.api
import com.google.gson.annotations.SerializedName
class picoftheDay {
    data class PicOfTheDay(
        @SerializedName("hdurl") val imageUrl: String
    )

}