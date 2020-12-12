package com.example.dagger_hilt.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.dagger_hilt.MainAndroidViewModel
import com.example.dagger_hilt.MainViewModel
import com.example.dagger_hilt.R
import com.example.dagger_hilt.databinding.MainBinder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private val TAG : String = MainActivity::class.java.simpleName
        private lateinit var binding : MainBinder
    }

    private val mainViewModel : MainViewModel by viewModels()
    private val mainAndroidViewModel : MainAndroidViewModel by viewModels() //activityViewModels()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this@MainActivity,R.layout.activity_main)
        if (savedInstanceState == null) {
            binding.setViewModel(mainViewModel)
            binding.setAndroidViewModel(mainAndroidViewModel)
            binding.setLifecycleOwner(this@MainActivity)
            observeBoolean()
            observeString()
            observeInt()
            observeDouble()
            observeLong()
        }
        binding.buttonBoolean.setOnClickListener(this@MainActivity)
        binding.buttonString.setOnClickListener(this@MainActivity)
        binding.buttonInteger.setOnClickListener(this@MainActivity)
        binding.buttonDouble.setOnClickListener(this@MainActivity)
        binding.buttonLong.setOnClickListener(this@MainActivity)
        Log.d(TAG,"MainViewModel Instance ${binding.getViewModel()?.getInstance()}")
        Log.d(TAG,"MainAndroidViewModel Instance ${binding.getAndroidViewModel()?.getInstance()}")
        Log.d(TAG,"MainViewModel Repository Instance ${binding.getViewModel()?.getRepositoryInstance()}")
        Log.d(TAG,"MainAndroidViewModel Repository Instance ${binding.getAndroidViewModel()?.getRepositoryInstance()}")
    }

    //region Observer Methods
    private fun observeBoolean() {
        binding.getAndroidViewModel()?.observeBoolean()?.observe(this, object : Observer<Boolean> {
            override fun onChanged(value : Boolean?) {
                Log.d(TAG,"observeBoolean() $value")
                binding.labelBoolean.setText(value.toString())
            }
        })
    }

    private fun observeString() {
        binding.getAndroidViewModel()?.observeString()?.observe(this, object : Observer<String> {
            override fun onChanged(value : String?) {
                Log.d(TAG,"observeString() $value")
                binding.labelString.setText(value.toString())
            }
        })
    }

    private fun observeInt() {
        binding.getAndroidViewModel()?.observeInt()?.observe(this, object : Observer<Int> {
            override fun onChanged(value : Int?) {
                Log.d(TAG,"observeInt() $value")
                binding.labelInteger.setText(value.toString())
            }
        })
    }

    private fun observeDouble() {
        binding.getAndroidViewModel()?.observeDouble()?.observe(this, object : Observer<Double> {
            override fun onChanged(value : Double?) {
                Log.d(TAG,"observeInt() $value")
                binding.labelDouble.setText(value.toString())
            }
        })
    }

    private fun observeLong() {
        binding.getAndroidViewModel()?.observeLong()?.observe(this, object : Observer<Long> {
            override fun onChanged(value : Long?) {
                Log.d(TAG,"observeInt() $value")
                binding.labelLong.setText(value.toString())
            }
        })
    }
    //endregion
    override fun onClick(view : View) {
        when(view) {
            binding.buttonBoolean -> {
                binding.getAndroidViewModel()?.update(
                    binding.checkBoxBoolean.isChecked()
                )
            }
            binding.buttonString -> {
                binding.getAndroidViewModel()?.update(
                    binding.editTextString.getText().toString()
                )
            }
            binding.buttonInteger -> {
                binding.getAndroidViewModel()?.update(
                    binding.editTextInteger.getText().toString().toInt()
                )
            }
            binding.buttonDouble -> {
                binding.getAndroidViewModel()?.update(
                    binding.editTextDouble.getText().toString().toDouble()
                )
            }
            binding.buttonLong -> {
                binding.getAndroidViewModel()?.update(
                    binding.editTextLong.getText().toString().toLong()
                )
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}