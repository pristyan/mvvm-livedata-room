package com.chandra.first.module.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.chandra.first.R
import com.chandra.first.database.entity.Photo
import kotlinx.android.synthetic.main.photo_list_item.view.*

class PlacePhotoAdapter: PagerAdapter() {

    private var data: List<Photo> = emptyList()

    fun setPhotos(data: List<Photo>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val itemView = inflater.inflate(R.layout.photo_list_item, container, false)
        Glide.with(itemView).load(data[position].url).into(itemView.imgPhoto)
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}