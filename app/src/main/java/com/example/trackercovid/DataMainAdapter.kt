package com.example.trackercovid

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DataMainAdapter(): RecyclerView.Adapter<ItemViewHolder>() {
    var items:MutableList<CovidDataMain> = mutableListOf()
    var fragmentContext:DataMain? = null
    var index:Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var view= LayoutInflater.from(parent.context).inflate(R.layout.view_data_main,parent,false)
        var holder = ItemViewHolder(view)
        view.setOnClickListener() {
            fragmentContext?.navigateToDistrictData(holder.itemName.text.toString())
      //      Toast.makeText(parent.context,"this is it",Toast.LENGTH_SHORT).show()
//            Log.d("indexAdapter",items[holder.adapterPosition].districtData[1].name.toString())
//            Log.d("indexAdapterItemId",holder.itemview.text. .toString())
        }
        return holder
    }

    override fun getItemCount(): Int {
            return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemName.text = "${items[position].state}"
        holder.itemData.text = "  Total Cases - ${items[position].confirmed}\n  Active Cases - ${items[position].active}\n  Total Deaths - ${items[position].deaths}\n  Recovered Cases - ${items[position].recovered}"
        index = position
        Log.d("indexAdapter",index.toString())
    }
}

class ItemViewHolder(val itemview: View):RecyclerView.ViewHolder(itemview){
    var itemName = itemView.findViewById<TextView>(R.id.showName)
    var itemData = itemView.findViewById<TextView>(R.id.showdata)
}