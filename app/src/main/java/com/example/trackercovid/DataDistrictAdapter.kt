package com.example.trackercovid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataDistrictAdapter:RecyclerView.Adapter<ItemViewHolderDistrict>() {
    var districtDataList = mutableListOf<CovidDataDistrict>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolderDistrict {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.view_data_main,parent,false)
        return ItemViewHolderDistrict(view)
    }

    override fun getItemCount(): Int {
        return districtDataList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolderDistrict, position: Int) {
        holder.itemName.text = "${districtDataList[position].name }"
        holder.itemData.text = " Total Cases - ${districtDataList[position].confirmed}\n  "

    }

}
class ItemViewHolderDistrict(val itemview: View):RecyclerView.ViewHolder(itemview){
    var itemName = itemView.findViewById<TextView>(R.id.showName)
    var itemData = itemView.findViewById<TextView>(R.id.showdata)
}