package training.android.myweather

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView



class CityAdapter (val cities: Array<String>, val itemClickListener: View.OnClickListener)
    : RecyclerView.Adapter <CityAdapter.ViewHolder>() {

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardView = itemView.findViewById<CardView>(R.id.cardView_cityActivity)
        val name = itemView.findViewById<TextView>(R.id.nameCity_cityActivity)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.city_activity, parent, false)

        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        holder.name.text = city
        holder.cardView.tag = position
        holder.cardView.setOnClickListener(itemClickListener)
    }
}