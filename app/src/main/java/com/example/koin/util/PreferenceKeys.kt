package com.example.koin.util

import androidx.datastore.preferences.core.*

public object PreferenceKeys {
    val BOOLEAN_KEY : Preferences.Key<Boolean> = booleanPreferencesKey("boolean_key")
    val STRING_KEY : Preferences.Key<String> = stringPreferencesKey("string_key")
    val INTEGER_KEY : Preferences.Key<Int> = intPreferencesKey("integer_key")
    val DOUBLE_KEY : Preferences.Key<Double> = doublePreferencesKey("double_key")
    val LONG_KEY : Preferences.Key<Long> = longPreferencesKey("long_key")
    //val LIST_MODEL_KEY : Preferences.Key<List<CustomModel>> = preferencesKey<List<CustomModel>>("list_model_key")
}