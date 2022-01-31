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

class WildlifeActivity : AppCompatActivity() {

    lateinit var wildlifeProgress:ProgressBar

    var wildlifeRecyclerView: RecyclerView?=null

    var list = ArrayList<String>()
    var Adapter: WallpaperAdapter? = null
    var reference: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wildlife)

        wildlifeProgress=findViewById(R.id.WildlifeProgress)

        supportActionBar?.hide()

        setUpWildlifeRecyclerView()
    }

    private fun setUpWildlifeRecyclerView() {
        val Adapter= WallpaperAdapter(list, this.applicationContext)

        wildlifeRecyclerView=findViewById(R.id.recyclerWildlife)

        wildlifeRecyclerView?.adapter = Adapter

        wildlifeRecyclerView?.layoutManager = GridLayoutManager(this, 2)

        reference = FirebaseDatabase.getInstance().getReference("unixpaper_images").child("wildlife")

        wildlifeRecyclerView?.setHasFixedSize(true)

        getData()
    }

    private fun getData() {
        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (shot in snapshot.children) {
                    val data = shot.value.toString()
                    list.add(data)
                }
                wildlifeRecyclerView?.layoutManager = GridLayoutManager(this@WildlifeActivity, 2)
                Adapter = WallpaperAdapter(list, this@WildlifeActivity)
                wildlifeRecyclerView?.adapter = Adapter
                wildlifeProgress.visibility=View.GONE

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@WildlifeActivity, "Error: " + error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}