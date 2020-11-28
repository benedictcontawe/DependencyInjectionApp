package com.example.koin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.koin.MainAndroidViewModel
import com.example.koin.MainViewModel
import com.example.koin.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // Lazy injected MainViewModel
    private val mainViewModel : MainViewModel by viewModel<MainViewModel>()
    private val mainAndroidViewModel : MainAndroidViewModel by viewModel<MainAndroidViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
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
