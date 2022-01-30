package com.sunayanpradhan.unixpaper.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sunayanpradhan.unixpaper.R
import com.sunayanpradhan.unixpaper.activities.DownloadActivity

class WallpaperAdapter(private val list: ArrayList<String>, private val context: Context) : RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {


        val view: View = LayoutInflater.from(context).inflate(R.layout.design_layout, parent, false)
        return WallpaperViewHolder(view)


    }

    @SuppressLint("RecyclerView")
    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {

        Glide.with(context.applicationContext).load(list[position]).into(holder.imageView)
      holder.itemView.setOnClickListener {
          val intent = Intent(context, DownloadActivity::class.java)
          intent.putExtra(PHOTO, list[position])
          intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
          context.startActivity(intent)

      }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class WallpaperViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.design_image)


    }

    companion object {

        const val PHOTO = "PHOTO"
    }

}
