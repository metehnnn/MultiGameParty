package com.example.multigameparty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_timer.*

class TimerActivity : AppCompatActivity() {
    private var difficultly = 0
    lateinit var countdownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val extras = intent.extras

        if(extras != null){
            difficultly = extras.getInt("Difficultly")
        }

       countdownTimer = object : CountDownTimer(4000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
                tests.text = (millisUntilFinished / 1000).toString()
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                tests.text = ""
                val intent = Intent(this@TimerActivity, JerryGameActivity::class.java)
                intent.putExtra("Difficultly",difficultly)
                startActivity(intent)
                finish()
            }
        }.start()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        countdownTimer.cancel()
        finish()
    }
}