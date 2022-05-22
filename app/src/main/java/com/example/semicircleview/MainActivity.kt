package com.example.semicircleview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val circleView = findViewById<SemiCircleView>(R.id.semi_circle_view)
        val progressTv = findViewById<AppCompatTextView>(R.id.progress_tv)
        val changeProgressButton = findViewById<AppCompatButton>(R.id.change_progress_button)

        circleView.setupUI(R.color.circle_foreground)
        changeProgressButton.setOnClickListener {
            val progress = Random.nextInt(0,100)
            progressTv.text = "$progress %"
            circleView.setProgress(progress/100f)
        }
    }
}