package com.khidrew.pagination

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.khidrew.pagination.core.BaseViewModel
import com.khidrew.pagination.databinding.ActivityNetworkBinding
import com.khidrew.pagination.view.adapters.CatsAdapter
import com.khidrew.pagination.view.adapters.CatsLoadingStateAdapter

abstract class BaseActivity : AppCompatActivity() {
   abstract val viewModel:BaseViewModel
   lateinit var binding:ActivityNetworkBinding
   val adapter by lazy { CatsAdapter() }


   fun initAdapter(isMediator:Boolean){

      binding.catsrv.adapter = adapter.withLoadStateHeaderAndFooter(
         footer = CatsLoadingStateAdapter{adapter.retry()},
         header= CatsLoadingStateAdapter{adapter.retry()}
      )
      adapter.addLoadStateListener {loadState ->

         val refreshState = if(isMediator) loadState.mediator?.refresh else loadState.source.refresh

         binding.catsrv.isVisible = refreshState is LoadState.NotLoading
         binding.loading.isVisible = refreshState is LoadState.Loading
         binding.btnRetry.isVisible = refreshState is LoadState.Error
         handleError(loadState)
      }

      binding.btnRetry.setOnClickListener{
         adapter.retry()
      }
   }

   private fun handleError(loadState: CombinedLoadStates) {
      val errorState = loadState.source.append as? LoadState.Error
         ?: loadState.source.prepend as? LoadState.Error

      errorState?.let {
         Toast.makeText(this, "${it.error}", Toast.LENGTH_LONG).show()
      }
   }
}