package com.khidrew.pagination.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khidrew.pagination.databinding.ItemLoadStateBinding

class CatsLoadingStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CatsLoadingStateAdapter.LoadingStateViewHolder>() {


    class LoadingStateViewHolder(
        private val binding: ItemLoadStateBinding,
        private val retry: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        companion object {
            fun create(view: ViewGroup, retry: () -> Unit): LoadingStateViewHolder {
                val inflater = LayoutInflater.from(view.context)
                val binding = ItemLoadStateBinding.inflate(inflater, view, false)
                return LoadingStateViewHolder(binding, retry)
            }
        }


        fun onBind(loadState: LoadState) {
            if(loadState is LoadState.Error){
                binding.errMsg.text = loadState.error.message
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState !is LoadState.Loading
            binding.errMsg.isVisible = loadState !is LoadState.Loading
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {

        return LoadingStateViewHolder.create(parent, retry)
    }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        return holder.onBind(loadState)
    }


}