package cz.uhk.umteapp.utils

import android.hardware.Sensor

/**
 * Created by petrw on 01.04.2019
 * weissar.petr@gmail.com
 **/

enum class SensorValue constructor(val sensorType: Int) {

    Accelerometer(Sensor.TYPE_ACCELEROMETER),
    Gyroscope(Sensor.TYPE_GYROSCOPE),
    Magnetometer(Sensor.TYPE_MAGNETIC_FIELD),
    OrientationVector(Sensor.TYPE_ROTATION_VECTOR);

    fun getType() : Int {
        return sensorType
    }
}