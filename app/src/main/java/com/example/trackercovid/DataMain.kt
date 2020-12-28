package com.example.trackercovid

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.Volley
import com.example.trackercovid.databinding.FragmentDataMainBinding


class DataMain : Fragment() {
    private lateinit var viewModelDataMain:ViewModelDataMain
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = FragmentDataMainBinding.inflate(inflater,container,false)

        var adapter = DataMainAdapter()


        adapter.fragmentContext = this

        adapter.items = mutableListOf<CovidDataMain>()

        var queue = Volley.newRequestQueue(context)

        binding.recyclerViewDataMain.layoutManager = LinearLayoutManager(context?.applicationContext)

        binding.recyclerViewDataMain.adapter = adapter

         viewModelDataMain = ViewModelProviders.of(this).get(ViewModelDataMain::class.java)

        viewModelDataMain.request.observe(viewLifecycleOwner, Observer { request->
            queue.add(request)
        })




        viewModelDataMain.getData()

        viewModelDataMain.dataList.observe(viewLifecycleOwner, Observer { dataList->
            adapter.items = dataList
            Log.d("dataList","called")
            var dataDistrict = dataList[0].districtData
            dataDistrict.map { i->Log.d("mapping",i.name.toString()) }
            adapter.notifyDataSetChanged()
        })
        
        return binding.root
    }
    fun navigateToDistrictData(name:String) {
        var dataState = viewModelDataMain.dataList.value?.filter { state->state.state==name }
        Log.d("dataMainArray", dataState!![0].districtData[0].name.toString())
        var dataDistrict = dataState?.get(0)?.districtData
        Log.d("seeArray", dataDistrict?.get(0)?.name.toString())
        var arrayDataDistrict = dataDistrict?.size?.let { Array<CovidDataDistrict>(it,{i->CovidDataDistrict()}) }
        if (arrayDataDistrict != null) {
            for (i in 0 until arrayDataDistrict.size){
                arrayDataDistrict[i] = dataDistrict?.get(i)!!
                Log.d("dataMainArray",arrayDataDistrict[i].name.toString())
            }
        }
        var action = arrayDataDistrict?.let { DataMainDirections.actionDataMain2ToDataDistrict3(it,name) }
        if (action != null) {
            findNavController().navigate(action)
        }
    }

}