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

class NatureActivity : AppCompatActivity() {

    lateinit var natureProgress:ProgressBar

    var natureRecyclerView: RecyclerView? = null

    var list = ArrayList<String>()
    var Adapter: WallpaperAdapter? = null
    var reference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nature)
        setUpNatureRecyclerView()
        natureProgress=findViewById(R.id.NatureProgress)

        supportActionBar?.hide()


    }

    private fun setUpNatureRecyclerView() {
        val Adapter= WallpaperAdapter(list, this.applicationContext)

        natureRecyclerView=findViewById(R.id.recyclerNature)

        natureRecyclerView?.adapter = Adapter

        natureRecyclerView?.layoutManager = GridLayoutManager(this, 2)

        reference = FirebaseDatabase.getInstance().getReference("unixpaper_images").child("nature")

        natureRecyclerView?.setHasFixedSize(true)


        getData()
    }

    private fun getData() {
        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (shot in snapshot.children) {
                    val data = shot.value.toString()
                    list.add(data)
                }
                natureRecyclerView?.layoutManager = GridLayoutManager(this@NatureActivity, 2)
                Adapter = WallpaperAdapter(list, this@NatureActivity)
                natureRecyclerView?.adapter = Adapter
                natureProgress.visibility= View.GONE

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@NatureActivity, "Error: " + error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}
