package com.example.dependencyinjectionapp.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.dependencyinjectionapp.model.CustomModel
import com.example.dependencyinjectionapp.room.CustomEntity

public object ConvertList {
    private fun toListModel(customEntity: List<CustomEntity>) : MutableList<CustomModel> {
        val itemList : MutableList<CustomModel> = mutableListOf<CustomModel>()
        customEntity.map {
            itemList.add(
                CustomModel(it.id?:0, it.name?:"")
            )
        }
        return itemList
    }

    public fun toLiveDataListModel(localList : LiveData<List<CustomEntity>>) : LiveData<MutableList<CustomModel>> {
        return Transformations.map<
                List<CustomEntity>, //localList Data Type
                MutableList<CustomModel> //toListModel List Data Type
                >(
            localList) { listEntity ->
            toListModel(listEntity)
        }
    }

    public fun toEntity(customModel : CustomModel) : CustomEntity {
        return when(customModel.id) {
            Constants.NEGATIVE_ONE -> {
                CustomEntity(
                    null,
                    customModel.name ?:"",
                    customModel.icon ?:0
                )
            }
            else -> {
                CustomEntity(
                    customModel.id,
                    customModel.name ?:"",
                    customModel.icon ?:0
                )
            }
        }
    }
}