package training.android.myweather.openweathermap

import training.android.myweather.weather.Weather

fun mapOpenWeatherDataToWeather(weatherWrapper: WeatherWrapper) : Weather {
    val weatherFirst = weatherWrapper.weather.first()

    return Weather(
        description = weatherFirst.description,
        temperature = weatherWrapper.main.temparature,
        humidity = weatherWrapper.main.humidity,
        pressure = weatherWrapper.main.pressure,
        wind = weatherWrapper.wind.speed,
        iconUrl = "https://openweathermap.org/img/w/${weatherFirst.icon}.png"
    )
}