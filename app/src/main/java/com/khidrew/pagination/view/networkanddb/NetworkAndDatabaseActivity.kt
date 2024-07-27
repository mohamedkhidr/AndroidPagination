package com.khidrew.pagination.view.networkanddb

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.khidrew.pagination.BaseActivity
import com.khidrew.pagination.R
import com.khidrew.pagination.databinding.ActivityNetworkBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NetworkAndDatabaseActivity : BaseActivity() {
    override val viewModel: NetworkAndDatabaseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter(true)
        lifecycleScope.launch {
            viewModel.cats.collectLatest {
                adapter.submitData(it)
            }
        }
    }
}