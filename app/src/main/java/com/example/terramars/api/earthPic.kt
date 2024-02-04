package com.example.terramars.api
import com.google.gson.annotations.SerializedName
class earthPic {
    data class EarthPic(
        @SerializedName("url") val imageUrl: String
    )

}