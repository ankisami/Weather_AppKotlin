package training.android.myweather.api

import retrofit2.Call
import retrofit2.http.GET

interface HttpBinServiceJson {

    @GET("get")
    fun getUserInfo() : Call<GetData>
    //fun getDataWeatherInfo() : Call<CityWeatherData>
}