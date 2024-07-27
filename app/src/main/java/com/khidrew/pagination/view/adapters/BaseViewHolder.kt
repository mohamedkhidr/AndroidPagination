package com.khidrew.pagination.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder(private val binding:ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(position:Int)
}