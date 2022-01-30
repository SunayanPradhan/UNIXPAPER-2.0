package com.sunayanpradhan.unixpaper.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.sunayanpradhan.unixpaper.R
import com.sunayanpradhan.unixpaper.adapters.WallpaperAdapter
import java.util.ArrayList

class CreativeActivity : AppCompatActivity() {

    var creativeRecyclerView: RecyclerView? = null

    var list = ArrayList<String>()
    var Adapter: WallpaperAdapter? = null
    var reference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creative)


        setUpCreativeRecyclerView()

        supportActionBar?.hide()
    }

    private fun setUpCreativeRecyclerView() {
        val Adapter= WallpaperAdapter(list, this.applicationContext)

        creativeRecyclerView=findViewById(R.id.recyclerTopCharts)

        creativeRecyclerView?.adapter = Adapter

        creativeRecyclerView?.layoutManager = GridLayoutManager(this, 2)

        reference = FirebaseDatabase.getInstance().getReference("unixpaper_images").child("creative")

        creativeRecyclerView?.setHasFixedSize(true)


        getData()
    }

    private fun getData() {
        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (shot in snapshot.children) {
                    val data = shot.value.toString()
                    list.add(data)
                }
                creativeRecyclerView?.layoutManager = GridLayoutManager(this@CreativeActivity, 2)
                Adapter = WallpaperAdapter(list, this@CreativeActivity)
                creativeRecyclerView?.adapter = Adapter

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@CreativeActivity, "Error: " + error.message, Toast.LENGTH_SHORT).show()

            }
        })
    }


}