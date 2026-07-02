package com.example.hellokmp.presentation

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DataStoreViewModel(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val counterKey = intPreferencesKey("counter")

    val counter: StateFlow<Int> = dataStore.data
        .map { preferences ->
            preferences[counterKey] ?: 1
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 1
        )

    fun increment() {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                val current = preferences[counterKey] ?: 1
                preferences[counterKey] = current + 1
            }
        }
    }
}
