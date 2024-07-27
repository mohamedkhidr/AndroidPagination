package com.khidrew.pagination

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.khidrew.pagination.core.BaseViewModel
import com.khidrew.pagination.databinding.ActivityNetworkBinding
import com.khidrew.pagination.view.adapters.CatsAdapter

abstract class BaseActivity : AppCompatActivity() {
   abstract val viewModel:BaseViewModel
   lateinit var binding:ActivityNetworkBinding
   val adapter by lazy { CatsAdapter() }


   fun initAdapter(){
      binding.catsrv.adapter = adapter
   }
}