package com.example.dependencyinjectionapp.view.models

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dependencyinjectionapp.model.CustomModel
import com.example.dependencyinjectionapp.repository.BaseRepository
import com.example.dependencyinjectionapp.util.ConvertList
import com.example.dependencyinjectionapp.util.Coroutines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
public class MainViewModel : ViewModel {

    companion object {
        private val TAG : String = MainViewModel::class.java.simpleName
    }

    private val repository : BaseRepository

    private val liveStandBy : MutableLiveData<Boolean>

    private val liveList : MutableLiveData<MutableList<CustomModel>> by lazy(LazyThreadSafetyMode.NONE, initializer = {
        MutableLiveData<MutableList<CustomModel>>()
    })

    private val liveUpdate : MutableLiveData<CustomModel> by lazy(LazyThreadSafetyMode.NONE, initializer = {
        MutableLiveData<CustomModel>()
    })

    @Inject
    constructor(repository : BaseRepository) {
        this.repository = repository
        liveStandBy = MutableLiveData(null)
    }

    fun getInstance() : String {
        return this.toString()
    }

    fun getRepositoryInstance() : String {
        return repository.giveRepository()
    }

    public fun viewDidLoad() {
        liveStandBy.setValue(true)
    }

    public fun checkIfFragmentLoaded(fragment : Fragment) { Log.d("MainViewModel","checkIfFragmentLoaded")
        Coroutines.default(this@MainViewModel, {
            while (!fragment.isVisible()) delay(100)
            viewWillAppear()
        })
    }

    public suspend fun viewWillAppear() { Log.d("MainViewModel","viewWillAppear")
        delay(500)
        liveStandBy.postValue(false)
    }

    public fun observeLoadState() : LiveData<Boolean> {
        return liveStandBy
    }

    fun setUpdate(item : CustomModel?) { Coroutines.io(this@MainViewModel) {
        if (item != null) {
            liveStandBy.postValue(true)
            liveUpdate.postValue(item)
        }
    } }

    fun observeUpdate() : LiveData<CustomModel> {
        return liveUpdate
    }

    fun insertItem(item : CustomModel) { Coroutines.io(this@MainViewModel) {
        liveStandBy.postValue(true)
        repository.insert(
            ConvertList.toEntity(item)
        )
        viewWillAppear()
    } }

    public fun updateItem(updated : String) { Coroutines.io(this@MainViewModel, {
        liveStandBy.postValue(true)
        val old : CustomModel? = liveUpdate.value
        repository.update(
            ConvertList.toEntity(CustomModel(old?.id, updated, old?.icon))
        )
        viewWillAppear()
    }) }

    fun deleteItem(item : CustomModel?) { Coroutines.io(this@MainViewModel) {
        liveStandBy.postValue(true)
        if (item != null)
            repository.delete(
                ConvertList.toEntity(item)
            )
        viewWillAppear()
    } }

    fun deleteAll() { Coroutines.io(this@MainViewModel) {
        liveStandBy.postValue(true)
        repository.deleteAll()
        viewWillAppear()
    } }

    fun observeItems() : LiveData<MutableList<CustomModel>> {
        return ConvertList.toLiveDataListModel(
            repository.getAll()
        )
    }

    override fun onCleared() {
        super.onCleared()
    }
}