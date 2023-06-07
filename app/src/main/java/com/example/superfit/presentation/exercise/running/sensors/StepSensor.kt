package com.example.superfit.presentation.exercise.running.sensors

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import com.example.superfit.presentation.common.sensors.AndroidSensor

class StepSensor(
    context: Context
) : AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_STEP_COUNTER,
    sensorType = Sensor.TYPE_STEP_COUNTER
)