package com.khidrew.pagination.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.khidrew.pagination.R
import com.khidrew.pagination.databinding.ItemCatBinding
import com.khidrew.pagination.databinding.ItemLoadStateBinding
import com.khidrew.pagination.databinding.ItemSeparatorBinding
import com.khidrew.pagination.model.CatListItem

class CatsAdapter : PagingDataAdapter<CatListItem, BaseViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CatListItem>() {
            override fun areItemsTheSame(oldItem: CatListItem, newItem: CatListItem): Boolean {
                return compareCat(oldItem, newItem) || compareSeparator(oldItem, newItem)
            }

            override fun areContentsTheSame(oldItem: CatListItem, newItem: CatListItem): Boolean {
                return oldItem == newItem
            }

        }

        private enum class ItemType {
            CAT, SEPARATOR
        }

        private fun compareCat(
            oldItem: CatListItem, newItem: CatListItem
        ) =
            (oldItem is CatListItem.CatItem
                    && newItem is CatListItem.CatItem
                    && oldItem.cat.id == newItem.cat.id)

        private fun compareSeparator(
            oldItem: CatListItem, newItem: CatListItem
        ) =
            (oldItem is CatListItem.SeparatorItem
                    && newItem is CatListItem.SeparatorItem
                    && oldItem.letter == newItem.letter)


    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CatListItem.CatItem -> ItemType.CAT.ordinal
            is CatListItem.SeparatorItem -> ItemType.SEPARATOR.ordinal
            null -> throw UnsupportedOperationException("UnexpectedView")
        }
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
       val item = getItem(position)
        if(item != null){
            holder.onBind(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == ItemType.CAT.ordinal) {
            CatViewHolder(ItemCatBinding.inflate(inflater, parent, false))
        } else {
            SeparatorViewHolder(ItemSeparatorBinding.inflate(inflater, parent, false))
        }
    }

    inner class CatViewHolder(private val binding: ItemCatBinding) :
        BaseViewHolder(binding) {
        override fun onBind(position: Int) {
            binding.cat.load((getItem(position) as CatListItem.CatItem).cat.url) {
                placeholder(R.mipmap.ic_launcher)
            }
        }
    }

    inner class SeparatorViewHolder(private val binding: ItemSeparatorBinding) :
        BaseViewHolder(binding) {

        fun create(view: ViewGroup): SeparatorViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val binding = ItemSeparatorBinding.inflate(inflater, view, false)
            return SeparatorViewHolder(binding)
        }

        override fun onBind(position: Int) {
            binding.title.text = (getItem(position) as CatListItem.SeparatorItem).letter
        }
    }




}