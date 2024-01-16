package com.example.multigameparty

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.TextView
import com.example.multigameparty.sharkHunterGame.GameView
import com.google.android.material.button.MaterialButton

class SharkHunterGameActivity : AppCompatActivity() {
    private lateinit var gameView:GameView
    private var mMediaPlayer: MediaPlayer? = null
    private lateinit var sharedPreferences: SharedPreferences
    private var isShowInfoCard = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val point = Point()
        windowManager.defaultDisplay.getSize(point)
        gameView = GameView(this,point.x,point.y)
        setContentView(gameView)
        sharedPreferences = getSharedPreferences("game", Context.MODE_PRIVATE)
        isShowInfoCard = sharedPreferences.getBoolean("dontShowAgainShark",false);

        if(mMediaPlayer == null){
            mMediaPlayer = MediaPlayer.create(this,R.raw.shark_hunter)
          //  mMediaPlayer!!.start()
        }
//        if(!isShowInfoCard)
//            infoDialog()
    }

    override fun onPause() {
        super.onPause()
        gameView.pause()
    }

    override fun onResume() {
        super.onResume()
        gameView.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer!!.stop()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        mMediaPlayer!!.stop()
        finish()
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
        infoText.text = resources.getText(R.string.info_shark_hunter)
        btnOk.setOnClickListener(View.OnClickListener {
            isShowInfoCard = cbDontShowAgain.isChecked
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("dontShowAgainShark",cbDontShowAgain.isChecked)
            editor.apply()
            dialog.dismiss()
        })
    }

}