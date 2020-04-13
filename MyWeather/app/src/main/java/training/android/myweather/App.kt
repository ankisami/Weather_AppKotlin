package training.android.myweather

import android.app.Application
import training.android.myweather.model.Database
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import training.android.myweather.openweathermap.OpenWeatherService

class App: Application(){

    companion object {
        lateinit var instance: App
        val database: Database by lazy {
            Database(instance)
        }

        // Récupère les logs reçus par l'api
        private val httpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        //Client retrofit pour faire des requete http sur l'api
        // Et convertis les infos en classe avec GsonConverter
        private val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherService: OpenWeatherService = retrofit.create(OpenWeatherService::class.java)
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}