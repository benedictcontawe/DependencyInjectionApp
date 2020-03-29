package com.example.koin.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.koin.model.room.CustomEntity
import com.example.koin.model.CustomModel

class ConvertList {

    companion object {
        private fun toListModel(customEntity: List<CustomEntity>) : MutableList<CustomModel> {
            val itemList : MutableList<CustomModel> = mutableListOf<CustomModel>()
            customEntity.map {
                itemList.add(
                        CustomModel(it.id?:0, it.name?:"")
                )
            }
            return itemList
        }

        fun toLiveDataListModel(localList : LiveData<List<CustomEntity>>) : LiveData<MutableList<CustomModel>> {
            return Transformations.map<
                    List<CustomEntity>, //localList Data Type
                    MutableList<CustomModel> //toListModel List Data Type
                    >(
                    localList,
                    Companion::toListModel
            )
        }

        fun toEntity(customModel: CustomModel) : CustomEntity {
            return when(customModel.id) {
                null -> {
                    CustomEntity(
                            customModel.name?:"",
                            customModel.icon?:0
                    )
                }
                else -> {
                    CustomEntity(
                            customModel.id!!,
                            customModel.name?:"",
                            customModel.icon?:0
                    )
                }
            }
        }
    }
}