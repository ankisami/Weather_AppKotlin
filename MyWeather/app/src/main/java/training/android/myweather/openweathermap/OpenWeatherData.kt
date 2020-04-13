package training.android.myweather.openweathermap

import com.google.gson.annotations.SerializedName

data class WeatherWrapper(val weather: Array<WeatherData>,
                          val main: MainData,
                          val wind: WindData)


data class WeatherData (val description: String,
                        val icon: String)

data class MainData (@SerializedName("temp") val temparature: Float,
                     val pressure: Int,
                     val humidity: Int)

data class WindData(val speed: Float)