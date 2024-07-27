package com.khidrew.pagination

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.khidrew.pagination.databinding.ActivityMainBinding
import com.khidrew.pagination.view.db.DatabaseActivity
import com.khidrew.pagination.view.networkanddb.NetworkAndDatabaseActivity
import com.khidrew.pagination.view.netwrok.NetworkActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.network.setOnClickListener{
            startActivity(Intent(this, NetworkActivity::class.java))
        }
        binding.database.setOnClickListener{
            startActivity(Intent(this, DatabaseActivity::class.java))
        }
        binding.networkanddatabase.setOnClickListener{
            startActivity(Intent(this, NetworkAndDatabaseActivity::class.java))
        }

    }
}