package com.sunayanpradhan.unixpaper.activities

import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sunayanpradhan.unixpaper.R
import com.sunayanpradhan.unixpaper.adapters.WallpaperAdapter

class DownloadActivity : AppCompatActivity() {

    private lateinit var setImage: ImageView
    private  lateinit var setButton: Button

    var url: String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        supportActionBar?.hide()



        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

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
}