package com.example.weather

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.api.WeatherService.apiService
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.repository.MainRepository
import com.example.weather.viewModel.MainViewModel
import com.example.weather.viewModel.ViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        val toolbarTop: androidx.appcompat.widget.Toolbar = binding.toolbar
        setSupportActionBar(toolbarTop)
        work()
    }



    private fun work() {


        val recyclerView = binding.recycleView
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = MainAdapter(this, this)
        recyclerView.adapter = mAdapter

        val repository = MainRepository(apiService)

        viewModel =
            ViewModelProvider(this, ViewModelFactory(repository)).get(MainViewModel::class.java)


        binding.search.setOnClickListener {
            binding.cityName.visibility = View.VISIBLE
            binding.search1.visibility = View.VISIBLE
            binding.search.visibility = View.GONE


        }
        binding.search1.setOnClickListener {
            binding.cityName.visibility = View.GONE
            binding.search1.visibility = View.GONE
            binding.search.visibility = View.VISIBLE
            val text1 = binding.cityName.text
            if (NetworkUtils.isNetworkConnected(this)) {


                viewModel.getWeather(text1, "metric", "4cb0327c665c78d7fc28d031b0e9c9c1")
                viewModel.weather.observe(this, Observer {
                    if (it.isSuccessful) {
                        it.body()?.let {

                            mAdapter.updateNews(it)

                            Log.d("okk", it.weather.toString())
                            Log.d("response", "done")
                        }
                    } else {
                        Log.d("response", "fail")
                    }
                })


            } else {
                Toast.makeText(this, "Something is wrong", Toast.LENGTH_LONG).show()
            }

            binding.cityName.text.clear()


            val view = this.currentFocus
            if (view != null) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }

        }
    }


}

