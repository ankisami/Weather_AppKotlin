package training.android.myweather.api

import com.google.gson.annotations.SerializedName

data class GetData(
    @SerializedName("origin") val ip: String,
    val url: String
) {
}