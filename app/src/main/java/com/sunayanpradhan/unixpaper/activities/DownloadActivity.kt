package com.sunayanpradhan.unixpaper.activities

import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sunayanpradhan.unixpaper.R
import com.sunayanpradhan.unixpaper.adapters.WallpaperAdapter

class DownloadActivity : AppCompatActivity() {

    private lateinit var decorView:View

    private lateinit var setImage: ImageView
    private  lateinit var setButton: Button

    var url: String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_download)




        supportActionBar?.hide()

        decorView = window.decorView

        decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility == 0) {
                decorView.systemUiVisibility = hideSystemBars()
            }
        }




        setImage=findViewById(R.id.setImage)
        setButton=findViewById(R.id.setButton)


        val intent = intent
        url = intent.getStringExtra(WallpaperAdapter.PHOTO)
        Glide.with(this).load(getIntent().getStringExtra(WallpaperAdapter.PHOTO))
            .into(setImage)






        setButton.setOnClickListener {
            setButton.visibility=View.INVISIBLE


            if (setButton.visibility==View.INVISIBLE){
                val bitmap = (setImage.drawable as BitmapDrawable).bitmap
                val manager = WallpaperManager.getInstance(applicationContext)
                manager.setBitmap(bitmap)
                Toast.makeText(this, "Successfully Set", Toast.LENGTH_SHORT).show()
                setButton.visibility=View.VISIBLE
                setButton.text="APPLIED"
                setButton.isEnabled=false

            }
            else{
                setButton.isEnabled=true
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