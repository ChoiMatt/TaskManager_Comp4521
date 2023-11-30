package com.example.taskmanager_comp4521.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserPreference(private val context: Context) {
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserPreference")
        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
        val PROFILE_PICTURE_KEY = stringPreferencesKey("picture_uri")
    }

    val getUserName: Flow<String?> = context.dataStore.data
        .map{ preferences ->
            preferences[USER_NAME_KEY]?:""
        }
    val getThemePreference: Flow<Boolean?> = context.dataStore.data
        .map{ preferences ->
            preferences[DARK_THEME_KEY]?:false
        }

    val getProfilePicture: Flow<String?> = context.dataStore.data
        .map{ preferences ->
            preferences[PROFILE_PICTURE_KEY]?:""
        }

    suspend fun savePreference(name: String , darkTheme: Boolean , uri: String){
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
            preferences[DARK_THEME_KEY] = darkTheme
            preferences[PROFILE_PICTURE_KEY] = uri
        }
    }
}