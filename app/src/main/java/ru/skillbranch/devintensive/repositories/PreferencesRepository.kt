package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import android.preference.PreferenceManager
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile

object PreferencesRepository {
    private val prefs: SharedPreferences by lazy{
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun getProfile(): Profile? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun saveProfile(profile: Profile) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun putValue(pair: Pair<String, Any>) = with(prefs.edit()){
        val key = pair.first
        val value = pair.second

        when(value){
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Only primitives types can be stored in Shared Preferences")
        }

        apply()
    }
}