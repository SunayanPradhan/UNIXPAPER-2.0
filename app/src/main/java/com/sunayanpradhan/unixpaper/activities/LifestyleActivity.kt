package com.sunayanpradhan.unixpaper.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.sunayanpradhan.unixpaper.R
import com.sunayanpradhan.unixpaper.adapters.WallpaperAdapter
import java.util.ArrayList

class LifestyleActivity : AppCompatActivity() {

    lateinit var lifestyleProgress:ProgressBar

    var lifestyleRecyclerView: RecyclerView? = null

    var list = ArrayList<String>()
    var Adapter: WallpaperAdapter? = null
    var reference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifestyle)

        lifestyleProgress=findViewById(R.id.LifestyleProgress)

        setUpTopChartsRecyclerView()

        supportActionBar?.hide()


    }

    private fun setUpTopChartsRecyclerView() {

        val Adapter= WallpaperAdapter(list, this.applicationContext)

        lifestyleRecyclerView=findViewById(R.id.recyclerLifestyle)

        lifestyleRecyclerView?.adapter = Adapter

        lifestyleRecyclerView?.layoutManager = GridLayoutManager(this, 2)

        reference = FirebaseDatabase.getInstance().getReference("unixpaper_images").child("lifestyle")

        lifestyleRecyclerView?.setHasFixedSize(true)


        getData()

    }


    private fun getData() {
        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (shot in snapshot.children) {
                    val data = shot.value.toString()
                    list.add(data)
                }
                lifestyleRecyclerView?.layoutManager = GridLayoutManager(this@LifestyleActivity, 2)
                Adapter = WallpaperAdapter(list, this@LifestyleActivity)
                lifestyleRecyclerView?.adapter = Adapter
                lifestyleProgress.visibility=View.GONE

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@LifestyleActivity, "Error: " + error.message, Toast.LENGTH_SHORT).show()

            }
        })
    }

}