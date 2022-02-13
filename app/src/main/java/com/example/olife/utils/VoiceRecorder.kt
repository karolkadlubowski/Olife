package com.example.olife.utils

import android.content.Context
import android.media.MediaRecorder
import android.os.Environment
import java.io.IOException
import android.os.ParcelFileDescriptor
import java.text.SimpleDateFormat
import java.util.*


class VoiceRecorder(private val context: Context) {
    var fileName: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private var state: Boolean = false
    var file: ParcelFileDescriptor? = null
    var output: String? = null


    fun setUp() {
        fileName = getCurrentDateTime().toString("MMddHHmmss") + ".mp3"
        output = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
            .toString() + "/" + fileName

        mediaRecorder = MediaRecorder()
        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)

    }

    fun startRecording() {
        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun stopRecording() {
        if (state) {
            mediaRecorder?.stop()
            mediaRecorder?.release()
            state = false
        }
    }

    companion object {
        fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
            val formatter = SimpleDateFormat(format, locale)
            return formatter.format(this)
        }

        fun getCurrentDateTime(): Date {
            return Calendar.getInstance().time
        }
    }
}