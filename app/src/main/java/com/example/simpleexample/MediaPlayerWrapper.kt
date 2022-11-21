package com.example.simpleexample

import android.content.Context
import android.media.MediaPlayer

class MediaPlayerWrapper(context : Context) {
    enum class Sound {
        NOT_USED, CLAP, RAIN
    }

    private var mChosenSound = Sound.NOT_USED
    private var mCurrentSound = Sound.NOT_USED

    private val mContext = context
    private var mMediaPlayer: MediaPlayer? = null

    private var mCreator = Runnable {}

    fun play(sound : String) {
        when (sound) {
            "Clap" -> {
                mChosenSound = Sound.CLAP
                mCreator = Runnable { createClapPlayer() }
            }
            "Rain" -> {
                mChosenSound = Sound.RAIN
                mCreator = Runnable { createRainPlayer() }
            }
            else -> {
                throw IllegalArgumentException("Wrong sound")
            }
        }

        if (mMediaPlayer == null) {
            mCreator.run()
            mMediaPlayer?.start()
            mCurrentSound = mChosenSound
        } else {
            val isIsDemandedSoundChosen = mChosenSound == mCurrentSound
            if (isIsDemandedSoundChosen) {
                if (mMediaPlayer?.isPlaying == true) {
                    mMediaPlayer?.pause()
                } else {
                    mMediaPlayer?.start()
                }
            } else {
                mMediaPlayer?.reset()
                mMediaPlayer?.release()
                mMediaPlayer = null
                mCreator.run()
                mMediaPlayer?.start()
                mCurrentSound = mChosenSound
            }
        }
    }

    private fun createRainPlayer() {
        mMediaPlayer = MediaPlayer.create(mContext, R.raw.rain)
        setOnCompleteListener()
    }

    private fun createClapPlayer() {
        mMediaPlayer = MediaPlayer.create(mContext, R.raw.clapping)
        setOnCompleteListener()
    }

    private fun setOnCompleteListener() {
        mMediaPlayer?.setOnCompletionListener {
            mMediaPlayer?.reset()
            mMediaPlayer?.release()
            mMediaPlayer = null
            mChosenSound = Sound.NOT_USED
            mCurrentSound = Sound.NOT_USED
        }
    }

}