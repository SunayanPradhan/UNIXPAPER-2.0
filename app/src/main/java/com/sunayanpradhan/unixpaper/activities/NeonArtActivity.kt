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

class NeonArtActivity : AppCompatActivity() {

    lateinit var neonArtProgress:ProgressBar

    var neonartRecyclerView: RecyclerView?=null

    var list = ArrayList<String>()
    var Adapter: WallpaperAdapter? = null
    var reference: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_neon_art)

        neonArtProgress=findViewById(R.id.NeonArtProgress)

        supportActionBar?.hide()

        setUpNeonArtRecyclerView()
    }

    private fun setUpNeonArtRecyclerView() {
        val Adapter= WallpaperAdapter(list, this.applicationContext)

        neonartRecyclerView=findViewById(R.id.recyclerNeonArt)

        neonartRecyclerView?.adapter = Adapter

        neonartRecyclerView?.layoutManager = GridLayoutManager(this, 2)

        reference = FirebaseDatabase.getInstance().getReference("unixpaper_images").child("neonart")

        neonartRecyclerView?.setHasFixedSize(true)

        getData()
    }

    private fun getData() {
        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (shot in snapshot.children) {
                    val data = shot.value.toString()
                    list.add(data)
                }
                neonartRecyclerView?.layoutManager = GridLayoutManager(this@NeonArtActivity, 2)
                Adapter = WallpaperAdapter(list, this@NeonArtActivity)
                neonartRecyclerView?.adapter = Adapter
                neonArtProgress.visibility= View.GONE

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@NeonArtActivity, "Error: " + error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


}