package training.android.myweather.api

import com.google.gson.annotations.SerializedName

data class CityWeatherData (
    val weather: Array<WeatherDataApi>,
    val main: MainDataApi,
    val sys: SysDataApi,
    val name: String
)


data class WeatherDataApi (
    val description: String,
    val icon: String
)

data class MainDataApi(
    @SerializedName("temp") val temperature: Float,
    val pressure: Int,
    val humidity: Int
)

data class SysDataApi (
    val country: String
)


