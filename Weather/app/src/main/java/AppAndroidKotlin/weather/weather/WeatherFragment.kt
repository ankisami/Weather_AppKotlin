package AppAndroidKotlin.weather.weather

import AppAndroidKotlin.weather.App
import AppAndroidKotlin.weather.R
import AppAndroidKotlin.weather.api_openWeatherMap.WeatherWrapper
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class WeatherFragment: Fragment() {

    companion object {
        val EXTRA_CITY_NAME = "kotlin.weather.extras.EXTRA_CITY_NAME"

        fun newInstance() : WeatherFragment = WeatherFragment()
    }

    private val TAG = WeatherFragment::class.java.simpleName

    private lateinit var cityName: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity?.intent!!.hasExtra(EXTRA_CITY_NAME)) {
            updateWeatherForCity(activity!!.intent.getStringExtra(EXTRA_CITY_NAME))
        }
    }

    private fun updateWeatherForCity(cityName: String) {
        this.cityName = cityName

        val call = App.weatherService.getWeather("$cityName,fr")
        call.enqueue(object : Callback<WeatherWrapper>{

            override fun onResponse(call: Call<WeatherWrapper>,
                                    response: Response<WeatherWrapper>?) {
                Log.i(TAG, "OpenWeatherMap response: ${response?.body()}")
            }

            override fun onFailure(call: Call<WeatherWrapper>, t: Throwable) {
                Log.e(TAG, "Could not load city weather", t)
                Toast.makeText(activity,
                    getString(R.string.weather_message_error_could_not_load_city_weather),
                    Toast.LENGTH_SHORT).show()
            }

        })


    }
}