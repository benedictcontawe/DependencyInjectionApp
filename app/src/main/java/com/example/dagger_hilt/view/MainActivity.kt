package com.example.dagger_hilt.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.dagger_hilt.MainAndroidViewModel
import com.example.dagger_hilt.MainViewModel
import com.example.dagger_hilt.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val  mainViewModel : MainViewModel by viewModels()
    private val mainAndroidViewModel : MainAndroidViewModel by viewModels()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.sayHello().observe(this, object : Observer<String> {
            override fun onChanged(data : String?) {
                text_view_result_view_model.setText("${data}")
            }
        })

        mainAndroidViewModel.sayHello().observe(this, object : Observer<String> {
            override fun onChanged(data : String?) {
                text_view_result_android_view_model.setText("${data}")
            }
        })
    }
}