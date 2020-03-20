package com.example.koin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.koin.MainViewModel
import com.example.koin.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // Lazy injected MainViewModel
    val mainViewModel : MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.sayHello().observe(this, object : Observer<String> {
            override fun onChanged(data : String?) {
                text_view_result.setText("${data}")
            }
        })
    }
}
