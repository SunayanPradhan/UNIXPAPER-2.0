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

class EditorsChoiceActivity : AppCompatActivity() {

    var editorsChoiceRecyclerView: RecyclerView? = null

    var list = ArrayList<String>()
    var Adapter: WallpaperAdapter? = null
    var reference: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editors_choice)

        setUpEditorsChoiceRecyclerView()

        supportActionBar?.hide()




    }

    private fun setUpEditorsChoiceRecyclerView() {

        val Adapter= WallpaperAdapter(list, this.applicationContext)

        editorsChoiceRecyclerView=findViewById(R.id.recyclerEditorsChoice)

        editorsChoiceRecyclerView?.adapter = Adapter

        editorsChoiceRecyclerView?.layoutManager = GridLayoutManager(this, 2)

        reference = FirebaseDatabase.getInstance().getReference("unixpaper_images").child("editorschoice")

        editorsChoiceRecyclerView?.setHasFixedSize(true)


        getData()
    }

    private fun getData() {
        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (shot in snapshot.children) {
                    val data = shot.value.toString()
                    list.add(data)
                }
                editorsChoiceRecyclerView?.layoutManager = GridLayoutManager(this@EditorsChoiceActivity, 2)
                Adapter = WallpaperAdapter(list, this@EditorsChoiceActivity)
                editorsChoiceRecyclerView?.adapter = Adapter

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@EditorsChoiceActivity, "Error: " + error.message, Toast.LENGTH_SHORT).show()

            }
        })
    }

}