package com.khidrew.pagination.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.khidrew.pagination.R
import com.khidrew.pagination.databinding.ItemCatBinding
import com.khidrew.pagination.model.Cat

class CatsAdapter : PagingDataAdapter<Cat, CatsAdapter.CatViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
       val item = getItem(position)
        if(item != null){
            (holder as CatViewHolder).bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder.create(parent)
    }

    class CatViewHolder(private val binding: ItemCatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(view: ViewGroup): CatViewHolder {
                val inflater = LayoutInflater.from(view.context)
                val binding = ItemCatBinding.inflate(inflater, view, false)
                return CatViewHolder(binding)
            }
        }

        fun bind(item: Cat?) {
            binding.cat.load(item?.url) {
                placeholder(R.mipmap.ic_launcher)
            }
        }
    }

    enum class ItemType {

    }
}