package com.sunayanpradhan.unixpaper.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import com.sunayanpradhan.unixpaper.R
import com.sunayanpradhan.unixpaper.activities.*
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext


class SliderAdapter(var images: IntArray,private val context: Context) : SliderViewAdapter<SliderAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_layout, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(viewHolder: Holder, position: Int) {
        Glide.with(context).load(images[position]).into(viewHolder.imageView)


        viewHolder.imageView.setOnClickListener {


            when(true){
                position==0->{
                    var intent= Intent(this.context,TrendingActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
                position==1->{
                    var intent= Intent(this.context,TopchartsActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
                position==2->{
                    var intent= Intent(this.context,CreativeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
                position==3->{
                    var intent= Intent(this.context,LifestyleActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
                position==4->{
                    var intent= Intent(this.context,QuietCalmActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
                position==5->{
                    var intent= Intent(this.context,EditorsChoiceActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }

            }




        }




    }

    override fun getCount(): Int {
        return images.size
    }

    inner class Holder(itemView: View) : ViewHolder(itemView) {

        var imageView: ImageView = itemView.findViewById(R.id.image_view)

    }
}