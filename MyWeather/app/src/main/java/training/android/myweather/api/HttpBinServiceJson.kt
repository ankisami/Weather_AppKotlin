package training.android.myweather.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


private const val API_KEY = "ac52f05c4e10b0ea93ac78cb3ecf2bae"


interface HttpBinServiceJson {

    //@GET("/data/2.5/weather?units=metric&q=Maisons-alfort&appid=ac52f05c4e10b0ea93ac78cb3ecf2bae")
    @GET("/data/2.5/weather?units=metric")
    //fun getUserInfo() : Call<GetData>
    fun getDataWeatherInfo(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = API_KEY
    ) : Call<CityWeatherData>
}