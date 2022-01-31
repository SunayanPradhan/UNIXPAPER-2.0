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

class VehiclesActivity : AppCompatActivity() {

    lateinit var vehiclesProgress:ProgressBar

    var vehiclesRecyclerView: RecyclerView?=null

    var list = ArrayList<String>()
    var Adapter: WallpaperAdapter? = null
    var reference: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles)

        vehiclesProgress=findViewById(R.id.VehiclesProgress)

        supportActionBar?.hide()

        setUpVehiclesRecyclerView()

    }

    private fun setUpVehiclesRecyclerView() {

        val Adapter= WallpaperAdapter(list, this.applicationContext)

        vehiclesRecyclerView=findViewById(R.id.recyclerVehicles)

        vehiclesRecyclerView?.adapter = Adapter

        vehiclesRecyclerView?.layoutManager = GridLayoutManager(this, 2)

        reference = FirebaseDatabase.getInstance().getReference("unixpaper_images").child("vehicles")

        vehiclesRecyclerView?.setHasFixedSize(true)

        getData()
    }
    private fun getData() {
        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (shot in snapshot.children) {
                    val data = shot.value.toString()
                    list.add(data)
                }
                vehiclesRecyclerView?.layoutManager = GridLayoutManager(this@VehiclesActivity, 2)
                Adapter = WallpaperAdapter(list, this@VehiclesActivity)
                vehiclesRecyclerView?.adapter = Adapter
                vehiclesProgress.visibility= View.GONE

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@VehiclesActivity, "Error: " + error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
