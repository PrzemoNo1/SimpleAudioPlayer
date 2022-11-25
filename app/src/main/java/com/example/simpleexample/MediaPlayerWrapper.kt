package com.example.simpleexample

import android.content.Context
import android.media.MediaPlayer

class MediaPlayerWrapper(context : Context) {
    enum class Sound {
        NOT_USED, CLAP, RAIN, TRAIN
    }

    private var mChosenSound = Sound.NOT_USED
    private var mCurrentSound = Sound.NOT_USED

    private val mContext = context
    private var mMediaPlayer: MediaPlayer? = null

    private var mCreator = Runnable {}
    private var mIsLooping = false

    fun getCurrentPosition() : Int {
        val currentPosition : Int
        try {
            currentPosition = mMediaPlayer!!.currentPosition
        } catch (e : Exception) {
            return 0
        }
        return currentPosition
    }

    fun getDuration() : Int {
        return mMediaPlayer!!.duration
    }

    fun setLooping(isLooping : Boolean) {
        mMediaPlayer?.isLooping = isLooping
        mIsLooping = isLooping
    }

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
            "Train" -> {
                mChosenSound = Sound.TRAIN
                mCreator = Runnable { createTrainPlayer() }
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
        mMediaPlayer?.isLooping = mIsLooping
        setOnCompleteListener()
    }

    private fun createClapPlayer() {
        mMediaPlayer = MediaPlayer.create(mContext, R.raw.clapping)
        mMediaPlayer?.isLooping = mIsLooping
        setOnCompleteListener()
    }

    private fun createTrainPlayer() {
        mMediaPlayer = MediaPlayer.create(mContext, R.raw.train)
        mMediaPlayer?.isLooping = mIsLooping
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