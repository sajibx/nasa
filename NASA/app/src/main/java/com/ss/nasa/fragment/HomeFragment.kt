package com.ss.nasa.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

import com.ss.nasa.R
import com.ss.nasa.fragment.data.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //init()

        if (Appsession.covid.Date.length<=2)
        {
            Toast.makeText(context, "Restart the application if it takes too long to load due to api limitation", Toast.LENGTH_LONG).show()
            init()
        }
        else
        {
            var recyclerView = view!!.findViewById<RecyclerView>(R.id.recycler)
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            var adapter = CovidAdapter(Appsession.covid,progressBar,fragmentManager)
            recyclerView.adapter = adapter
        }
    }

    fun init()
    {
        var recyclerView = view!!.findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)

        Appsession.name = "sajib"
        "https://api.covid19api.com/summary"
            .httpGet()
            .responseObject(Covid2.Deserializer())
            { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()

                    }
                    is Result.Success -> {

                        var data = result

//                        var adapter = CovidAdapter(result.value,progressBar,fragmentManager)
//                        recyclerView.adapter = adapter

                        updateUI {
                            Appsession.covid = result.value
                            Appsession.name = "sajib"
                            var adapter = CovidAdapter(Appsession.covid,progressBar,fragmentManager)
                            recyclerView.adapter = adapter
                        }


                    }
                }
            }

        //Toast.makeText(context,Appsession.name,Toast.LENGTH_LONG).show()

//        "https://api.openaq.org/v1/locations?country=BD"
//            .httpGet()
//            .responseObject(Air_Quality2.Deserializer())
//            { request, response, result ->
//                when (result) {
//                    is Result.Failure -> {
//                        val ex = result.getException()
//                        Toast.makeText(context, "error",Toast.LENGTH_LONG).show()
//
//                    }
//                    is Result.Success -> {
//
//                        var data = result
//
//                        var k = result.value.results.size-1
//                        Toast.makeText(context, result.value.results[k].countsByMeasurement[0].count.toString()+" "+result.value.results[k].countsByMeasurement[0].parameter, Toast.LENGTH_LONG).show()
//                        //Toast.makeText(context,"lol",Toast.LENGTH_LONG).show()
//
//
//                    }
//                }
//            }



//        var CITY:String? = "Bangladesh"
//        val API = "17279f6e39db894ca499af2102f2310a"
//
//        launch {
//            "https://api.openweathermap.org/data/2.5/forecast?q=$CITY&units=metric&appid=$API"
//            .httpGet()
//            .responseObject(Weather_Data2.Deserializer())
//            { request, response, result ->
//                when (result) {
//                    is Result.Failure -> {
//                        val ex = result.getException()
//                        //Toast.makeText(context, "error",Toast.LENGTH_LONG).show()
//                        updateUI {
//                            Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show()
//                            Log.e("sajib",ex.toString())
//                        }
//
//                    }
//                    is Result.Success -> {
//
//                        var data = result
//
//                        updateUI {
//                            Toast.makeText(context, result.value.list[0].main.humidity.toString()+"%" ,Toast.LENGTH_LONG).show()
//                        }
//
//                        //var k = result.value.results.size-1
//                        //Toast.makeText(context, result.value.results[k].countsByMeasurement[0].count.toString()+" "+result.value.results[k].countsByMeasurement[0].parameter, Toast.LENGTH_LONG).show()
//                        //Toast.makeText(context,"lol",Toast.LENGTH_LONG).show()
//
//
//                    }
//                }
//            }
//        }


    }

}
