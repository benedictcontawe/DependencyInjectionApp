package com.example.koin.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.koin.MainAndroidViewModel
import com.example.koin.MainViewModel
import com.example.koin.R
import com.example.koin.model.web.CountryResponseModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // Lazy injected MainViewModel
    val mainViewModel : MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.requestCountry().observe(this, object : Observer<List<CountryResponseModel>> {
            override fun onChanged(list : List<CountryResponseModel>) {
                text_view_result.text = list
                    .map {
                        Log.d("MainActivity",it.Name?:"")
                        it.Name
                    }.toString()
            }
        })
    }
}
