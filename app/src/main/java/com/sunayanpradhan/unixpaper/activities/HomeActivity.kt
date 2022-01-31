package com.sunayanpradhan.unixpaper.activities

import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.firebase.database.*
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.sunayanpradhan.unixpaper.R
import com.sunayanpradhan.unixpaper.adapters.SliderAdapter
import com.sunayanpradhan.unixpaper.adapters.WallpaperAdapter

class HomeActivity : AppCompatActivity() {

    lateinit var homeProgress:ProgressBar


    var sliderView: SliderView? = null
    val images = intArrayOf(
        R.drawable.trending,
        R.drawable.top_charts,
        R.drawable.creative,
        R.drawable.lifestyle,
        R.drawable.quiet_calm,
        R.drawable.editors_choice
    )


    var homeRecyclerView: RecyclerView?=null

    var list = ArrayList<String>()
    var Adapter: WallpaperAdapter? = null
    var reference: DatabaseReference? = null

    lateinit var app_bar_layout:AppBarLayout


    lateinit var nature: CardView
    lateinit var wildlife: CardView
    lateinit var neon_art: CardView
    lateinit var vehicles: CardView


    private var backPressedTime: Long = 0
    private var backToast: Toast? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeProgress=findViewById(R.id.HomeProgress)



        app_bar_layout=findViewById(R.id.app_bar_layout)

        supportActionBar?.hide()


        setUpSliderRecyclerView()

        setUpHomeRecyclerView()

        nature=findViewById(R.id.nature)
        wildlife=findViewById(R.id.wildlife)
        neon_art=findViewById(R.id.neon_art)
        vehicles=findViewById(R.id.vehicles)



        nature.setOnClickListener {

            var intent=Intent(this,NatureActivity::class.java)
            startActivity(intent)


        }

        wildlife.setOnClickListener {
            var intent=Intent(this,WildlifeActivity::class.java)
            startActivity(intent)


        }

        neon_art.setOnClickListener {
            var intent=Intent(this,NeonArtActivity::class.java)
            startActivity(intent)


        }

        vehicles.setOnClickListener {
            var intent=Intent(this,VehiclesActivity::class.java)
            startActivity(intent)


        }





    }



    private fun setUpSliderRecyclerView() {
        val sliderAdapter= SliderAdapter(images,this.applicationContext)
        sliderView= findViewById(R.id.image_slider)
        sliderView?.setSliderAdapter(sliderAdapter)
        sliderView?.setIndicatorAnimation(IndicatorAnimationType.WORM)
        sliderView?.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        sliderView?.startAutoCycle()



    }


    private fun setUpHomeRecyclerView() {
        val Adapter= WallpaperAdapter(list, this.applicationContext)

        homeRecyclerView=findViewById(R.id.homeRecyclerView)

        homeRecyclerView?.adapter = Adapter

        homeRecyclerView?.layoutManager = GridLayoutManager(this, 2)

        reference = FirebaseDatabase.getInstance().getReference("unixpaper_images").child("home")

        homeRecyclerView?.setHasFixedSize(true)




        getData()



    }

    private fun getData() {

        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (shot in snapshot.children) {
                    val data = shot.value.toString()
                    list.add(data)
                }
                homeRecyclerView?.layoutManager = GridLayoutManager(this@HomeActivity, 2)
                Adapter = WallpaperAdapter(list, this@HomeActivity)
                homeRecyclerView?.adapter = Adapter
                homeProgress.visibility=View.GONE

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@HomeActivity, "Error: " + error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast?.cancel()
            finishAffinity()
        }

        else {
            backToast = Toast.makeText(baseContext, "Double press to Exit", Toast.LENGTH_SHORT)
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()

    }
}