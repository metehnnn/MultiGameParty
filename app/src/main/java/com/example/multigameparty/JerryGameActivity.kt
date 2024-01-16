package com.example.multigameparty

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_jerry_game.*
import kotlinx.android.synthetic.main.dialog_postive_layout.*


class JerryGameActivity : AppCompatActivity() {
    private var score = 0
    private var imageArray = ArrayList<ImageView>()
    private var difficulty:Int = 0
    private lateinit var countdownTimer: CountDownTimer
    lateinit var countdownTimerForImage: CountDownTimer
    private var mMediaPlayer: MediaPlayer? = null
    private lateinit var sharedPreferences:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jerry_game)
        sharedPreferences = getSharedPreferences("game", Context.MODE_PRIVATE)
        supportActionBar?.hide()
        if(mMediaPlayer == null){
            mMediaPlayer = MediaPlayer.create(this,R.raw.tom_and_jerry)
            mMediaPlayer!!.start()
        }
        val extras = intent.extras

        if(extras != null){
            difficulty = extras.getInt("Difficultly")
        }
        initUI()
    }

    private fun initUI(){
        highScoreText.text = String.format("ℍ𝕚𝕘𝕙 𝕊𝕔𝕠𝕣𝕖: %s",sharedPreferences.getInt("highScoreJerryGame",0).toString())
        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        imageArray.add(imageView10)
        imageArray.add(imageView11)
        imageArray.add(imageView12)
        imageArray.add(imageView13)
        imageArray.add(imageView14)
        imageArray.add(imageView15)
        imageArray.add(imageView16)
        imageArray.add(imageView17)
        imageArray.add(imageView18)
        startGame()
    }

    fun hideImages(){
        for(image in imageArray){
            image.visibility = View.INVISIBLE
        }
        val random = java.util.Random()
        val randomIndex = random.nextInt(18)
        imageArray[randomIndex].visibility = View.VISIBLE
    }

    fun increaseScore(view: View){
        score++
        scoreText.text = score.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
    private fun startGame(){
        countdownTimerForImage = object : CountDownTimer(20000, difficulty.toLong()){
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {
                hideImages()
            }
        }.start()

        //CountDown Timer
        countdownTimer = object :CountDownTimer(20000,1000){
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                timeText.text = "𝟘"
                countdownTimerForImage.cancel()
                saveHighScore()
                openWinDialog()
            }

        }.start()
    }
    private fun openWinDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_postive_layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        val infoText = dialog.findViewById(R.id.infoText) as TextView
        val btnRestart = dialog.findViewById(R.id.btnRestart) as FloatingActionButton
        val btnCancel = dialog.findViewById(R.id.btnCancel) as FloatingActionButton
        try {
            dialog.show()
        }catch (e:Exception){
            e.printStackTrace()
        }
        infoText.text = String.format(infoText.text.toString(),score.toString())
        btnRestart.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@JerryGameActivity, TimerActivity::class.java)
            intent.putExtra("Difficultly",difficulty)
            startActivity(intent)
            this.finish()
        })
        btnCancel.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@JerryGameActivity,MainActivity::class.java))
            this.finish()
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mMediaPlayer!!.stop()
        finish()
    }
    private fun saveHighScore() {
        if(sharedPreferences.getInt("highScoreJerryGame",0) < score){
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("highScoreJerryGame",score)
            editor.apply()
        }
    }

}