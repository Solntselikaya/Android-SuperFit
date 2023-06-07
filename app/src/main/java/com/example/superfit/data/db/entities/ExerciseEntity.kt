package com.example.superfit.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.superfit.common.Constants.DATABASE_NAME
import com.example.superfit.common.TrainingType
import com.example.superfit.domain.model.TrainingModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = DATABASE_NAME, primaryKeys = ["user_email", "exercise_type"])
data class ExerciseEntity(
    @ColumnInfo(name = "user_email")
    val userEmail: String,
    @ColumnInfo(name = "exercise_type")
    val exerciseType: String,
    @ColumnInfo(name = "count")
    val count: Int
)

fun ExerciseEntity.toTrainingModel(): TrainingModel {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val current = LocalDate.now().format(formatter)

    return TrainingModel(
        date = current,
        exercise = TrainingType.valueOf(exerciseType),
        repeatCount = count
    )
}