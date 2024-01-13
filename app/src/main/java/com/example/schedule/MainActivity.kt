package com.example.schedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.schedule.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentHost.id, RootFragment.newInstance())
            .commit()
    }
}