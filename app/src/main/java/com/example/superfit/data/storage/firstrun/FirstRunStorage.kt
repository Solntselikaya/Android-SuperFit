package com.example.superfit.data.storage.firstrun

interface FirstRunStorage {
    companion object {
        const val FIRST_RUN_KEY = "firstRun"
    }

    fun saveFirstRun()

    fun getFirstRun(): Boolean

    fun deleteFirstRun()
}