package AppAndroidKotlin.weather.api_openWeatherMap

import AppAndroidKotlin.weather.weather.Weather

fun mapOpenWeatherDataToWeather(weatherWrapper: WeatherWrapper) : Weather {
    val weatherFirst = weatherWrapper.weather.first()
    return Weather(
        description = weatherFirst.description,
        temperature = weatherWrapper.main.temperature,
        humidity = weatherWrapper.main.temperature,
        pressure = weatherWrapper.main.pressure,
        iconUrl = "https://openweathermap.org/img/wn/${weatherFirst.icon}.png"
    )
}