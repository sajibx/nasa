package com.ss.nasa.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.kittinunf.fuel.httpGet

import com.ss.nasa.R
import com.ss.nasa.fragment.data.Air_Quality2
import com.ss.nasa.fragment.data.Appsession
import com.ss.nasa.fragment.data.Covid2
import com.ss.nasa.fragment.data.Weather_Data2
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init()
    {
        //Toast.makeText(context, Appsession.CountryCode, Toast.LENGTH_LONG).show()



        "https://api.openaq.org/v1/locations?country=${Appsession.CountryCode.toString()}"
            .httpGet()
            .responseObject(Air_Quality2.Deserializer())
            { request, response, result ->
                when (result) {
                    is com.github.kittinunf.result.Result.Failure -> {
                        val ex = result.getException()
                        updateUI {
                            //Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show()
                        }

                    }
                    is com.github.kittinunf.result.Result.Success -> {

                        var data = result

//                        var adapter = CovidAdapter(result.value,progressBar,fragmentManager)
//                        recyclerView.adapter = adapter

                        updateUI {
                            var k = result.value.results.size-1

                            try {
                                air_quality.text = "Air Quality : "+result.value.results[k].countsByMeasurement[0].count.toString()+" pm25"
                            }catch (e:Exception)
                            {
                                air_quality.text = "Air Quality : pm25"
                            }
                            //Toast.makeText(context, Appsession.CountryCode, Toast.LENGTH_LONG).show()
                        }


                    }
                }
            }



        var CITY:String? = Appsession.Country
        val API = "17279f6e39db894ca499af2102f2310a"

        launch {
            "https://api.openweathermap.org/data/2.5/forecast?q=$CITY&units=metric&appid=$API"
            .httpGet()
            .responseObject(Weather_Data2.Deserializer())
            { request, response, result ->
                when (result) {
                    is com.github.kittinunf.result.Result.Failure -> {
                        val ex = result.getException()
                        updateUI {
                            //Toast.makeText(context, ex.toString(), Toast.LENGTH_LONG).show()
                        }

                    }
                    is com.github.kittinunf.result.Result.Success -> {

                        var data = result

//                        var adapter = CovidAdapter(result.value,progressBar,fragmentManager)
//                        recyclerView.adapter = adapter

                        updateUI {

                            tempareture.text = "Tempareture : "+result.value.list[2].main.temp.toString()+" C"
                            humidity.text = "Humidity : "+result.value.list[2].main.humidity+"%"

                            if (result.value.list[0].main.temp>25)
                            {
                                if (((Appsession.NewDeaths.toDouble()/Appsession.NewConfirmed)*100)<2)
                                {
                                    probDeath.text = "You are less likely to die if located in this region"
                                }
                                else
                                {
                                    probDeath.text = "You are more likely to die if located in this region"
                                }

                                if (((Appsession.NewRecovered.toDouble()/Appsession.NewConfirmed)*100)<25)
                                {
                                    probRecover.text = "You are less likely to recover if located in this region"
                                }
                                else
                                {
                                    probRecover.text = "You are more likely to recover if located in this region"
                                }

                            }
                            else if (result.value.list[0].main.temp in 15.0..24.0)
                            {
                                if (((Appsession.NewDeaths.toDouble()/Appsession.NewConfirmed)*100)>2)
                                {
                                    probDeath.text = "You are more likely to die if located in this region"
                                }
                                else
                                {
                                    probDeath.text = "You are less likely to die if located in this region"
                                }
                                if (((Appsession.NewRecovered.toDouble()/Appsession.NewConfirmed)*100)>25)
                                {
                                    probRecover.text = "You are more likely to recover if located in this region"
                                }
                                else
                                {
                                    probRecover.text = "You are less likely to recover if located in this region"
                                }
                            }
                            else if (result.value.list[0].main.temp in 9.0..14.0)
                            {
                                if (((Appsession.NewDeaths.toDouble()/Appsession.NewConfirmed)*100)>2)
                                {
                                    probDeath.text = "You are more likely to die if located in this region"
                                }
                                else
                                {
                                    probDeath.text = "You are less likely to die if located in this region"
                                }
                                if (((Appsession.NewRecovered.toDouble()/Appsession.NewConfirmed)*100)>25)
                                {
                                    probRecover.text = "You are more likely to recover if located in this region"
                                }
                                else
                                {
                                    probRecover.text = "You are less likely to recover if located in this region"
                                }
                            }
                            //Toast.makeText(context, Appsession.CountryCode, Toast.LENGTH_LONG).show()
                        }


                    }
                }
            }
        }


        totalCases.text = Appsession.TotalConfirmed.toString()
        totalDeaths.text = Appsession.TotalDeaths.toString()
        totalRecovered.text = "Recovered : "+Appsession.TotalRecovered.toString()

        ncs.text = "New Confirmed Cases : "+Appsession.NewConfirmed.toString()
        nrs.text = "New Recovered Cases : "+Appsession.NewRecovered.toString()
        nds.text = "New Death Cases : "+Appsession.NewDeaths.toString()

        tcs.text = "Total Confirmed Cases : "+Appsession.TotalConfirmed.toString()
        trs.text = "Total Recovered Cases : "+Appsession.TotalRecovered.toString()
        tds.text = "Total Death Cases : "+Appsession.TotalDeaths.toString()




            var newRecovery = (Appsession.NewRecovered.toDouble()/Appsession.NewConfirmed)*100
            var newDeath = (Appsession.NewDeaths.toDouble()/Appsession.NewConfirmed)

        //Toast.makeText(context, Appsession.NewConfirmed.toString()+"--"+Appsession.NewDeaths.toString()+"--"+Appsession.NewRecovered.toString(), Toast.LENGTH_LONG).show()

            recoveryRate.text = "Recovery Rate : " + newRecovery.toString()+" %"
            deathRate.text = "Death Rate : " + newDeath.toString()+" %"




    }

}
