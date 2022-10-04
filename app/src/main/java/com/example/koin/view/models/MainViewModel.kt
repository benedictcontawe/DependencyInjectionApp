package com.example.koin.view.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.koin.repository.BaseRepository
import com.example.koin.util.Coroutines
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

public class MainViewModel : ViewModel, KoinComponent {

    companion object {
        private val TAG : String = MainViewModel::class.java.getSimpleName()
    }

    private val repository : BaseRepository by inject()

    constructor() {

    }

    fun getInstance() : String {
        return this.toString()
    }

    fun getRepositoryInstance() : String {
        return repository.giveRepository()
    }

    fun update(booleanKey : Boolean) {
        Coroutines.io(this@MainViewModel) {
            repository.update(booleanKey)
        }
    }

    fun update(stringKey : String) {
        Coroutines.io(this@MainViewModel) {
            repository.update(stringKey)
        }
    }

    fun update(integerKey : Int) {
        Coroutines.io(this@MainViewModel) {
            repository.update(integerKey)
        }
    }

    fun update(doubleKey : Double) {
        Coroutines.io(this@MainViewModel) {
            repository.update(doubleKey)
        }
    }

    fun update(longKey : Long) {
        Coroutines.io(this@MainViewModel) {
            repository.update(longKey)
        }
    }

    fun observeBoolean() : LiveData<Boolean> {
        return repository.getBoolean().asLiveData(/*viewModelScope.coroutineContext*/)
    }

    fun observeString() : LiveData<String> {
        return repository.getString().asLiveData(/*viewModelScope.coroutineContext*/)
    }

    fun observeInt() : LiveData<Int> {
        return repository.getInteger().asLiveData(/*viewModelScope.coroutineContext*/)
    }

    fun observeDouble() : LiveData<Double> {
        return repository.getDouble().asLiveData(/*viewModelScope.coroutineContext*/)
    }

    fun observeLong() : LiveData<Long> {
        return repository.getLong().asLiveData(/*viewModelScope.coroutineContext*/)
    }

    override fun onCleared() {
        super.onCleared()
    }
}