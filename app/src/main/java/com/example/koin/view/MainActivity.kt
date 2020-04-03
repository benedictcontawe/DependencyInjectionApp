package com.example.koin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.koin.LoadingState
import com.example.koin.R
import com.example.koin.UserViewModel
import com.example.koin.view.fragment.EnterMandatoryFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel.data.observe(this, Observer {
            // Populate the UI
            // Todo: Populate the recyclerView here
            it.forEach { githubUser ->
                Toast.makeText(baseContext, githubUser.login, Toast.LENGTH_SHORT).show()
                Log.e("MainActivity",githubUser.login)
            }
        })

        userViewModel.loadingState.observe(this, Observer {
            when (it.status) {
                // Observe the loading state
                LoadingState.Status.FAILED -> Toast.makeText(baseContext, it.msg, Toast.LENGTH_SHORT).show()
                LoadingState.Status.RUNNING -> Toast.makeText(baseContext, "Loading", Toast.LENGTH_SHORT).show()
                LoadingState.Status.SUCCESS -> Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun callCreateAccountFragment() {
        /*
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, EnterMandatoryFragment.newInstance())
            .commitNow()
        */
        val fragmentManager = supportFragmentManager
        fragmentManager.apply {
            val transaction = this.beginTransaction()
            transaction.replace(R.id.container, EnterMandatoryFragment.newInstance())
            transaction.commit()
        }
    }
}
