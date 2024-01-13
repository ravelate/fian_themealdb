package com.felina.fianthemealdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.felina.fianthemealdb.databinding.ActivityMainBinding
import com.felina.fianthemealdb.feature.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.fcvFragment.id, HomeFragment())
            .commit()
    }
}