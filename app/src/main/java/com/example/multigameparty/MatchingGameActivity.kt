package com.example.multigameparty

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.infoButton
import kotlinx.android.synthetic.main.activity_matching_game.*
import kotlinx.android.synthetic.main.activity_sos_game.view1
import kotlinx.android.synthetic.main.activity_sos_game.view2
import kotlinx.android.synthetic.main.detail_layout.*
import kotlinx.android.synthetic.main.dialog_postive_layout.*
import org.w3c.dom.Text

class MatchingGameActivity : AppCompatActivity() {
    private var score1 = 0
    private var score2 = 0
    private var tempImage = ArrayList<ImageView>()
    private val imageViewArray = ArrayList<ImageView>()
    private val cardViewArray = ArrayList<MaterialCardView>()
    private val imageArray = ArrayList<Int>()
    private val randList = ArrayList<Int>()
    private val tempRand = ArrayList<Int>()
    private val tempImageView = ArrayList<ImageView>()
    private val tempCardView = ArrayList<MaterialCardView>()
    private var gameType = 0
    private var name1 = ""
    private var name2 = ""
    private var isPlayer1 = true
    private var isPlayer2 = false
    private lateinit var sharedPreferences: SharedPreferences
    private var isShowInfoCard = false;
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching_game)
        sharedPreferences = getSharedPreferences("game", Context.MODE_PRIVATE)
        isShowInfoCard = sharedPreferences.getBoolean("dontShowAgainMatchingGame",false);
        supportActionBar?.hide()
        val extras = intent.extras
        if(extras != null){
            gameType = extras.getInt("GameType")
            name1 = extras.getString("Name1").toString()
            name2 = extras.getString("Name2").toString()
        }
        if(gameType == 1){
            playerLayout.visibility = View.INVISIBLE
            logo.visibility = View.VISIBLE
        }
        if(gameType == 2){
            logo.visibility = View.INVISIBLE
            playerLayout.visibility = View.VISIBLE
            view1.background = resources.getDrawable(R.drawable.green_rounded,resources.newTheme())
            txtPlayer2.text = name2
        }
        infoButton.setOnClickListener(View.OnClickListener {
            infoDialog()
        })
        initUI()
        if(!isShowInfoCard)
            infoDialog()
        imageQuestionMark1.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView1)
                tempCardView.add(cardView1)
                imageView1.visibility = View.VISIBLE
                controlImage(imageView1,cardView1)
            }
        })
        imageQuestionMark2.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView2)
                tempCardView.add(cardView2)
                imageView2.visibility = View.VISIBLE
                controlImage(imageView2,cardView2)
            }
        })
        imageQuestionMark3.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView3)
                tempCardView.add(cardView3)
                imageView3.visibility = View.VISIBLE
                controlImage(imageView3,cardView3)
            }
        })
        imageQuestionMark4.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView4)
                tempCardView.add(cardView4)
                imageView4.visibility = View.VISIBLE
                controlImage(imageView4,cardView4)
            }
        })
        imageQuestionMark5.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView5)
                tempCardView.add(cardView5)
                imageView5.visibility = View.VISIBLE
                controlImage(imageView5,cardView5)
            }
        })
        imageQuestionMark6.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView6)
                tempCardView.add(cardView6)
                imageView6.visibility = View.VISIBLE
                controlImage(imageView6,cardView6)
            }
        })
        imageQuestionMark7.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView7)
                tempCardView.add(cardView7)
                imageView7.visibility = View.VISIBLE
                controlImage(imageView7,cardView7)
            }
        })
        imageQuestionMark8.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView8)
                tempCardView.add(cardView8)
                imageView8.visibility = View.VISIBLE
                controlImage(imageView8,cardView8)
            }
        })
        imageQuestionMark9.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView9)
                tempCardView.add(cardView9)
                imageView9.visibility = View.VISIBLE
                controlImage(imageView9,cardView9)
            }
        })
        imageQuestionMark10.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView10)
                tempCardView.add(cardView10)
                imageView10.visibility = View.VISIBLE
                controlImage(imageView10,cardView10)
            }
        })
        imageQuestionMark11.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView11)
                tempCardView.add(cardView11)
                imageView11.visibility = View.VISIBLE
                controlImage(imageView11,cardView11)
            }
        })
        imageQuestionMark12.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView12)
                tempCardView.add(cardView12)
                imageView12.visibility = View.VISIBLE
                controlImage(imageView12,cardView12)
            }
        })
        imageQuestionMark13.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView13)
                tempCardView.add(cardView13)
                imageView13.visibility = View.VISIBLE
                controlImage(imageView13,cardView13)
            }
        })
        imageQuestionMark14.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView14)
                tempCardView.add(cardView14)
                imageView14.visibility = View.VISIBLE
                controlImage(imageView14,cardView14)
            }
        })
        imageQuestionMark15.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView15)
                tempCardView.add(cardView15)
                imageView15.visibility = View.VISIBLE
                controlImage(imageView15,cardView15)
            }
        })
        imageQuestionMark16.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView16)
                tempCardView.add(cardView16)
                imageView16.visibility = View.VISIBLE
                controlImage(imageView16,cardView16)
            }
        })
        imageQuestionMark17.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView17)
                tempCardView.add(cardView17)
                imageView17.visibility = View.VISIBLE
                controlImage(imageView17,cardView17)
            }
        })
        imageQuestionMark18.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView18)
                tempCardView.add(cardView18)
                imageView18.visibility = View.VISIBLE
                controlImage(imageView18,cardView18)
            }
        })
        imageQuestionMark19.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView19)
                tempCardView.add(cardView19)
                imageView19.visibility = View.VISIBLE
                controlImage(imageView19,cardView19)
            }
        })
        imageQuestionMark20.setOnClickListener(View.OnClickListener {
            if(tempImageView.size <2){
                tempImageView.add(imageView20)
                tempCardView.add(cardView20)
                imageView20.visibility = View.VISIBLE
                controlImage(imageView20,cardView20)
            }
        })
    }

    private fun increaseScore() {
        if(gameType == 2){
            if(isPlayer1){
                score1++
                scoreText1.text = score1.toString()
            }
            if(isPlayer2){
                score2++
                scoreText2.text = score2.toString()
            }
            if(score1 + score2 == 10){
                openWinDialog()
            }
        }
        if(gameType == 1){
            score1++
            if(score1 == 10)
                openWinDialog()
        }

        tempImage.clear()
    }

    private fun controlImage(imageView: ImageView,cardView: MaterialCardView){
        if(tempImageView.size == 2){
            if(tempImageView[0] != tempImageView[1]){
                if(tempImageView[0].tag == tempImageView[1].tag){
                    increaseScore()
                    tempImageView.clear()
                    Handler(Looper.myLooper()!!).postDelayed({
                        tempCardView[0].visibility = View.INVISIBLE
                        tempCardView[1].visibility = View.INVISIBLE
                        tempCardView.clear()
                    },500)

                }
                else{
                    object: CountDownTimer(1000,500){
                        @SuppressLint("UseCompatLoadingForDrawables")
                        override fun onFinish() {
                            tempImageView[0].visibility = View.INVISIBLE
                            tempImageView[1].visibility = View.INVISIBLE
                            tempImageView.clear()
                            tempCardView.clear()
                            if(gameType == 2){
                                if(isPlayer1){
                                    view1.background = resources.getDrawable(R.drawable.rounded,resources.newTheme())
                                    view2.background = resources.getDrawable(R.drawable.green_rounded,resources.newTheme())
                                    view1.visibility = View.INVISIBLE
                                    view2.visibility = View.VISIBLE
                                    isPlayer1 = false
                                    isPlayer2 = true
                                }
                                else if(isPlayer2){
                                    view1.background = resources.getDrawable(R.drawable.green_rounded,resources.newTheme())
                                    view2.background = resources.getDrawable(R.drawable.rounded,resources.newTheme())
                                    view1.visibility = View.VISIBLE
                                    view2.visibility = View.INVISIBLE
                                    isPlayer2 = false
                                    isPlayer1 = true
                                }
                            }

                        }
                        override fun onTick(millisUntilFinished: Long) {
                        }
                    }.start()

                }
            }
            else{
             tempImageView.remove(tempImageView[1])
            }

        }



    }
