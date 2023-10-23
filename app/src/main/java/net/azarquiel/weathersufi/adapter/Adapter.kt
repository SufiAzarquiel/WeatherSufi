package net.azarquiel.recyclerviewdays.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.weathersufi.R
import net.azarquiel.weathersufi.model.Day

class Adapter(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var dataList: List<Day> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setDays(days: List<Day>) {
        this.dataList = days
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Day){
            // create view references
            val ivRowIcon = itemView.findViewById(R.id.ivRowIcon) as ImageView
            val tvRowDesc = itemView.findViewById(R.id.tvRowDesc) as TextView
            val tvRowTemp = itemView.findViewById(R.id.tvRowTemp) as TextView
            val tvRowDate = itemView.findViewById(R.id.tvRowDate) as TextView
            val tvRowPop = itemView.findViewById(R.id.tvRowPop) as TextView

            // set values
            tvRowPop.text = dataItem.probability.toString() + "%"
            tvRowDate.text = dataItem.date
            tvRowTemp.text = "Min: ${dataItem.temp.min}ºC - Max: ${dataItem.temp.max}ºC"
            tvRowDesc.text = dataItem.weather.get(0).description
            // load image from url
            Picasso.get()
                .load("http://openweathermap.org/img/wn/" + dataItem.weather.get(0).icon + "@4x.png")
                .into(ivRowIcon)

        }
    }
}