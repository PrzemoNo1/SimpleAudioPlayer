package com.example.simpleexample

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    enum class Sound {
        CLAP, RAIN, TRAIN, USER
    }

    private lateinit var clapButton : Button
    private lateinit var rainButton : Button
    private lateinit var trainButton : Button
    private lateinit var fileButton : Button

    private lateinit var checkLoop : CheckBox
    private lateinit var tvProgress : TextView
    private lateinit var tvRemain : TextView

    private var mMediaPlayer: MediaPlayer? = null

    private val DEFAULT_LISTENER = View.OnClickListener() {
        Toast.makeText(this, "Not implemented button yet!", Toast.LENGTH_SHORT).show()
    }

    private var sound : Sound? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clapButton = findViewById<Button>(R.id.btnClap)
        rainButton = findViewById<Button>(R.id.btnRain)
        trainButton = findViewById<Button>(R.id.btnTrain)
        fileButton = findViewById<Button>(R.id.btnFile)

        checkLoop = findViewById<CheckBox>(R.id.cbLoop)
        tvProgress = findViewById<TextView>(R.id.tvProgress)
        tvRemain = findViewById<TextView>(R.id.tvRemain)

        clapButton.setOnClickListener{
            if (mMediaPlayer == null) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.clapping)
                setOnCompleteListener()
                mMediaPlayer?.start()
                sound = Sound.CLAP
            } else {
                if (sound?.equals(Sound.CLAP) == true && mMediaPlayer?.isPlaying() == true) {
                    mMediaPlayer?.pause()
                } else {
                    mMediaPlayer?.start()
                }
            }
        }

        rainButton.setOnClickListener{
            if (mMediaPlayer == null) {
                mMediaPlayer = MediaPlayer.create(this, R.raw.rain)
                setOnCompleteListener()
                mMediaPlayer?.start()
                sound = Sound.RAIN
            } else {
                if (sound?.equals(Sound.RAIN) == true && mMediaPlayer?.isPlaying() == true) {
                    mMediaPlayer?.pause()
                } else {
                    mMediaPlayer?.start()
                }
            }
        }

        trainButton.setOnClickListener(DEFAULT_LISTENER)
        fileButton.setOnClickListener(DEFAULT_LISTENER)
    }

    private fun setOnCompleteListener() {
        mMediaPlayer?.setOnCompletionListener {
            mMediaPlayer?.reset()
            mMediaPlayer?.release()
            mMediaPlayer = null
        }
    }
}