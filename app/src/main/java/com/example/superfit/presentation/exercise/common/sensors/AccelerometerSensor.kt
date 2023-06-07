package com.example.superfit.presentation.exercise.common.sensors

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import com.example.superfit.presentation.common.sensors.AndroidSensor

class AccelerometerSensor(
    context: Context
) : AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_ACCELEROMETER,
    sensorType = Sensor.TYPE_ACCELEROMETER
)