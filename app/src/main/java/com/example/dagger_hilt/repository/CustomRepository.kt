package com.example.dagger_hilt.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.dagger_hilt.util.PreferenceKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class CustomRepository : BaseRepository {

    private val dataStore  : DataStore<Preferences>

    @Inject
    constructor (dataStore  : DataStore<Preferences>) {
        this.dataStore = dataStore
    }


    override fun giveRepository() : String {
        return this.toString()
    }
    //region CRUD Operation
    override suspend fun update(booleanKey : Boolean) {
        dataStore?.edit { preference ->
            preference.set(PreferenceKeys.BOOLEAN_KEY, booleanKey)
        }
    }

    override suspend fun update(stringKey : String) {
        dataStore?.edit { preference ->
            preference.set(PreferenceKeys.STRING_KEY, stringKey)
        }
    }

    override suspend fun update(integerKey : Int) {
        dataStore?.edit { preference ->
            preference.set(PreferenceKeys.INTEGER_KEY, integerKey)
        }
    }

    override suspend fun update(doubleKey : Double) {
        dataStore?.edit { preference ->
            preference.set(PreferenceKeys.DOUBLE_KEY, doubleKey)
        }
    }

    override suspend fun update(longKey : Long) {
        dataStore?.edit { preference ->
            preference.set(PreferenceKeys.LONG_KEY, longKey)
        }
    }

    override fun getBoolean() : Flow<Boolean> {
        return dataStore?.data?.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }?.map { preference ->
            preference.get(PreferenceKeys.BOOLEAN_KEY) ?: false
        } ?: emptyFlow()
    }

    override fun getString() : Flow<String> {
        return dataStore?.data?.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }?.map { preference ->
            preference.get(PreferenceKeys.STRING_KEY) ?: "Nil"
        } ?: emptyFlow()
    }

    override fun getInteger() : Flow<Int> {
        return dataStore?.data?.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }?.map { preference ->
            preference.get(PreferenceKeys.INTEGER_KEY) ?: 0
        } ?: emptyFlow()
    }

    override fun getDouble() : Flow<Double> {
        return dataStore?.data?.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }?.map { preference ->
            preference.get(PreferenceKeys.DOUBLE_KEY) ?: 0.00
        } ?: emptyFlow()
    }

    override fun getLong() : Flow<Long> {
        return dataStore?.data?.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }?.map { preference ->
            preference.get(PreferenceKeys.LONG_KEY) ?: 0L
        } ?: emptyFlow()
    }
    //endregion
}