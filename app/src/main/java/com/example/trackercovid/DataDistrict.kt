package com.example.trackercovid

import android.os.Bundle
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.*
import androidx.databinding.DataBinderMapperImpl
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.trackercovid.databinding.FragmentDataDistrictBinding
import org.json.JSONArray
import org.json.JSONObject


class DataDistrict : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =FragmentDataDistrictBinding.inflate(inflater)
        val adapter = DataDistrictAdapter()
        val args = DataDistrictArgs.fromBundle(requireArguments())
        var mutableList = mutableListOf<CovidDataDistrict>()
        var arrayDistrictData = args.dataDistrict
        binding.stateName.text = args.stateName
        for (i in 0 until arrayDistrictData.size){
            if(arrayDistrictData[i]!=null){
                Log.d("array",arrayDistrictData[i].toString())
            mutableList.add(arrayDistrictData[i])
            }
        }
        adapter.districtDataList = mutableList
    //    Log.d("newData",viewModelDataDistrict.dataListDistrict.toString())
        binding.recyclerViewDataDistrict.layoutManager = LinearLayoutManager(context?.applicationContext)
        binding.recyclerViewDataDistrict.adapter = adapter

        return binding.root
    }

}