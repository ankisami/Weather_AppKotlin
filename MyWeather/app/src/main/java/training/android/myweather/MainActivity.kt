package training.android.myweather

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
import training.android.myweather.api.CityWeatherData
import training.android.myweather.api.GetData
import training.android.myweather.api.HttpBinServiceJson

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

        if (view.tag != null) {
            val index = view.tag as Int
            val city = cities[index]
            Toast.makeText(this, "${city} a été sélectionnée", Toast.LENGTH_SHORT)
                .show()

            /*//Request HTTP api
             val retrofitJson = Retrofit.Builder()
                 .baseUrl("http://httpbin.org/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build()

             val serviceJson = retrofitJson.create(HttpBinServiceJson::class.java)
             val callJson = serviceJson.getDataWeatherInfo()
             callJson.enqueue(object: Callback<CityWeatherData>{

                 override fun onResponse(
                     call: Call<CityWeatherData>?,
                     response: Response<CityWeatherData>?
                 ) {
                     val getData = response?.body()
                     Log.i(TAG, "Request Api : ${getData?.name}")
                 }

                 override fun onFailure(call: Call<CityWeatherData>, t: Throwable) {
                     Log.i(TAG, "Request Api : ERROR")

                 }

             })*/
            val retrofitJson = Retrofit.Builder()
                .baseUrl("http://httpbin.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // generate service based on client
            val serviceJson = retrofitJson.create(HttpBinServiceJson::class.java)
            val callJson = serviceJson.getUserInfo()
            callJson.enqueue(object: Callback<GetData> {

                override fun onResponse(call: Call<GetData>?, response: Response<GetData>?) {
                    val getData = response?.body()
                    Log.i(TAG, "Received object: ${getData?.url}")
                }

                override fun onFailure(call: Call<GetData>?, t: Throwable?) {
                    Log.i(TAG, "CA NE MARCHE PAS")

                }

            })
        }
    }
}
