package com.example.task3ongraph

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter


class SlideAdapter  (
    val mSliderItems: List<String>,
    val context: Context,
    ) : SliderViewAdapter<SlideAdapter.SliderAdapterVH>() {

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.custom_image_slider_layout_item , null)
        return SliderAdapterVH(view)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        Glide.with(viewHolder.itemView)
            .load(mSliderItems[position])
            .fitCenter()
            .into(viewHolder.imageViewBackground)
    }

    override fun getCount(): Int {
        return mSliderItems.size
    }

    inner class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        val imageViewBackground = itemView.findViewById<ImageView>(R.id.iv_auto_image_slider)
    }
}