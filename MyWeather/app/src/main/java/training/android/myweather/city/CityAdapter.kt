package training.android.myweather.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.draw_item_city.view.*
import training.android.myweather.R

class CityAdapter(private val cities: List<City>,
                  private val cityListener: CityAdapter.CityItemList)
    : RecyclerView.Adapter<CityAdapter.ViewHolder>(), View.OnClickListener {

    interface CityItemList {
        fun CitySelected(city: City)
        fun CityDeleted(city: City)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardView = itemView.findViewById<CardView>(R.id.cardView_item_city)!!
        val cityNameView = itemView.findViewById<TextView>(R.id.city_name)!!
        val deleteView = itemView.findViewById<ImageView>(R.id.delete)!!
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.draw_item_city, parent, false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        with(holder) {
            cardView.tag = city
            cardView.setOnClickListener(this@CityAdapter)
            cityNameView.text = city.name
            deleteView.tag = city
            deleteView.setOnClickListener(this@CityAdapter)
        }
    }

    override fun getItemCount(): Int = cities.size

    override fun onClick(v: View) {
        when(v.id) {
            R.id.cardView_item_city -> cityListener.CitySelected(v.tag as City);
            R.id.delete -> cityListener.CityDeleted(v.tag as City)
        }
    }

}