package com.example.recyclerjank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerjank.databinding.ActivityMainBinding
import com.example.recyclerjank.pager.ViewCache

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RootAdapter(AsyncDifferConfig.Builder(IconServiceDiffCallback).build())
        binding.recyclerView.adapter = adapter
        adapter.submitList(MockDataFactory.getData(this))

    }

    override fun onBackPressed() {
        super.onBackPressed()
        ViewCache.clear()
        finish()
    }
}