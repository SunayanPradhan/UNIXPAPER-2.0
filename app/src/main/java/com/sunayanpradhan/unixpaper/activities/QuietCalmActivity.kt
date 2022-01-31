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

class QuietCalmActivity : AppCompatActivity() {

    lateinit var quietCalmProgress:ProgressBar

    var quietCalmRecyclerView: RecyclerView? = null

    var list = ArrayList<String>()
    var Adapter: WallpaperAdapter? = null
    var reference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiet_calm)

        setUpQuietCalmRecyclerView()

        supportActionBar?.hide()
    }

    private fun setUpQuietCalmRecyclerView() {
        val Adapter= WallpaperAdapter(list, this.applicationContext)

        quietCalmRecyclerView=findViewById(R.id.recyclerQuietCalm)

        quietCalmRecyclerView?.adapter = Adapter

        quietCalmRecyclerView?.layoutManager = GridLayoutManager(this, 2)

        reference = FirebaseDatabase.getInstance().getReference("unixpaper_images").child("quietcalm")

        quietCalmRecyclerView?.setHasFixedSize(true)


        getData()
    }

    private fun getData() {
        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (shot in snapshot.children) {
                    val data = shot.value.toString()
                    list.add(data)
                }
                quietCalmRecyclerView?.layoutManager = GridLayoutManager(this@QuietCalmActivity, 2)
                Adapter = WallpaperAdapter(list, this@QuietCalmActivity)
                quietCalmRecyclerView?.adapter = Adapter
                quietCalmProgress.visibility= View.GONE

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@QuietCalmActivity, "Error: " + error.message, Toast.LENGTH_SHORT).show()

            }
        })
    }
}