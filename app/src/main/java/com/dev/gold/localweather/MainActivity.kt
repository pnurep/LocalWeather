package com.dev.gold.localweather

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.widget.LinearLayout
import com.dev.gold.localweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val listAdapter by lazy { ListAdapter(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        lifecycle.addObserver(mainViewModel)

        mainViewModel.weatherLiveData.observe(this, Observer {
            listAdapter.data = it
        })

        with(binding.weatherList) {
            DividerItemDecoration(this@with.context, LinearLayout.VERTICAL).apply {
                setDrawable(ContextCompat.getDrawable(this@MainActivity, R.drawable.list_divider)!!)
                this@with.addItemDecoration(this)
            }

            this.setHasFixedSize(true)
        }

        binding.mainViewModel = mainViewModel
        binding.listAdapter = listAdapter

    }

}



