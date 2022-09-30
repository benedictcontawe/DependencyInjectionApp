package com.example.koin.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.koin.R
import com.example.koin.databinding.MainBinder
import com.example.koin.model.NasaHolderModel
import com.example.koin.util.Coroutines
import com.example.koin.view.adapters.NasaAdapter
import com.example.koin.view.models.MainAndroidViewModel
import com.example.koin.view.models.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

public class MainActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        private val TAG : String = MainActivity::class.java.getSimpleName()

        public fun newIntent(context : Context) : Intent = Intent(context.getApplicationContext(), MainActivity::class.java)
        //.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }

    private var binder : MainBinder? = null
    private val viewModel : MainViewModel by viewModel<MainViewModel>()
    private val androidViewModel : MainAndroidViewModel by viewModel<MainAndroidViewModel>()
    private val adapter : NasaAdapter by lazy { NasaAdapter() }

    override fun onCreate(savedInstanceState : Bundle?) {
        binder = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        super.onCreate(savedInstanceState)
        binder?.setViewModel(viewModel)
        binder?.setLifecycleOwner(this@MainActivity)
    }

    override suspend fun onSetObservers(scope : CoroutineScope) {
        binder?.recyclerView?.setAdapter(adapter)
        binder?.swipeRefreshLayout?.setOnRefreshListener(this@MainActivity)
        binder?.getViewModel()?.requestAPOD()
        binder?.getViewModel()?.observeAPOD()?.observe(binder?.getLifecycleOwner()!!, object : Observer<List<NasaHolderModel>> {
            override fun onChanged(list : List<NasaHolderModel>?) {
                binder?.recyclerView?.removeAllViews()
                adapter.setItems( list )
                if (binder?.swipeRefreshLayout?.isRefreshing() == true) binder?.swipeRefreshLayout?.setRefreshing(false)
            }
        })
        getOnBackPressedDispatcher().addCallback(this@MainActivity, getHandleOnBackPressed())
    }

    override fun onRefresh() { Coroutines.main(this@MainActivity, { scope : CoroutineScope ->
        scope.launch ( block = {
            binder?.recyclerView?.removeAllViews()
            binder?.getViewModel()?.requestAPOD()
        })
    }) }
}