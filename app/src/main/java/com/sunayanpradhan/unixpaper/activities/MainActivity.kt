package com.sunayanpradhan.unixpaper.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sunayanpradhan.unixpaper.R
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var timer:Timer

    private lateinit var decorView: View



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        timer= Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        }, 2000)


        decorView = window.decorView

        decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility == 0) {
                decorView.systemUiVisibility = hideSystemBars()
            }
        }


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            decorView.systemUiVisibility = hideSystemBars()
        }
    }

    private fun hideSystemBars(): Int {
        return (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                or View.SYSTEM_UI_FLAG_VISIBLE)
    }
}