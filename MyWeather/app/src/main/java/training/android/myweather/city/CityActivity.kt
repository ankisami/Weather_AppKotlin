package training.android.myweather.city

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import training.android.myweather.R
import training.android.myweather.weather.WeatherActivity
import training.android.myweather.weather.WeatherFragment

class CityActivity : AppCompatActivity(), CityFragment.CityFragmentListener {

    private lateinit var cityFragment: CityFragment
    private var currentCity: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // On récupère le city_Fragment depuis le layout Activity_main
        cityFragment = supportFragmentManager.findFragmentById(R.id.city_fragment) as CityFragment
        cityFragment.listener = this

    }
    private fun startWeatherActivity(city: City) {
        val intent = Intent(this, WeatherActivity::class.java)
        //Ajoute le nom de la ville en extra pour faire la requête api
        intent.putExtra(WeatherFragment.EXTRA_CITY_NAME, city.name )
        startActivity(intent)
    }

    override fun CitySelected(city: City) {
        currentCity = city
        startWeatherActivity(city)
    }
}
