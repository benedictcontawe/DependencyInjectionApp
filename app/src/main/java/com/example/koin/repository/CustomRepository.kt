package com.example.koin.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.koin.util.PreferenceKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

public class CustomRepository : BaseRepository, KoinComponent {

    companion object {
        private val TAG : String = CustomRepository::class.java.getSimpleName()
    }

    private val context : Context by inject()
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

    override fun giveRepository() : String {
        return this.toString()
    }
    //region CRUD Operation
    override suspend fun update(booleanKey : Boolean) {
        context.dataStore.edit { preference ->
            preference.set(PreferenceKeys.BOOLEAN_KEY, booleanKey)
        }
    }

    override suspend fun update(stringKey : String) {
        context.dataStore.edit { preference ->
            preference.set(PreferenceKeys.STRING_KEY, stringKey)
        }
    }

    override suspend fun update(integerKey : Int) {
        context.dataStore.edit { preference ->
            preference.set(PreferenceKeys.INTEGER_KEY, integerKey)
        }
    }

    override suspend fun update(doubleKey : Double) {
        context.dataStore.edit { preference ->
            preference.set(PreferenceKeys.DOUBLE_KEY, doubleKey)
        }
    }

    override suspend fun update(longKey : Long) {
        context.dataStore.edit { preference ->
            preference.set(PreferenceKeys.LONG_KEY, longKey)
        }
    }

    override fun getBoolean() : Flow<Boolean> {
        return context.dataStore.data.catch { exception ->
            if (exception is Exception) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preference ->
            preference.get(PreferenceKeys.BOOLEAN_KEY) ?: false
        } ?: emptyFlow()
    }

    override fun getString() : Flow<String> {
        return context.dataStore.data.catch { exception ->
            if (exception is Exception) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preference ->
            preference.get(PreferenceKeys.STRING_KEY) ?: "Nil"
        } ?: emptyFlow()
    }

    override fun getInteger() : Flow<Int> {
        return context.dataStore.data.catch { exception ->
            if (exception is Exception) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preference ->
            preference.get(PreferenceKeys.INTEGER_KEY) ?: 0
        } ?: emptyFlow()
    }

    override fun getDouble() : Flow<Double> {
        return context.dataStore.data.catch { exception ->
            if (exception is Exception) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preference ->
            preference.get(PreferenceKeys.DOUBLE_KEY) ?: 0.00
        } ?: emptyFlow()
    }

    override fun getLong() : Flow<Long> {
        return context.dataStore.data.catch { exception ->
            if (exception is Exception) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preference ->
            preference.get(PreferenceKeys.LONG_KEY) ?: 0L
        } ?: emptyFlow()
    }
    //endregion
}