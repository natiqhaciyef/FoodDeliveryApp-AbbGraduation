package com.natiqhaciyef.fooddeliveryapp_abbgraduation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppPref(var context: Context) {
    private val Context.ds :DataStore<Preferences> by preferencesDataStore("info")

    companion object{
        val NAME_KEY = stringPreferencesKey("NAME")
        val COUNTER_KEY = intPreferencesKey("COUNTER")
    }

    suspend fun saveName(name: String){
        context.ds.edit {
            it[NAME_KEY] = name
        }
    }

    suspend fun saveCounter(counter: Int){
        context.ds.edit {
            it[COUNTER_KEY] = counter
        }
    }

    suspend fun readName(): String = context.ds.data.first()[NAME_KEY] ?: "Name Not Found"
    suspend fun readCounter(): Int = context.ds.data.first()[COUNTER_KEY] ?: 0

    suspend fun removeName(){
        context.ds.edit {
            it.remove(NAME_KEY)
        }
    }

}