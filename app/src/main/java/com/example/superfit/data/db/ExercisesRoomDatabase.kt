package com.example.superfit.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.superfit.data.db.dao.ExercisesDao
import com.example.superfit.data.db.entities.ExerciseEntity

@Database(entities = [ExerciseEntity::class], version = 1)
abstract class ExercisesRoomDatabase : RoomDatabase() {

    abstract fun collectionDao(): ExercisesDao

}