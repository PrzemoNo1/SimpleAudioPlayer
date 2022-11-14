package com.example.simpleexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var clapButton : Button
    private lateinit var rainButton : Button
    private lateinit var trainButton : Button
    private lateinit var fileButton : Button

    private lateinit var checkLoop : CheckBox
    private lateinit var tvProgress : TextView
    private lateinit var tvRemain : TextView

    private val DEFAULT_LISTENER = View.OnClickListener() {
        Toast.makeText(this, "Not implemented button yet!", Toast.LENGTH_SHORT).show()
    }

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

        clapButton.setOnClickListener(DEFAULT_LISTENER)
        rainButton.setOnClickListener(DEFAULT_LISTENER)
        trainButton.setOnClickListener(DEFAULT_LISTENER)
        fileButton.setOnClickListener(DEFAULT_LISTENER)
    }
}