private fun initUI(){
    imageViewArray.add(imageView1)
    imageViewArray.add(imageView2)
    imageViewArray.add(imageView3)
    imageViewArray.add(imageView4)
    imageViewArray.add(imageView5)
    imageViewArray.add(imageView6)
    imageViewArray.add(imageView7)
    imageViewArray.add(imageView8)
    imageViewArray.add(imageView9)
    imageViewArray.add(imageView10)
    imageViewArray.add(imageView11)
    imageViewArray.add(imageView12)
    imageViewArray.add(imageView13)
    imageViewArray.add(imageView14)
    imageViewArray.add(imageView15)
    imageViewArray.add(imageView16)
    imageViewArray.add(imageView17)
    imageViewArray.add(imageView18)
    imageViewArray.add(imageView19)
    imageViewArray.add(imageView20)

    cardViewArray.add(cardView1)
    cardViewArray.add(cardView2)
    cardViewArray.add(cardView3)
    cardViewArray.add(cardView4)
    cardViewArray.add(cardView5)
    cardViewArray.add(cardView6)
    cardViewArray.add(cardView7)
    cardViewArray.add(cardView8)
    cardViewArray.add(cardView9)
    cardViewArray.add(cardView10)
    cardViewArray.add(cardView11)
    cardViewArray.add(cardView12)
    cardViewArray.add(cardView13)
    cardViewArray.add(cardView14)
    cardViewArray.add(cardView15)
    cardViewArray.add(cardView16)
    cardViewArray.add(cardView17)
    cardViewArray.add(cardView18)
    cardViewArray.add(cardView19)
    cardViewArray.add(cardView20)

    imageArray.add(R.drawable.spiderman)
    imageArray.add(R.drawable.dog)
    imageArray.add(R.drawable.rabbit)
    imageArray.add(R.drawable.cow)
    imageArray.add(R.drawable.tweety)
    imageArray.add(R.drawable.panda)
    imageArray.add(R.drawable.jerry2)
    imageArray.add(R.drawable.temel_reis)
    imageArray.add(R.drawable.hulk)
    imageArray.add(R.drawable.superman)
    imageArray.add(R.drawable.spiderman)
    imageArray.add(R.drawable.dog)
    imageArray.add(R.drawable.rabbit)
    imageArray.add(R.drawable.cow)
    imageArray.add(R.drawable.tweety)
    imageArray.add(R.drawable.panda)
    imageArray.add(R.drawable.jerry2)
    imageArray.add(R.drawable.temel_reis)
    imageArray.add(R.drawable.hulk)
    imageArray.add(R.drawable.superman)

    for(i in 0 until imageArray.size){
        if(tempRand.size > 0){
            val random = ((0 until imageArray.size)- tempRand.toSet()).random()
            tempRand.add(random)
            randList.add(random)
        }
        else{
            val random = (0 until imageArray.size).random()
            randList.add(random)
            tempRand.add(random)
        }
    }
    for(i in randList){
        imageViewArray[i].setImageResource(imageArray[randList[i]])
        imageViewArray[i].tag = imageArray[randList[i]]
    }
}
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    private fun openWinDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_postive_layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        val infoText = dialog.findViewById(R.id.infoText) as TextView
        val winText = dialog.findViewById(R.id.winText) as TextView
        val btnRestart = dialog.findViewById(R.id.btnRestart) as FloatingActionButton
        val btnCancel = dialog.findViewById(R.id.btnCancel) as FloatingActionButton
        try {
            dialog.show()
        }catch (e:Exception){
            e.printStackTrace()
        }
        if(gameType == 2){
            if(score1 > score2){
                winText.text = String.format("𝕂𝔸ℤ𝔸ℕ𝔸ℕ %s",name1)
                infoText.text = "𝕋𝕖𝕜𝕣𝕒𝕣 𝕆𝕪𝕟𝕒𝕞𝕒𝕜 𝕚𝕤𝕥𝕖𝕣 𝕞𝕚𝕤𝕚𝕟𝕚𝕫?"
            }
            else if(score1 < score2){
                winText.text = String.format("𝕂𝔸ℤ𝔸ℕ𝔸ℕ %s",name2)
                infoText.text = "𝕋𝕖𝕜𝕣𝕒𝕣 𝕆𝕪𝕟𝕒𝕞𝕒𝕜 𝕚𝕤𝕥𝕖𝕣 𝕞𝕚𝕤𝕚𝕟𝕚𝕫?"
            }
            else{
                winText.text = "𝔹𝔼ℝ𝔸𝔹𝔼ℝ𝕃İ𝕂"
                infoText.text = String.format("%s %s\n%s %s",name1,score1,name2,score2)

            }
        }
        if(gameType == 1){
            winText.text = "𝕋𝔼𝔹ℝİ𝕂𝕃𝔼ℝ"
            infoText.text = "𝕋𝕖𝕜𝕣𝕒𝕣 𝕆𝕪𝕟𝕒𝕞𝕒𝕜 𝕚𝕤𝕥𝕖𝕣 𝕞𝕚𝕤𝕚𝕟𝕚𝕫?"
        }
        btnRestart.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MatchingGameActivity, MatchingGameActivity::class.java)
            intent.putExtra("GameType",gameType)
            intent.putExtra("Name1",name1)
            intent.putExtra("Name2",name2)
            startActivity(intent)
            this.finish()
        })
        btnCancel.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MatchingGameActivity,MainActivity::class.java))
            this.finish()
        })
    }
    private fun infoDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.info_dialog)
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
        infoText.text = resources.getText(R.string.info_find_the_same)
        btnOk.setOnClickListener(View.OnClickListener {
            isShowInfoCard = cbDontShowAgain.isChecked
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("dontShowAgainMatchingGame",cbDontShowAgain.isChecked)
            editor.apply()
            dialog.dismiss()
        })
    }

}