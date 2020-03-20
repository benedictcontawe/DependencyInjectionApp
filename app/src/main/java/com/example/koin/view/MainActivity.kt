package com.example.koin.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.koin.R
import com.example.koin.presenter.CustomPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    // Lazy injected CustomPresenter
    val customPresenter : CustomPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        try {
            //Toast.makeText(this,customPresenter.sayHello(),Toast.LENGTH_SHORT).show()
            text_view_result.setText("${customPresenter.sayHello()}")
            Log.d("MainActivity",customPresenter.sayHello().toString())
        } catch (ex : Exception) {
            text_view_result.setText("Error! ${ex.message}")
            Log.e("MainActivity",ex.toString())
        }
    }
}
