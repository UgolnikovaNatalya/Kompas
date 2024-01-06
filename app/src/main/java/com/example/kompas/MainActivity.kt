package com.example.kompas

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kompas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SensorEventListener {
    private val vb by viewBinding(
        ActivityMainBinding::bind,
        R.id.main_root
    )

    lateinit var picture : ImageView
    lateinit var sm : SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        picture = vb.compassPic
        sm = getSystemService(SENSOR_SERVICE) as SensorManager

    }

    override fun onResume() {
        super.onResume()
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME)

    }

    override fun onPause() {
        super.onPause()
        sm.unregisterListener(this)
    }
    override fun onSensorChanged(event: SensorEvent?) {
        var degree = Math.round(event!!.values[0])
        var current_degree = degree

        var ra = RotateAnimation(degree.toFloat(),
            (-degree).toFloat(), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        ra.duration = 210
        ra.fillAfter = true
        picture.startAnimation(ra)

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}