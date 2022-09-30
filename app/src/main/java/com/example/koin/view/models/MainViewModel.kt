package com.example.koin.view.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcomponentsapp.NasaRequestModel
import com.example.jetpackcomponentsapp.NasaResponseModel
import com.example.koin.model.NasaHolderModel
import com.example.koin.repository.BaseRepository
import com.example.koin.util.Constants
import com.example.koin.util.Coroutines
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
public class MainViewModel : ViewModel {

    companion object {
        private val TAG : String = MainViewModel::class.java.getSimpleName()
    }

    private val repository : BaseRepository
    private val list : MutableList<NasaHolderModel> by lazy(LazyThreadSafetyMode.NONE, initializer = {
        mutableListOf<NasaHolderModel>()
    })

    private val liveList : MutableLiveData<List<NasaHolderModel>> by lazy(LazyThreadSafetyMode.NONE, initializer = {
        MutableLiveData<List<NasaHolderModel>>()
    })

    @Inject
    constructor(repository : BaseRepository) {
        this.repository = repository
    }

    fun getInstance() : String {
        return this.toString()
    }

    fun getRepositoryInstance() : String {
        return repository.giveRepository()
    }

    public fun requestAPOD() { Coroutines.default(this@MainViewModel, {
        val request : NasaRequestModel = NasaRequestModel(Constants.API_KEY, list.size + 5)
        val responseList : List<NasaResponseModel> = repository.getAPOD(request)
        list.clear()
        Log.d(TAG, "getAPOD() size ${responseList.size}")
        responseList.forEach { response -> Log.d(TAG, "Response $response")
            list.add(NasaHolderModel(list.size + 1, response))
        }
        liveList.postValue(list.reversed())
    } ) }

    public fun observeAPOD() : LiveData<List<NasaHolderModel>> {
        return liveList
    }

    override fun onCleared() {
        super.onCleared()
    }
}