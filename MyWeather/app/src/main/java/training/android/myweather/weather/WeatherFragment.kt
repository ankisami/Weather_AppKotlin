package training.android.myweather.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import training.android.myweather.App
import training.android.myweather.R
import training.android.myweather.openweathermap.WeatherWrapper
import training.android.myweather.openweathermap.mapOpenWeatherDataToWeather
import training.android.myweather.utils.toast
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WeatherFragment: Fragment() {

    companion object {
        val EXTRA_CITY_NAME = "training.kotlin.weather.extras.EXTRA_CITY_NAME"
        fun newInstance() = WeatherFragment()
    }

    private val TAG = WeatherFragment::class.java.simpleName
    private lateinit var cityName: String

    //private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var city: TextView
    private lateinit var weatherIcon: ImageView
    private lateinit var weatherDescription: TextView
    private lateinit var temperature: TextView
    private lateinit var humidity: TextView
    private lateinit var pressure: TextView
    private lateinit var wind: TextView
    private lateinit var date: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        city = view.findViewById(R.id.city)
        weatherIcon = view.findViewById(R.id.meteoImg)
        weatherDescription = view.findViewById(R.id.description)
        temperature = view.findViewById(R.id.temp)
        humidity = view.findViewById(R.id.humidity)
        pressure = view.findViewById(R.id.pressure)
        wind = view.findViewById(R.id.wind)
        date = view.findViewById(R.id.date)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity?.intent!!.hasExtra(EXTRA_CITY_NAME)) {
            updateWeatherForCity(activity!!.intent.getStringExtra(EXTRA_CITY_NAME))
        }
    }

    private fun updateWeatherForCity(cityName: String) {
        this.cityName = cityName
        this.city.text = cityName

        val call = App.weatherService.getWeather("$cityName")
        call.enqueue(object: Callback<WeatherWrapper>{
            override fun onResponse(call: Call<WeatherWrapper>, response: Response<WeatherWrapper>){
                val weather = mapOpenWeatherDataToWeather(response.body()!!)
                updateUi(weather)
                Log.i(TAG, "OpenWeatherMap response: ${response?.body()}")
            }
            override fun onFailure(call: Call<WeatherWrapper>, t: Throwable) {
                Log.e(TAG, getString(R.string.weather_message_error_could_not_load_weather), t)
                context?.toast(getString(R.string.weather_message_error_could_not_load_weather))
            }

        })
    }

    private fun updateUi(weather: Weather) {
        Picasso.get()
            .load(weather.iconUrl)
            .into(weatherIcon)

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = current.format(formatter)

        date.text = formatted
        weatherDescription.text = weather.description
        temperature.text = getString(R.string.weather_temperature_value, weather.temperature.toInt())
        humidity.text = getString(R.string.weather_humidity_value, weather.humidity)
        pressure.text = getString(R.string.weather_pressure_value, weather.pressure.toString())
        wind.text = getString(R.string.weather_wind_value, weather.wind.toString())
    }
}