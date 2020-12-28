package com.example.trackercovid

import android.os.Parcel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class ViewModelDataMain:ViewModel() {
    private var _dataList = MutableLiveData<MutableList<CovidDataMain>>()
    val dataList:LiveData<MutableList<CovidDataMain>>
    get() = _dataList


    private val url = "https://api.covidindiatracker.com/state_data.json"

    private  var auxDataList = mutableListOf<CovidDataMain>()


    private var _test = MutableLiveData<Boolean>()
    val test : LiveData<Boolean>
        get() = _test


    private var _request = MutableLiveData<JsonArrayRequest>()
    val request : LiveData<JsonArrayRequest>
    get() = _request


    fun getData(){

     _request.value = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                    Log.d("Volley","This was called")
                for (i in 0 until response.length()) {
                    var covidDataMain=CovidDataMain()

                    var obj = response[i] as JSONObject
                    Log.d("firstElement",obj.getString("state"))
                    covidDataMain.state = obj.getString("state")
                    covidDataMain.active = obj.getString("active")
                    covidDataMain.confirmed = obj.getString("confirmed")
                    covidDataMain.recovered = obj.getString("recovered")
                    covidDataMain.deaths = obj.getString("deaths")

                  var  dataDistrictList = mutableListOf<CovidDataDistrict>()
                    var parcel = Parcel.obtain()
                    for (j in 0 until obj.getJSONArray("districtData").length()) {
                        var obj = obj.getJSONArray("districtData")[j] as JSONObject
                        var covidDataDistrict = CovidDataDistrict()
                        covidDataDistrict.name = obj.getString("name")
                        covidDataDistrict.confirmed = obj.getString("confirmed")
                        covidDataDistrict.recovered = obj.getString("recovered")
                        covidDataDistrict.deaths = obj.getString("deaths")
                        dataDistrictList.add(covidDataDistrict)
                    }
                    covidDataMain.districtData = dataDistrictList
                    auxDataList.add(covidDataMain)

                }
                _dataList.value =auxDataList
            },
            Response.ErrorListener { error ->  Log.d("ErrorVolley",error.toString())
            })
        //findNavController().navigate(DataMainDirections.ActionDataMain2ToDataDistrict3)
        //adapter.notifyDataSetChanged()
    }
}