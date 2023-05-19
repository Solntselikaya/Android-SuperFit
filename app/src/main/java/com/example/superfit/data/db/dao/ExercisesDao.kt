package com.example.superfit.data.db.dao

import androidx.room.*
import com.example.superfit.common.Constants.DATABASE_NAME
import com.example.superfit.common.TrainingType
import com.example.superfit.data.db.entities.ExerciseEntity

@Dao
interface ExercisesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: ExerciseEntity)

    @Query("SELECT count FROM $DATABASE_NAME WHERE user_email = :userEmail AND exercise_type = :exerciseType")
    suspend fun getExerciseRepeatCount(userEmail: String, exerciseType: TrainingType): Int

    @Update
    suspend fun update(exercise: ExerciseEntity)

    @Query("SELECT * FROM $DATABASE_NAME WHERE user_email = :userEmail")
    suspend fun getAllForUser(userEmail: String): List<ExerciseEntity>

    @Query("DELETE FROM $DATABASE_NAME WHERE user_email = :userEmail")
    suspend fun deleteAllForUser(userEmail: String)

    @Query("DELETE FROM $DATABASE_NAME")
    suspend fun deleteAll()
}