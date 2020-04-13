package training.android.myweather.city

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import training.android.myweather.App
import training.android.myweather.R
import training.android.myweather.model.Database
import training.android.myweather.utils.toast

class CityFragment: Fragment(), CityAdapter.CityItemList {

    interface CityFragmentListener {
        fun CitySelected(city: City)
    }

    var listener: CityFragmentListener? = null

    private lateinit var cities: MutableList<City>
    private lateinit var database: Database
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
        var view = inflater.inflate(R.layout.fragment_city, container, false)
        recyclerView = view.findViewById(R.id.cities_RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cities = database.getAllCities()
        adapter = CityAdapter(cities, this)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.fragment_city, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_create_city -> {
                showCreateCityDialog()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showCreateCityDialog() {
        val createCityFragment = CreateCityDialogFragment()
        createCityFragment.listener = object: CreateCityDialogFragment.CreateCityDialogListener {

            override fun onDialogPositiveClick(cityName: String) {
                saveCity(City(cityName))
            }

            override fun onDialogNegativeClick() { }
        }

        fragmentManager?.let { createCityFragment.show(it, "Create_City_DialogFragment") }
    }



    override fun CitySelected(city: City) {
        listener?.CitySelected(city)
    }

    override fun CityDeleted(city: City) {
        showDeleteCityDialog(city)
    }

    private fun showDeleteCityDialog(city: City) {
        val deleteCityFragment = DeleteCityDialogFragment.newInstance(city.name)
        deleteCityFragment.listener = object: DeleteCityDialogFragment.DeleteDialogListener{
            override fun onDialogPositiveClick() {
                deleteCity(city)
            }

            override fun onDialogNegativeClick() {}
        }

        deleteCityFragment.show(this!!.fragmentManager!!, "DeleteCityDialogFragment")
        //fragmentManager?.let { deleteCityFragment.show(it, "Delete_City_DialogFragment") }
    }


    private fun saveCity(city: City) {
        if(database.createCity(city)) {
            cities.add(city)
            adapter.notifyDataSetChanged()
        } else {
            context!!.toast(getString(R.string.city_message_error_could_not_create_city))
        }
    }

    private fun deleteCity(city: City) {
        if(database.deleteCity(city)){
            //Supprime la ville de la List<City>
            cities.remove(city)
            adapter.notifyDataSetChanged()
            context!!.toast(getString(R.string.city_message_info_city_delete, city.name))

        } else {
            context!!.toast(getString(R.string.city_message_error_could_not_delete_city, city.name))
        }
    }
}