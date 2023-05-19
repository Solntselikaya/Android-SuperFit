package com.example.superfit.data.storage.firstrun

import android.content.Context
import com.example.superfit.data.storage.firstrun.FirstRunStorage.Companion.FIRST_RUN_KEY

class FirstRunSharedPreferencesStorage(context: Context) : FirstRunStorage {
    companion object {
        const val USER_SHARED_PREFS_NAME = "userSharedPreferences"
    }

    private val sharedPreferences = context.getSharedPreferences(
        USER_SHARED_PREFS_NAME,
        Context.MODE_PRIVATE
    )

    override fun saveFirstRun() {
        sharedPreferences.edit().putBoolean(FIRST_RUN_KEY, true).apply()
    }

    override fun getFirstRun(): Boolean {
        return sharedPreferences.getBoolean(FIRST_RUN_KEY, false)
    }

    override fun deleteFirstRun() {
        sharedPreferences.edit().remove(FIRST_RUN_KEY).apply()
    }
}