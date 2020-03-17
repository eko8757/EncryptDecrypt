package com.example.enkripdekrip.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.enkripdekrip.MainActivity
import com.example.enkripdekrip.R

class SplashActivity : AppCompatActivity() {

    private var handler: Handler? = null
    private var SPLASH_DURATION: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler = Handler()
        handler!!.postDelayed(runnable, SPLASH_DURATION)
    }

    internal val runnable: Runnable = Runnable {
        if (!isFinishing) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onDestroy() {

        if (handler != null) {
            handler!!.removeCallbacks(runnable)
        }

        super.onDestroy()

    }
}
