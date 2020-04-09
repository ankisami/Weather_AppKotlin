package training.android.myweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener {

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
        }
    }
}
