package AppAndroidKotlin.weather.city

import AppAndroidKotlin.weather.App
import AppAndroidKotlin.weather.Database
import AppAndroidKotlin.weather.R
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CityFragment: Fragment(), CityAdapter.CityItemListener {

    private lateinit var cities: MutableList<City>
    private lateinit var  database: Database
    private lateinit var recyclerView: RecyclerView
    private  lateinit var adapter: CityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = App.database
        //cities = mutableListOf()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_city, container, false)
        recyclerView = view.findViewById(R.id.cities_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cities = database.getAllCities()
        adapter = CityAdapter(cities, this)
        recyclerView.adapter = adapter
    }


    override fun onCitySelected(city: City) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCityDeleted(city: City) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.fragment_city, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.action_create_city -> {
                showCreateCityDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showCreateCityDialog() {
        val createCityFragment = CreateCityDialogueFragment()
        createCityFragment.listener = object: CreateCityDialogueFragment.CreateCityDialogueListener {
            override fun onDialogPositiveClick(cityName: String) {
                saveCity(City(cityName))
            }

            override fun onDialogNegativeClick() { }
        }

        fragmentManager?.let { createCityFragment.show(it, "CreateCityDialog" ) }
    }



    private fun saveCity(city: City) {
       if (database.createCity(city)) {
           cities.add(city)
           adapter.notifyDataSetChanged()
       } else {
           Toast.makeText(context,
               "Could not create city",
               Toast.LENGTH_SHORT).show()
       }
    }



}