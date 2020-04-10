package training.android.myweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import training.android.myweather.api.CityWeatherData
import training.android.myweather.api.GetData
import training.android.myweather.api.HttpBinServiceJson
import training.android.myweather.api.HttpBinServiceString
import java.lang.Exception

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = MainActivity::class.java.simpleName
    val cities = arrayOf<String>("Paris", "Lyon", "Dijon", "Marseille")
    val adapter = CityAdapter(cities, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.cities_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter



    }

    override fun onClick(view: View) {

          var  JsonData : CityWeatherData? = null
        if (view.tag != null) {
            val index = view.tag as Int
            val city = cities[index]
            Toast.makeText(this, "${city} a été sélectionnée", Toast.LENGTH_SHORT)
                .show()

            //Request HTTP api
             val retrofitJson = Retrofit.Builder()
                 .baseUrl("https://api.openweathermap.org/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()

             val serviceJson = retrofitJson.create(HttpBinServiceJson::class.java)
             val callJson = serviceJson.getDataWeatherInfo(city)

             callJson.enqueue(object: Callback<CityWeatherData>{
                 override fun onResponse(
                     call: Call<CityWeatherData>?,
                     response: Response<CityWeatherData>?
                 ) {
                     val getData = response?.body()
                     JsonData = getData
                     //Log.i(TAG, "Request Api : ${getData}")
                     Log.i(TAG, "Request Api : ${JsonData}")
                 }

                 override fun onFailure(call: Call<CityWeatherData>, t: Throwable) {
                     Log.i(TAG, "Request Api : ERROR :  $t")
                 }
             })

            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("description", JsonData?.weather?.get(0)?.description)
            intent.putExtra("icon", JsonData?.weather?.get(0)?.icon)
            intent.putExtra("temperature", JsonData?.main?.temperature)
            intent.putExtra("pressure", JsonData?.main?.pressure)
            intent.putExtra("humidity", JsonData?.main?.humidity)
            intent.putExtra("country", JsonData?.sys?.country)
            intent.putExtra("city", city)

            startActivity(intent)
        }
    }
}
