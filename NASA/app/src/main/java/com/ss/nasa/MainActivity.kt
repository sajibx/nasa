package com.ss.nasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.ss.nasa.fragment.SplashFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        init()
    }

    fun init()
    {
        val transaction = this.supportFragmentManager.beginTransaction()
        var splash_fragment = SplashFragment()
        transaction.replace(R.id.universal, splash_fragment)
        //transaction.addToBackStack(null)
        transaction.commit()
    }
}
