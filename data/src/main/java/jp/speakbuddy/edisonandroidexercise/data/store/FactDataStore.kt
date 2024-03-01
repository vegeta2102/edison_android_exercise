package jp.speakbuddy.edisonandroidexercise.data.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FactDataStore @Inject constructor(@ApplicationContext val context: Context) {
    companion object {
        val KEY_FACT_CONTENT = stringPreferencesKey("content")
        val KEY_FACT_LENGTH = intPreferencesKey("length")
    }

    private val Context.factStore by preferencesDataStore("data_store_fact")
    val data: DataStore<Preferences> = context.factStore
}