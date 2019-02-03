package com.dev.gold.localweather.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.widget.LinearLayout
import com.dev.gold.localweather.ListAdapter
import com.dev.gold.localweather.R
import com.dev.gold.localweather.databinding.ActivityMainBinding
import com.dev.gold.localweather.viewmodel.MainViewModel
import com.dev.gold.localweather.viewmodel.MainViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance


class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: MainViewModelFactory by instance()
    private val listAdapter: ListAdapter by instance()

    val ddd: String by instance("ddd")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
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



