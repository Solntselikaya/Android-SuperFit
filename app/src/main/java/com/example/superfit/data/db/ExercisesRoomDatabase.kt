package com.example.superfit.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.superfit.common.Constants.DATABASE_NAME
import com.example.superfit.data.db.dao.ExercisesDao
import com.example.superfit.data.db.entities.ExerciseEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [ExerciseEntity::class], version = 1)
abstract class ExercisesRoomDatabase : RoomDatabase() {
    abstract fun exercisesDao(): ExercisesDao
}