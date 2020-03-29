package com.example.koin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.koin.MainViewModel
import com.example.koin.R
import com.example.koin.view.fragment.AddFragment
import com.example.koin.view.fragment.MainFragment
import com.example.koin.view.fragment.UpdateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // Lazy injected MainViewModel
    val mainViewModel : MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            callMainFragment()
        }
    }

    private fun callMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commitNow()
    }

    fun callAddFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, AddFragment.newInstance())
            .addToBackStack(
                AddFragment.getTag())
            .commit()
    }

    fun callUpdateFragment() {
        UpdateFragment
            .newInstance()
            .show(
                supportFragmentManager.beginTransaction(),
                UpdateFragment.getTag()
            )
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
        }
        else {
            supportFragmentManager.popBackStack()
        }
    }
}
