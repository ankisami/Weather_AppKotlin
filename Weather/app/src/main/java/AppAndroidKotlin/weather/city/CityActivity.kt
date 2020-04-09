package AppAndroidKotlin.weather.city

import AppAndroidKotlin.weather.R
import AppAndroidKotlin.weather.weather.WeatherActivity
import AppAndroidKotlin.weather.weather.WeatherFragment
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_city.*
import java.io.Console

class  CityActivity : AppCompatActivity(), CityFragment.CityFragmentListener {

    private lateinit var cityFragment: CityFragment
    private var currentCity: City? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        cityFragment = supportFragmentManager.findFragmentById(R.id.city_fragment) as CityFragment
        cityFragment.listener = this



        //fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        //obtieneLocalizacion()
    }

    override fun onCitySelected(city: City) {
        currentCity = city
        startWeatherActivity(city)
    }

    private fun startWeatherActivity(city: City) {

        val intent = Intent(this, WeatherActivity::class.java)
        intent.putExtra(WeatherFragment.EXTRA_CITY_NAME, city.name)
        startActivity(intent)
    }
    private fun obtieneLocalizacion(){
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                val latitude =  location?.latitude
                val longitude = location?.longitude
                Log.i("localisation","latitude"+latitude+" longitude "+longitude)
            }
    }
}
