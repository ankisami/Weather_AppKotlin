package training.android.myweather.api

import retrofit2.Call
import retrofit2.http.GET

interface HttpBinServiceString {

    @GET("user-agent")
    fun getUserAgent() : Call<String>
}