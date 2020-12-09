package com.chandra.first.module.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chandra.first.R
import com.chandra.first.database.entity.Place
import kotlinx.android.synthetic.main.place_list_item.view.*

class PlaceAdapter(private val callback: Callback): RecyclerView.Adapter<PlaceAdapter.Holder>() {

    private var data: List<Place> = emptyList()

    fun setData(data: List<Place>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.place_list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class Holder(superView: View): RecyclerView.ViewHolder(superView) {

        fun bind(data: Place) = with(itemView) {
            Glide.with(this).load(data.thumbnail).into(imgPlace)
            tvPlaceName.text = data.name
            setOnClickListener { callback.onPlaceClick(data) }
        }

    }

    interface Callback {
        fun onPlaceClick(data: Place)
    }

}