package training.android.myweather.weather

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import training.android.myweather.R
import training.android.myweather.city.City
import training.android.myweather.city.CityFragment

class WeatherActivity: AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Remplace le Layout par défaut par notre fragment_weather
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, WeatherFragment.newInstance())
            .commit()
    }



}