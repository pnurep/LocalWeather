package com.dev.gold.localweather

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.gold.localweather.databinding.ListItemRowBinding
import com.dev.gold.localweather.model.WeatherListData

class ListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        private const val VIEW_TYPE_DEFAULT = 0
        private const val VIEW_TYPE_HEADER = VIEW_TYPE_DEFAULT + 1

    }

    var data: List<WeatherListData>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == VIEW_TYPE_HEADER) {
            val headView = LayoutInflater.from(context).inflate(R.layout.list_header, parent, false)

            return HeadViewHolder(headView)
        }

        val binding = DataBindingUtil
                .inflate<ListItemRowBinding>(LayoutInflater.from(context), R.layout.list_item_row, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ListAdapter.ViewHolder) {
            val weatherData = data?.getOrNull(position) ?: return

            holder.binding.setVariable(BR.weatherData, weatherData)
            holder.binding.executePendingBindings()
            //holder.binding.invalidateAll()
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (data?.get(position)?.title == "head")
            VIEW_TYPE_HEADER
        else
            VIEW_TYPE_DEFAULT
    }

    inner class ViewHolder(val binding: ListItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    inner class HeadViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}