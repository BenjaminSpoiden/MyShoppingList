package com.ben.myshoppinglist.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "shopping_list_data_store")

class UserPreferences(context: Context) {

    private val appContext = context.applicationContext
    private val dataStore: DataStore<Preferences> = appContext.dataStore


    suspend fun onSaveAuthToken(accessToken: String) {
        dataStore.edit {
            it[AUTH_TOKEN_KEY] = accessToken
        }
    }

    val accessTokenFlow: Flow<String?>
        get() = dataStore.data.map {
            it[AUTH_TOKEN_KEY]
        }

    companion object {
        private val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token")
    }

}