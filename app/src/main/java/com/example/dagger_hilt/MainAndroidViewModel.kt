package com.example.dagger_hilt

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.dagger_hilt.repository.BaseRepository
import com.example.dagger_hilt.util.Coroutines

class MainAndroidViewModel : AndroidViewModel {

    private val  baseRepository : BaseRepository

    @ViewModelInject
    constructor(baseRepository : BaseRepository, application: Application) : super(application) {
        this.baseRepository = baseRepository
    }

    fun getInstance() : String {
        return this.toString()
    }

    fun getRepositoryInstance() : String {
        return baseRepository.giveRepository()
    }

    fun update(booleanKey : Boolean) {
        Coroutines.io(this@MainAndroidViewModel) {
            baseRepository.update(booleanKey)
        }
    }

    fun update(stringKey : String) {
        Coroutines.io(this@MainAndroidViewModel) {
            baseRepository.update(stringKey)
        }
    }

    fun update(integerKey : Int) {
        Coroutines.io(this@MainAndroidViewModel) {
            baseRepository.update(integerKey)
        }
    }

    fun update(doubleKey : Double) {
        Coroutines.io(this@MainAndroidViewModel) {
            baseRepository.update(doubleKey)
        }
    }

    fun update(longKey : Long) {
        Coroutines.io(this@MainAndroidViewModel) {
            baseRepository.update(longKey)
        }
    }

    fun observeBoolean() : LiveData<Boolean> {
        return baseRepository.getBoolean().asLiveData(/*viewModelScope.coroutineContext*/)
    }

    fun observeString() : LiveData<String> {
        return baseRepository.getString().asLiveData(/*viewModelScope.coroutineContext*/)
    }

    fun observeInt() : LiveData<Int> {
        return baseRepository.getInteger().asLiveData(/*viewModelScope.coroutineContext*/)
    }

    fun observeDouble() : LiveData<Double> {
        return baseRepository.getDouble().asLiveData(/*viewModelScope.coroutineContext*/)
    }

    fun observeLong() : LiveData<Long> {
        return baseRepository.getLong().asLiveData(/*viewModelScope.coroutineContext*/)
    }
}