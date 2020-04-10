package training.android.myweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), View.OnClickListener {

    val cities = arrayOf<String>("Paris", "Lyon", "Dijon", "Marseille")
    val adapter = CityAdapter(cities, this)
    val database = Database(this)
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnCreateCity = findViewById(R.id.action_create_city) as ImageView
        val recyclerView = findViewById<RecyclerView>(R.id.cities_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnCreateCity.setOnClickListener{
            val createCity = CreateCityDialog()
            createCity.listener  = object : CreateCityDialog.CreateCityDialogueListener{
                override fun onDialogPositiveClick(cityName: String) {
                    database.createCity(cityName)
                }

                override fun onDialogNegativeClick() {
                }
            }



            createCity.show(supportFragmentManager,"tag")


        }

    }

    override fun onClick(view: View) {

        if (view.tag != null) {
            val index = view.tag as Int
            val city = cities[index]
            Toast.makeText(this, "${city} a été sélectionnée", Toast.LENGTH_SHORT)
                .show()
        }
    }


}
