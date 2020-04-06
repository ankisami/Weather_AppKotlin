package AppAndroidKotlin.weather.api_openWeatherMap

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "ac52f05c4e10b0ea93ac78cb3ecf2bae"

interface OpenWeatherService {

    @GET("data/2.5/weather?units=metric&lang=fr")
    fun getWeather(@Query("q") cityName: String,
                   @Query("appid") apiKey: String = API_KEY): Call<WeatherWrapper>

}