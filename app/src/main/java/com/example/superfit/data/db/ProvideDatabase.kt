package com.example.superfit.data.db

import android.app.Application
import androidx.room.Room
import com.example.superfit.common.Constants.DATABASE_NAME

fun provideDatabase(application: Application): ExercisesRoomDatabase {
    return Room.databaseBuilder(
        application,
        ExercisesRoomDatabase::class.java,
        DATABASE_NAME
    ).build()
}