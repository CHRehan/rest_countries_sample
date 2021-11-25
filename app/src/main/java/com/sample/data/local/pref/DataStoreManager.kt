package com.sample.data.local.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@ActivityRetainedScoped
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = COUNTRIES_DATASTORE)

    companion object {

        val COUNTRIES_KEY = stringPreferencesKey("countries_json")
        val KEY_LAST_FETCH_TIME = stringPreferencesKey("key_last_fetch_time")
        private const val COUNTRIES_DATASTORE = "datastore"

    }


    //We can use these methods to store JSON String of countries.
    suspend fun saveCountriesJSONString(countriesJSON: String) {
        context.dataStore.edit {

            it[COUNTRIES_KEY] = countriesJSON


        }
    }

    suspend fun getCountriesJSONString(): String {

        return context.dataStore.data.first()[COUNTRIES_KEY] ?: ""
    }


    suspend fun saveLastFetchTime(lastFetchTime: Int) {
        context.dataStore.edit {
            it[KEY_LAST_FETCH_TIME] = lastFetchTime.toString()

        }
    }

    fun getLastFetchTime() = context.dataStore.data.map {
        it[KEY_LAST_FETCH_TIME]?.toInt() ?: 0

    }
}