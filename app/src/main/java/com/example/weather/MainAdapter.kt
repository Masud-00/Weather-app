package com.example.weather

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather.model.Weather
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainAdapter(private val context: Context, private val listener: Context): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
        private val weatherList=ArrayList<Weather>()
        inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val location: TextView =itemView.findViewById(R.id.location)
            val desc: TextView=itemView.findViewById(R.id.desc)
            val temp: TextView =itemView.findViewById(R.id.temp)
            val maxTemp: TextView=itemView.findViewById(R.id.maxTemp)
            val minTemp: TextView=itemView.findViewById(R.id.minTemp)
            val humidity: TextView=itemView.findViewById(R.id.humidity)
            val pressure: TextView=itemView.findViewById(R.id.pressure)
            val wind: TextView=itemView.findViewById(R.id.wind)
            val sunset: TextView=itemView.findViewById(R.id.sunset)
            val sunrise: TextView=itemView.findViewById(R.id.sunrise)
            val feelLikes: TextView=itemView.findViewById(R.id.feelTemp)
            val img: ImageView=itemView.findViewById(R.id.image)
            val visibility:TextView=itemView.findViewById(R.id.visibility)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view= LayoutInflater.from(parent.context).inflate(R.layout.card_view1,parent,false)
            val viewHolder=MainViewHolder(view)
            view.setOnClickListener {
                listener.onItemClick(weatherList[viewHolder.adapterPosition])
            }
            return viewHolder
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val currentItem=weatherList[position]

            holder.location.text=currentItem.name
            holder.temp.text=currentItem.main.temp.toInt().toString()+ "°C"
            holder.maxTemp.text=currentItem.main.temp_max.toInt().toString() + "°C"
            holder.minTemp.text=currentItem.main.temp_min.toInt().toString() + "°C"
            holder.humidity.text=currentItem.main.humidity.toString() + "%"
            holder.pressure.text=currentItem.main.pressure.toString()
            holder.wind.text= currentItem.wind.speed.toString() + "km/s"
            holder.sunrise.text= SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(currentItem.sys.sunrise.toLong()*1000)).toString()
            holder.sunset.text=SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(currentItem.sys.sunset.toLong()*1000)).toString()
           holder.feelLikes.text=currentItem.main.feels_like.toInt().toString() + "°C"
            holder.desc.text=currentItem.weather[0].description
            holder.visibility.text=currentItem.visibility.toString()
         val desc=currentItem.weather[0].main
            when(desc){
                "Clear"->Glide.with(context).load(R.drawable.clear_sky).centerCrop().into(holder.img)
                "Clouds"->Glide.with(context).load(R.drawable.few_clouds).centerCrop().into(holder.img)
                "Rain"->Glide.with(context).load(R.drawable.scattered_cloud).centerCrop().into(holder.img)
                "Drizzle"->Glide.with(context).load(R.drawable.broken_cloud).centerCrop().into(holder.img)
                "Shower rain"->Glide.with(context).load(R.drawable.shower_rain).centerCrop().into(holder.img)
                "Rain"->Glide.with(context).load(R.drawable.rain).centerCrop().into(holder.img)
                "Thunderstorm"->Glide.with(context).load(R.drawable.thunderstrom).centerCrop().into(holder.img)
                "Snow"->Glide.with(context).load(R.drawable.snow).centerCrop().into(holder.img)
                "Mist"->Glide.with(context).load(R.drawable.mist).centerCrop().into(holder.img)
                "Haze"->Glide.with(context).load(R.drawable.haze).centerCrop().into(holder.img)
                "Atmosphere"->Glide.with(context).load(R.drawable.overcast_clouds).centerCrop().into(holder.img)


            }
        }


    override fun getItemCount(): Int {
            return weatherList.size
        }
        @SuppressLint("NotifyDataSetChanged")
        fun updateNews(items: Weather){
         weatherList.clear()
                weatherList.addAll((listOf(items)))
            notifyDataSetChanged()
        }
    }

    private fun Context.onItemClick(weather: Weather) {


    }


