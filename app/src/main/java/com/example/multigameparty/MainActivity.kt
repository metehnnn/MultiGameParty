package com.example.multigameparty


import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.multigameparty.helpers.*
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.button_layout.*


class MainActivity : AppCompatActivity() {
    private var mMediaPlayer: MediaPlayer? = null
    private var isSoundActive = true;
    private lateinit var sharedPreferences: SharedPreferences
    private var isShowInfoCard = false;
    private var isFirstShowInfoCard = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        sharedPreferences = getSharedPreferences("game", Context.MODE_PRIVATE)
        mMediaPlayer = MediaPlayer.create(this,R.raw.app_sound)
        mMediaPlayer!!.isLooping = true
        isShowInfoCard = sharedPreferences.getBoolean("dontShowAgain",false);

        if(!isShowInfoCard && !isFirstShowInfoCard)
            infoDialog()
        infoButton.setOnClickListener(View.OnClickListener {
            infoDialog()
        })

        soundControl.setOnClickListener(View.OnClickListener {
            if(isSoundActive){
                isSoundActive = false;
                soundControl.setImageResource(R.drawable.sound_off)
                mMediaPlayer!!.pause()
            }
            else{
                mMediaPlayer!!.start()
                isSoundActive = true;
                soundControl.setImageResource(R.drawable.sound_on)
            }
        })

        motionlayout.setTransitionListener(object :MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                motionLayout?.post {
                    when (endId) {
                        R.id.detailCardCenter -> {
                            JerryGameHelpers(this@MainActivity,window.decorView)
                        }
                        R.id.detailCardRight1 -> {
                            MatchingGameHelpers(this@MainActivity,window.decorView)
                        }
                        R.id.detailCardLeft1 -> {
                            SosGameHelpers(this@MainActivity,window.decorView)
                        }
                        R.id.detailCardRight2 ->{
                            SharkHunterGameHelpers(this@MainActivity,window.decorView)
                        }
                        R.id.detailCardRight3 ->{
                            ReachTheCupHelpers(this@MainActivity,window.decorView)
                        }
                    }
                }
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }

        })

    }

    override fun onResume() {
        super.onResume()
        mMediaPlayer!!.start()
    }

    override fun onStop() {
        super.onStop()
        mMediaPlayer!!.pause()
    }

    private fun infoDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.info_dialog_main)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        val infoText = dialog.findViewById(R.id.infoText) as TextView
        val btnOk = dialog.findViewById(R.id.btnOk) as MaterialButton
        val cbDontShowAgain = dialog.findViewById(R.id.cbDontShowAgain) as CheckBox
        cbDontShowAgain.isChecked = isShowInfoCard

        try {
            dialog.show()
        }catch (e:Exception){
            e.printStackTrace()
        }
        infoText.text = resources.getText(R.string.info_main)
        btnOk.setOnClickListener(View.OnClickListener {
            isShowInfoCard = cbDontShowAgain.isChecked
            isFirstShowInfoCard = true;
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("dontShowAgain",cbDontShowAgain.isChecked)
            editor.apply()
            dialog.dismiss()
        })
    }

}
