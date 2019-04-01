package cz.uhk.umteapp.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import cz.uhk.umteapp.R
import cz.uhk.umteapp.utils.SensorValue
import cz.uhk.umteapp.utils.onItemSelected
import cz.uhk.umteapp.utils.toOurFormat
import kotlinx.android.synthetic.main.activity_sensor.*
import java.text.DecimalFormat

/**
 * Created by petrw on 25.03.2019
 * weissar.petr@gmail.com
 **/

class SensorActivity : AppCompatActivity(), SensorEventListener {

    val df = DecimalFormat("#.###")
    var sensorType = SensorValue.Accelerometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        // registrace k senzorům
        val manager = getSystemService(Context.SENSOR_SERVICE)
                as SensorManager

        for (value in SensorValue.values()) {
            manager.registerListener(this,
                manager.getDefaultSensor(value.getType()),
                SensorManager.SENSOR_DELAY_FASTEST)
        }

        val sensorAdapter = ArrayAdapter(this,
            android.R.layout.simple_dropdown_item_1line,
            android.R.id.text1,
            SensorValue.values())
        spinner.adapter = sensorAdapter

        spinner.onItemSelected<SensorValue> {
            sensorType = it
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {

        event?.let {
            if (it.sensor.type == sensorType.getType()) {

                xTextView.text = it.values[0].toOurFormat() // X
                yTextView.text = df.format(it.values[1]) // Y
                zTextView.text = df.format(it.values[2]) // Z
            }
        }
    }
}