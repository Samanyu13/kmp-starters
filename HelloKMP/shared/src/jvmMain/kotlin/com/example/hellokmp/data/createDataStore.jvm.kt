package com.example.hellokmp.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun createDataStore(): DataStore<Preferences> =
    createDataStore(
        producePath = { DATASTORE_FILE_NAME }
    )
