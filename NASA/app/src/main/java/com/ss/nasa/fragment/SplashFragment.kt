package com.ss.nasa.fragment


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ss.nasa.R
import com.ss.nasa.fragment.HomeFragment

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val transaction = fragmentManager!!.beginTransaction()
//        var splash_fragment = HomeFragment()
//        transaction.replace(R.id.universal, splash_fragment)
//        //transaction.addToBackStack(null)
//        transaction.commit()


        Handler().postDelayed(
            {
                val transaction = fragmentManager!!.beginTransaction()
                var splash_fragment = HomeFragment()
                transaction.replace(R.id.universal, splash_fragment)
                //transaction.addToBackStack(null)
                transaction.commit()
            },4036
        )
    }


}
