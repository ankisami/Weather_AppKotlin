package AppAndroidKotlin.weather.weather

import AppAndroidKotlin.weather.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class WeatherFragment: Fragment() {

    companion object {
        val EXTRA_CITY_NAME = "kotlin.weather.extras.EXTRA_CITY_NAME"

        fun newInstance() = WeatherFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        return view
    }
}