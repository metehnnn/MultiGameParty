package com.example.multigameparty

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.infoButton
import kotlinx.android.synthetic.main.activity_reach_the_exit_game_actvity.*
import java.lang.StringBuilder
import kotlin.random.Random
import kotlin.random.nextInt

class ReachTheExitGameActvity : AppCompatActivity() {
    private var listRowOne = arrayListOf<MaterialCardView>()
    private var listRowTwo = arrayListOf<MaterialCardView>()
    private var listRowThree = arrayListOf<MaterialCardView>()
    private var listRowFour = arrayListOf<MaterialCardView>()
    private var listRowFive = arrayListOf<MaterialCardView>()
    private var listRowSix = arrayListOf<MaterialCardView>()
    private var listRowSeven = arrayListOf<MaterialCardView>()
    private var listRowEight = arrayListOf<MaterialCardView>()
    private var listRowNine = arrayListOf<MaterialCardView>()

    private var isClickRowOne = false;
    private var isClickRowTwo = false;
    private var isClickRowThree = false;
    private var isClickRowFour = false;
    private var isClickRowFive = false;
    private var isClickRowSix = false;
    private var isClickRowSeven = false;
    private var isClickRowEight = false;
    private lateinit var sharedPreferences: SharedPreferences
    private var isShowInfoCard = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reach_the_exit_game_actvity)
        sharedPreferences = getSharedPreferences("game", Context.MODE_PRIVATE)
        isShowInfoCard = sharedPreferences.getBoolean("dontShowAgainHopstock",false);

        infoButton.setOnClickListener(View.OnClickListener {
            infoDialog()
        })
        initUI()
        if(!isShowInfoCard)
            infoDialog()
    }

    private fun initUI() {
        listRowOne.add(b33)
        listRowOne.add(b34)
        listRowOne.add(b35)
        listRowOne.add(b36)

        listRowTwo.add(b29)
        listRowTwo.add(b30)
        listRowTwo.add(b31)
        listRowTwo.add(b32)

        listRowThree.add(b25)
        listRowThree.add(b26)
        listRowThree.add(b27)
        listRowThree.add(b28)

        listRowFour.add(b21)
        listRowFour.add(b22)
        listRowFour.add(b23)
        listRowFour.add(b24)

        listRowFive.add(b17)
        listRowFive.add(b18)
        listRowFive.add(b19)
        listRowFive.add(b20)

        listRowSix.add(b13)
        listRowSix.add(b14)
        listRowSix.add(b15)
        listRowSix.add(b16)

        listRowSeven.add(b9)
        listRowSeven.add(b10)
        listRowSeven.add(b11)
        listRowSeven.add(b12)

        listRowEight.add(b5)
        listRowEight.add(b6)
        listRowEight.add(b7)
        listRowEight.add(b8)

        listRowNine.add(b1)
        listRowNine.add(b2)
        listRowNine.add(b3)
        listRowNine.add(b4)

        setColor()
    }
    private fun setColor(){
        var randomNumber = getRandom()
        for(i in 0..3){
            listRowOne[i].setCardBackgroundColor(resources.getColor(R.color.Red))
            listRowOne[i].tag = "RED"
            listRowTwo[i].setCardBackgroundColor(resources.getColor(R.color.Red))
            listRowTwo[i].tag = "RED"
            listRowThree[i].setCardBackgroundColor(resources.getColor(R.color.Red))
            listRowThree[i].tag = "RED"
            listRowFour[i].setCardBackgroundColor(resources.getColor(R.color.Red))
            listRowFour[i].tag = "RED"
            listRowFive[i].setCardBackgroundColor(resources.getColor(R.color.Red))
            listRowFive[i].tag = "RED"
            listRowSix[i].setCardBackgroundColor(resources.getColor(R.color.Red))
            listRowSix[i].tag = "RED"
            listRowSeven[i].setCardBackgroundColor(resources.getColor(R.color.Red))
            listRowSeven[i].tag = "RED"
            listRowEight[i].setCardBackgroundColor(resources.getColor(R.color.Red))
            listRowEight[i].tag = "RED"
            listRowNine[i].setCardBackgroundColor(resources.getColor(R.color.Red))
            listRowNine[i].tag = "RED"
        }
        listRowOne[randomNumber].setCardBackgroundColor(resources.getColor(R.color.Green))
        listRowOne[randomNumber].tag = "GREEN"
        randomNumber = getRandom()
        listRowTwo[randomNumber].setCardBackgroundColor(resources.getColor(R.color.Green))
        listRowTwo[randomNumber].tag = "GREEN"
        randomNumber = getRandom()
        listRowThree[randomNumber].setCardBackgroundColor(resources.getColor(R.color.Green))
        listRowThree[randomNumber].tag = "GREEN"
        randomNumber = getRandom()
        listRowFour[randomNumber].setCardBackgroundColor(resources.getColor(R.color.Green))
        listRowFour[randomNumber].tag = "GREEN"
        randomNumber = getRandom()
        listRowFive[randomNumber].setCardBackgroundColor(resources.getColor(R.color.Green))
        listRowFive[randomNumber].tag = "GREEN"
        randomNumber = getRandom()
        listRowSix[randomNumber].setCardBackgroundColor(resources.getColor(R.color.Green))
        listRowSix[randomNumber].tag = "GREEN"
        randomNumber = getRandom()
        listRowSeven[randomNumber].setCardBackgroundColor(resources.getColor(R.color.Green))
        listRowSeven[randomNumber].tag = "GREEN"
        randomNumber = getRandom()
        listRowEight[randomNumber].setCardBackgroundColor(resources.getColor(R.color.Green))
        listRowEight[randomNumber].tag = "GREEN"
        randomNumber = getRandom()
        listRowNine[randomNumber].setCardBackgroundColor(resources.getColor(R.color.Green))
        listRowNine[randomNumber].tag = "GREEN"

    }
    private fun getRandom():Int{
        return Random.nextInt(0..3)
    }

    fun click(view:View){
        when(view.id){
            R.id.b1_1 -> {
                if(isClickRowEight){
                    b1.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowNine[0],b1_1)
                    },500)
                        if(b1.tag.equals("GREEN")){
                            openWinDialog()
                        }

                }
            }
            R.id.b2_1 -> {
                if(isClickRowEight){
                    b2.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowNine[1],b2_1)
                    },500)
                        if(b2.tag.equals("GREEN")){
                            openWinDialog()
                        }

                }

            }
            R.id.b3_1 -> {
                if(isClickRowEight){
                    b3.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowNine[2],b3_1)
                    },500)
                        if(b3.tag.equals("GREEN")){
                            openWinDialog()
                        }

                }

            }
            R.id.b4_1 -> {
                if(isClickRowEight){
                    b4.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowNine[3],b4_1)
                    },500)
                        if(b4.tag.equals("GREEN")){
                            openWinDialog()
                        }
                }

            }
            R.id.b5_1 -> {
                if(isClickRowSeven){
                    if(b5.tag.equals("GREEN"))
                        isClickRowEight = true;
                    b5.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowEight[0],b5_1)
                    },500)
                }
            }
            R.id.b6_1 -> {
                if(isClickRowSeven){
                    if(b6.tag.equals("GREEN"))
                        isClickRowEight = true;
                    b6.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowEight[1],b6_1)
                    },500)
                }

            }
            R.id.b7_1 -> {
                if(isClickRowSeven){
                    if(b7.tag.equals("GREEN"))
                        isClickRowEight = true;
                    b7.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowEight[2],b7_1)
                    },500)
                }

            }
            R.id.b8_1 -> {
                if(isClickRowSeven){
                    if(b8.tag.equals("GREEN"))
                        isClickRowEight = true;
                    b8.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowEight[3],b8_1)
                    },500)
                }

            }
            R.id.b9_1 -> {
                if(isClickRowSix){
                    if(b9.tag.equals("GREEN"))
                        isClickRowSeven = true;
                    b9.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowSeven[0],b9_1)
                    },500)
                }

            }
            R.id.b10_1 -> {
                if(isClickRowSix){
                    if(b10.tag.equals("GREEN"))
                        isClickRowSeven = true;
                    b10.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowSeven[1],b10_1)
                    },500)
                }

            }
            R.id.b11_1 -> {
                if(isClickRowSix){
                    if(b11.tag.equals("GREEN"))
                        isClickRowSeven = true;
                    b11.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowSeven[2],b11_1)
                    },500)
                }

            }
            R.id.b12_1 -> {
                if(isClickRowSix){
                    if(b12.tag.equals("GREEN"))
                        isClickRowSeven = true;
                    b12.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowSeven[3],b12_1)
                    },500)
                }

            }
            R.id.b13_1 -> {
                if(isClickRowFive){
                    if(b13.tag.equals("GREEN"))
                        isClickRowSix = true;
                    b13.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowSix[0],b13_1)
                    },500)
                }

            }
            R.id.b14_1 -> {
                if(isClickRowFive){
                    if(b14.tag.equals("GREEN"))
                        isClickRowSix = true;
                    b14.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowSix[1],b14_1)
                    },500)
                }

            }
            R.id.b15_1 -> {
                if(isClickRowFive){
                    if(b15.tag.equals("GREEN"))
                        isClickRowSix = true;
                    b15.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowSix[2],b15_1)
                    },500)
                }

            }
            R.id.b16_1 -> {
                if(isClickRowFive){
                    if(b16.tag.equals("GREEN"))
                        isClickRowSix = true;
                    b16.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowSix[3],b16_1)
                    },500)
                }

            }
            R.id.b17_1 -> {
                if(isClickRowFour){
                    if(b17.tag.equals("GREEN"))
                        isClickRowFive = true;
                    b17.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowFive[0],b17_1)
                    },500)
                }

            }
            R.id.b18_1 -> {
                if(isClickRowFour){
                    if(b18.tag.equals("GREEN"))
                        isClickRowFive = true;
                    b18.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowFive[1],b18_1)
                    },500)
                }

            }
            R.id.b19_1 -> {
                if(isClickRowFour){
                    if(b19.tag.equals("GREEN"))
                        isClickRowFive = true;
                    b19.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowFive[2],b19_1)
                    },500)
                }

            }
            R.id.b20_1 -> {
                if(isClickRowFour){
                    if(b20.tag.equals("GREEN"))
                        isClickRowFive = true;
                    b20.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowFive[3],b20_1)
                    },500)
                }

            }
            R.id.b21_1 -> {
                if(isClickRowThree){
                    if(b21.tag.equals("GREEN"))
                        isClickRowFour = true;
                    b21.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowFour[0],b21_1)
                    },500)
                }

            }
            R.id.b22_1 -> {
                if(isClickRowThree){
                    if(b22.tag.equals("GREEN"))
                        isClickRowFour = true;
                    b22.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowFour[1],b22_1)
                    },500)
                }

            }
            R.id.b23_1 -> {
                if(isClickRowThree){
                    if(b23.tag.equals("GREEN"))
                        isClickRowFour = true;
                    b23.visibility = android.view.View.VISIBLE
                    android.os.Handler(android.os.Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowFour[2],b23_1)
                    },500)
                }

            }
            R.id.b24_1 -> {
                if(isClickRowThree){
                    if(b24.tag.equals("GREEN"))
                        isClickRowFour = true;
                    b24.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowFour[3],b24_1)
                    },500)
                }

            }
            R.id.b25_1 -> {
                if(isClickRowTwo){
                    if(b25.tag.equals("GREEN"))
                        isClickRowThree = true;
                    b25.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowThree[0],b25_1)
                    },500)
                }

            }
            R.id.b26_1 -> {
                if(isClickRowTwo){
                    if(b26.tag.equals("GREEN"))
                        isClickRowThree = true;
                    b26.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowThree[1],b26_1)
                    },500)
                }

            }
            R.id.b27_1 -> {
                if(isClickRowTwo){
                    if(b27.tag.equals("GREEN"))
                        isClickRowThree = true;
                    b27.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowThree[2],b27_1)
                    },500)
                }

            }
            R.id.b28_1 -> {
                if(isClickRowTwo){
                    if(b28.tag.equals("GREEN"))
                        isClickRowThree = true;
                    b28.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowThree[3],b28_1)
                    },500)
                }

            }
            R.id.b29_1 -> {
                if(isClickRowOne){
                    if(b29.tag.equals("GREEN"))
                        isClickRowTwo = true;
                    b29.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowTwo[0],b29_1)
                    },500)
                }

            }
            R.id.b30_1 -> {
                if(isClickRowOne){
                    if(b30.tag.equals("GREEN"))
                        isClickRowTwo = true;
                    b30.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowTwo[1],b30_1)
                    },500)
                }

            }
            R.id.b31_1 -> {
                if(isClickRowOne){
                    if(b31.tag.equals("GREEN"))
                        isClickRowTwo = true;
                    b31.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowTwo[2],b31_1)
                    },500)
                }

            }
            R.id.b32_1 -> {
                if(isClickRowOne){
                    if(b32.tag.equals("GREEN"))
                        isClickRowTwo = true;
                    b32.visibility = View.VISIBLE
                    Handler(Looper.myLooper()!!).postDelayed({
                        singlePlayer(listRowTwo[3],b32_1)
                    },500)
                }
            }
            R.id.b33_1 -> {
                if(b33.tag.equals("GREEN"))
                    isClickRowOne = true;
                b33.visibility = View.VISIBLE
                Handler(Looper.myLooper()!!).postDelayed({
                    singlePlayer(listRowOne[0],b33_1)
                },500)
            }
            R.id.b34_1 -> {
                if(b34.tag.equals("GREEN"))
                    isClickRowOne = true;
                b34.visibility = View.VISIBLE
                Handler(Looper.myLooper()!!).postDelayed({
                    singlePlayer(listRowOne[1],b34_1)
                },500)
            }
            R.id.b35_1 -> {
                if(b35.tag.equals("GREEN"))
                    isClickRowOne = true;
                b35.visibility = View.VISIBLE
                Handler(Looper.myLooper()!!).postDelayed({
                    singlePlayer(listRowOne[2],b35_1)
                },500)
            }
            R.id.b36_1 -> {
                if(b36.tag.equals("GREEN"))
                    isClickRowOne = true;
                b36.visibility = View.VISIBLE
                Handler(Looper.myLooper()!!).postDelayed({
                    singlePlayer(listRowOne[3],b36_1)
                },500)
            }

        }
    }

    private fun singlePlayer(cardView:MaterialCardView,cardView2: MaterialCardView){
        if(cardView.tag == "RED"){
            cardView.visibility = View.INVISIBLE
            cardView2.visibility = View.INVISIBLE

//            for(i in 0..3){
//                listRowOne[i].visibility = View.INVISIBLE
//                listRowTwo[i].visibility = View.INVISIBLE
//                listRowThree[i].visibility = View.INVISIBLE
//                listRowFour[i].visibility = View.INVISIBLE
//                listRowFive[i].visibility = View.INVISIBLE
//                listRowSix[i].visibility = View.INVISIBLE
//                listRowSeven[i].visibility = View.INVISIBLE
//                listRowEight[i].visibility = View.INVISIBLE
//                listRowNine[i].visibility = View.INVISIBLE
//            }
        }
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
        winText.text = resources.getText(R.string.msg_congratulations)
        infoText.text = resources.getText(R.string.msg_restart_game)
        btnRestart.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@ReachTheExitGameActvity, ReachTheExitGameActvity::class.java)
            startActivity(intent)
            this.finish()
        })
        btnCancel.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@ReachTheExitGameActvity,MainActivity::class.java))
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
        infoText.text = resources.getText(R.string.info_hopstock)
        btnOk.setOnClickListener(View.OnClickListener {
            isShowInfoCard = cbDontShowAgain.isChecked
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("dontShowAgainHopstock",cbDontShowAgain.isChecked)
            editor.apply()
            dialog.dismiss()
        })
    }
}