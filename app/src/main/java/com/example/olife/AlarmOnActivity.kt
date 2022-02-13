package com.example.olife

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.olife.databinding.ActivityAlarmOnBinding
import com.example.olife.databinding.ActivityMainBinding
import com.example.olife.utils.AlarmUtils


class AlarmOnActivity : AppCompatActivity() {

    private var alarmUtils = AlarmUtils

    private lateinit var binding: ActivityAlarmOnBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmOnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.aaTurnOffAlarm.setOnClickListener {
            alarmUtils.mediaPlayer.stop()
            alarmUtils.vibe.cancel()
            onBackPressed()
        }
    }
}