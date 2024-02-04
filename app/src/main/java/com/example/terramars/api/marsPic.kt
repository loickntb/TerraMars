package com.example.terramars.api
import com.google.gson.annotations.SerializedName
class marsPic {


    data class MarsPic(
        @SerializedName("img_src") val imgSrc: String,
    )
    data class MarsPhotosResponse(
        @SerializedName("photos") val photos: List<MarsPic>
    )


}