package com.example.simpleexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var clapButton : Button
    private lateinit var rainButton : Button
    private lateinit var trainButton : Button
    private lateinit var fileButton : Button

    private lateinit var checkLoop : CheckBox

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var mRunnable: Runnable
    private lateinit var mSeekBar : SeekBar

    private val DEFAULT_LISTENER = View.OnClickListener() {
        Toast.makeText(this, "Not implemented button yet!", Toast.LENGTH_SHORT).show()
    }

    private val mMediaPlayerWrapper = MediaPlayerWrapper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clapButton = findViewById(R.id.btnClap)
        rainButton = findViewById(R.id.btnRain)
        trainButton = findViewById(R.id.btnTrain)
        fileButton = findViewById(R.id.btnFile)

        checkLoop = findViewById(R.id.cbLoop)
        mSeekBar = findViewById(R.id.sbProgress)

        mRunnable = Runnable {
            mSeekBar.progress = mMediaPlayerWrapper.getCurrentPosition()
            handler.postDelayed(mRunnable, 1000)
        }
        handler.postDelayed(mRunnable, 1000)

        checkLoop.setOnClickListener {
            mMediaPlayerWrapper.setLooping(checkLoop.isChecked)
        }

        clapButton.setOnClickListener{
            mMediaPlayerWrapper.play("Clap")
            initializeSeekbar()
        }

        rainButton.setOnClickListener{
            mMediaPlayerWrapper.play("Rain")
            initializeSeekbar()
        }

        trainButton.setOnClickListener {
            mMediaPlayerWrapper.play("Train")
            initializeSeekbar()
        }
        fileButton.setOnClickListener(DEFAULT_LISTENER)
    }

    private fun initializeSeekbar() {
        mSeekBar = findViewById(R.id.sbProgress)
        mSeekBar.max = mMediaPlayerWrapper.getDuration()
        mSeekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{

                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    val tvProgress = findViewById<TextView>(R.id.tvProgress)
                    tvProgress.text = (ceil((progress.toDouble() / 1000))).toInt().toString()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}

            }
        )
    }
}