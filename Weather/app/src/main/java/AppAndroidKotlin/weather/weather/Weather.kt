package AppAndroidKotlin.weather.weather

data class Weather(val description: String,
                   val temperature: Float,
                   val humidity: Float,
                   val pressure: Int,
                   val iconUrl: String)
