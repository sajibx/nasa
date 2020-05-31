package com.ss.nasa.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.ss.nasa.R
import com.ss.nasa.fragment.data.Appsession
import com.ss.nasa.fragment.data.Covid

class CovidAdapter(
    val listdata: Covid,
    val progressBar: ProgressBar,
    val fragmentManager: FragmentManager?
) :RecyclerView.Adapter<CovidAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_covid, parent, false)
        //p = parent.context
        return CovidAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listdata.Countries.size
    }

    override fun onBindViewHolder(holder: CovidAdapter.ViewHolder, position: Int) {
        if (progressBar.isVisible)
        {
            progressBar.visibility = View.INVISIBLE
        }
        var data = listdata.Countries[position]
        holder.country.text = data.Country
        holder.newConfirm.text = "New Confirmed : "+data.NewConfirmed.toString()
        holder.newRecovered.text = "New Recovered : "+data.NewRecovered.toString()
        holder.newDeath.text = "New Death : "+data.NewDeaths.toString()

        holder.totalConfirmed.text = "Total Confirmed : "+data.TotalConfirmed.toString()
        holder.totalRecovered.text = "Total Recovered : "+data.TotalRecovered.toString()
        holder.totalDeath.text = "Total Death : "+data.TotalDeaths.toString()
        var t = data.Date.replace("T", " at ")
        t = t.replace("Z", " ")

        holder.date.text = t

        holder.list.setOnClickListener()
        {


            Appsession.Country = data.Country
            Appsession.CountryCode = data.CountryCode
            Appsession.Date = data.Date
            Appsession.NewConfirmed = data.NewConfirmed
            Appsession.NewRecovered = data.NewRecovered
            Appsession.NewDeaths = data.NewDeaths
            Appsession.TotalConfirmed = data.TotalConfirmed
            Appsession.TotalRecovered = data.TotalRecovered
            Appsession.TotalDeaths = data.TotalDeaths
            Appsession.Slug = data.Slug


            val transaction = fragmentManager!!.beginTransaction()
            var splash_fragment = DetailFragment()
            transaction.replace(R.id.universal, splash_fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    class ViewHolder(item:View):RecyclerView.ViewHolder(item)
    {
        var country = item.findViewById(R.id.country) as TextView
        var newConfirm = item.findViewById(R.id.newConfirm) as TextView
        var newRecovered = item.findViewById(R.id.newRecovered) as TextView
        var newDeath = item.findViewById(R.id.newDeath) as TextView
        var totalConfirmed = item.findViewById(R.id.totalConfirmed) as TextView
        var totalRecovered = item.findViewById(R.id.totalRecovered) as TextView
        var totalDeath = item.findViewById(R.id.totalDeath) as TextView
        var date = item.findViewById(R.id.date) as TextView

        var list = item.findViewById(R.id.list) as View
    }
}