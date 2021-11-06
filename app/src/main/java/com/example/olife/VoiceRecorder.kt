package com.example.olife

import android.content.ContentValues
import android.content.Context
import android.media.MediaRecorder
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import java.io.IOException
import android.os.ParcelFileDescriptor


class VoiceRecorder(private val context: Context) {
    public var fileName: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private var state: Boolean = false
    private var recordingStopped: Boolean = false
    var audiouri: Uri? = null
    var file: ParcelFileDescriptor? = null
    public var output : String? = null


    fun setUp() {
        output = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).toString() + "/recording1.mp3"
       /* var values = ContentValues(4)
        values.put(MediaStore.Audio.Media.TITLE, fileName);
        values.put(MediaStore.Audio.Media.DATE_ADDED, (System.currentTimeMillis() / 1000));
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/mp3")
        values.put(MediaStore.Audio.Media.RELATIVE_PATH, "Music/Recordings/")

        audiouri = context.contentResolver.insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values)
        file = audiouri?.let { context.contentResolver.openFileDescriptor(it, "w") }
*/

        /*audiouri = activity.getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);
        file = getContentResolver().openFileDescriptor(audiouri, "w");*/
        //if (file != null) {
            mediaRecorder = MediaRecorder()
            mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            mediaRecorder?.setOutputFile(output)
       // }
    }

    fun startRecording() {
        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            state = true
            //Toast.makeText(, "Recording started!", Toast.LENGTH_SHORT).show()
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
        } else {
            // Toast.makeText(this, "You are not recording right now!", Toast.LENGTH_SHORT).show()
        }
    }
}