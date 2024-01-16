package com.example.multigameparty

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TableRow
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.infoButton
import kotlinx.android.synthetic.main.activity_sos_game.*
import kotlinx.android.synthetic.main.dialog_choose.*
import kotlinx.android.synthetic.main.dialog_choose.view.*
import kotlin.collections.set


class SosGameActivity : AppCompatActivity() {
    private var dialog: Dialog? =null
    private var tempButton: Button? =null
    private var name1 = ""
    private var name2 = ""
    private var isPlayer1 = true
    private var isPlayer2 = false
    private var tempActionMap = HashMap<Int,String>()
    private var buttonId:Int = 0
    private var score = 0
    private var score1 = 0
    private var score2 = 0
    private var sRightBannedNumberList = listOf(5,6,11,12,17,18,23,24,29,30,35,36)
    private var sLeftBannedNumberList = listOf(7,8,13,14,19,20,25,26,31,32)
    private var sSouthEastBannedNumberList = listOf(5,6,11,12,17,18)
    private var sSouthWestBannedNumberList = listOf(1,2,7,8,13,14,19,20,25,26)
    private var sNorthWestBannedNumberList = listOf(19,20,25,26,31,32)
    private var sNorthEastBannedNumberList = listOf(11,12,17,18,23,24,29,30,35,36)
    private var oRightLeftBannedNumber = listOf(6,12,18,24,30,1,7,13,19,25,31)
    private var oCrossBannedNumbers1 = listOf(6,12,18,24,30)
    private var oCrossBannedNumbers2 = listOf(1,6,7,13,19,25,31)
    private var tempScore:Int = 0
    private var buttonList = HashMap<Int,Button>()
    private lateinit var sharedPreferences: SharedPreferences
    private var isShowInfoCard = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sos_game)
        sharedPreferences = getSharedPreferences("game", Context.MODE_PRIVATE)
        isShowInfoCard = sharedPreferences.getBoolean("dontShowAgainSos",false);
        dialog = Dialog(this)
        dialog!!.setContentView(R.layout.dialog_choose)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        view1.background = resources.getDrawable(R.drawable.green_rounded)
        val extras = intent.extras
        if(extras != null){
            name1 = extras.getString("Name1").toString().uppercase()
            name2 = extras.getString("Name2").toString().uppercase()
            txtPlayer1.text = name1
            txtPlayer2.text = name2
        }
        infoButton.setOnClickListener(View.OnClickListener {
            infoDialog()
        })
        initUI()
        if(!isShowInfoCard)
            infoDialog()

    }
    private fun initUI() {
        buttonList[1] = button1
        buttonList[2] = button2
        buttonList[3] = button3
        buttonList[4] = button4
        buttonList[5] = button5
        buttonList[6] = button6
        buttonList[7] = button7
        buttonList[8] = button8
        buttonList[9] = button9
        buttonList[10] = button10
        buttonList[11] = button11
        buttonList[12] = button12
        buttonList[13] = button13
        buttonList[14] = button14
        buttonList[15] = button15
        buttonList[16] = button16
        buttonList[17] = button17
        buttonList[18] = button18
        buttonList[19] = button19
        buttonList[20] = button20
        buttonList[21] = button21
        buttonList[22] = button22
        buttonList[23] = button23
        buttonList[24] = button24
        buttonList[25] = button25
        buttonList[26] = button26
        buttonList[27] = button27
        buttonList[28] = button28
        buttonList[29] = button29
        buttonList[30] = button30
        buttonList[31] = button31
        buttonList[32] = button32
        buttonList[33] = button33
        buttonList[34] = button34
        buttonList[35] = button35
        buttonList[36] = button36

        for(i in 1..37){
            buttonList[i]?.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary))
            buttonList[i]?.setTextColor(resources.getColor(R.color.white))
        }

        val buttonS = dialog!!.findViewById(R.id.buttonS) as Button
        val buttonO = dialog!!.findViewById(R.id.buttonO) as Button
        val tableLayout = dialog!!.findViewById(R.id.tableLayout) as TableRow
        tableLayout.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        buttonS.setOnClickListener(View.OnClickListener {
            if (tempButton != null){
                tempButton!!.text = "S"
                tempButton!!.isEnabled = false
                tempActionMap.put(buttonId,"S")
                checkGame(buttonId)
                if(isGameOver())
                    openWinDialog()
            }
            dialog!!.dismiss()
        })
        buttonO.setOnClickListener(View.OnClickListener {
            if (tempButton != null){
                tempButton!!.text = "O"
                tempButton!!.isEnabled = false
                tempActionMap.put(buttonId,"O")
                checkGame(buttonId)
                if(isGameOver())
                    openWinDialog()
            }
            dialog!!.dismiss()
        })
    }

    fun clickChoose(view:View){
        view.id
        when(view.id){
            R.id.button1 -> {
                tempButton = button1
                buttonId = 1
            }
            R.id.button2 ->{
                tempButton = button2
                buttonId = 2
            }
            R.id.button3 ->{
                tempButton = button3
                buttonId = 3
            }
            R.id.button4 -> {
                buttonId = 4
                tempButton = button4
            }
            R.id.button5 -> {
                tempButton = button5
                buttonId = 5
            }
            R.id.button6 -> {
                tempButton = button6
                buttonId = 6
            }
            R.id.button7 -> {
                tempButton = button7
                buttonId = 7
            }
            R.id.button8 -> {
                tempButton = button8
                buttonId = 8
            }
            R.id.button9 -> {
                tempButton = button9
                buttonId = 9
            }
            R.id.button10 -> {
                tempButton = button10
                buttonId = 10
            }
            R.id.button11 -> {
                tempButton = button11
                buttonId = 11
            }
            R.id.button12 -> {
                tempButton = button12
                buttonId = 12
            }
            R.id.button13 -> {
                tempButton = button13
                buttonId = 13
            }
            R.id.button14 -> {
                tempButton = button14
                buttonId = 14
            }
            R.id.button15 -> {
                tempButton = button15
                buttonId = 15
            }
            R.id.button16 -> {
                tempButton = button16
                buttonId= 16
            }
            R.id.button17 -> {
                tempButton = button17
                buttonId = 17
            }
            R.id.button18 -> {
                tempButton = button18
                buttonId = 18
            }
            R.id.button19 -> {
                tempButton = button19
                buttonId = 19
            }
            R.id.button20 -> {
                tempButton = button20
                buttonId = 20
            }
            R.id.button21 -> {
                tempButton = button21
                buttonId = 21
            }
            R.id.button22 -> {
                tempButton = button22
                buttonId = 22
            }
            R.id.button23 -> {
                tempButton = button23
                buttonId = 23
            }
            R.id.button24 -> {
                tempButton = button24
                buttonId = 24
            }
            R.id.button25 -> {
                tempButton = button25
                buttonId = 25
            }
            R.id.button26 -> {
                tempButton = button26
                buttonId = 26
            }
            R.id.button27 -> {
                tempButton = button27
                buttonId = 27
            }
            R.id.button28 -> {
                tempButton = button28
                buttonId = 28
            }
            R.id.button29 -> {
                tempButton = button29
                buttonId = 29
            }
            R.id.button30 -> {
                tempButton = button30
                buttonId = 30
            }
            R.id.button31 -> {
                tempButton = button31
                buttonId = 31
            }
            R.id.button32 -> {
                tempButton = button32
                buttonId = 32
            }
            R.id.button33 -> {
                tempButton = button33
                buttonId = 33
            }
            R.id.button34 -> {
                tempButton = button34
                buttonId = 34
            }
            R.id.button35 -> {
                tempButton = button35
                buttonId = 35
            }
            R.id.button36 -> {
                tempButton = button36
                buttonId = 36
            }
        }
        dialog?.show()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun checkGame(id : Int){
        if(tempActionMap[id].equals("S")) {
            if (tempActionMap[id + 1].equals("O"))
                if (tempActionMap[id + 2].equals("S"))
                    if (!sRightBannedNumberList.contains(id))
                        if (isPlayer1) {
                            score++
                            score1++
                            buttonList[id]?.let { buttonList[id+1]?.let { it1 ->
                                buttonList[id+2]?.let { it2 ->
                                    setRedButton(it,
                                        it1, it2
                                    )
                                }
                            } }
                        } else {
                            score++
                            score2++
                            buttonList[id]?.let { buttonList[id+1]?.let { it1 ->
                                buttonList[id+2]?.let { it2 ->
                                    setGreenButton(it,
                                        it1, it2
                                    )
                                }
                            } }
                        }
            if (tempActionMap[id - 1].equals("O"))
                if (tempActionMap[id - 2].equals("S"))
                    if (!sLeftBannedNumberList.contains(id))
                        if (isPlayer1) {
                            score++
                            score1++
                            buttonList[id]?.let { buttonList[id-1]?.let { it1 ->
                                buttonList[id-2]?.let { it2 ->
                                    setRedButton(it,
                                        it1, it2
                                    )
                                }
                            } }
                        } else {
                            score++
                            score2++
                            buttonList[id]?.let { buttonList[id-1]?.let { it1 ->
                                buttonList[id-2]?.let { it2 ->
                                    setGreenButton(it,
                                        it1, it2
                                    )
                                }
                            } }
                        }
            if (tempActionMap[id + 6].equals("O"))
                if (tempActionMap[id + 12].equals("S"))
                    if (isPlayer1) {
                        score++
                        score1++
                        buttonList[id]?.let { buttonList[id+6]?.let { it1 ->
                            buttonList[id+12]?.let { it2 ->
                                setRedButton(it,
                                    it1, it2
                                )
                            }
                        } }
                    } else {
                        score++
                        score2++
                        buttonList[id]?.let { buttonList[id+6]?.let { it1 ->
                            buttonList[id+12]?.let { it2 ->
                                setGreenButton(it,
                                    it1, it2
                                )
                            }
                        } }
                    }
            if (tempActionMap[id - 6].equals("O"))
                if (tempActionMap[id - 12].equals("S"))
                    if (isPlayer1) {
                        score++
                        score1++
                        buttonList[id]?.let { buttonList[id-6]?.let { it1 ->
                            buttonList[id-12]?.let { it2 ->
                                setRedButton(it,
                                    it1, it2
                                )
                            }
                        } }
                    } else {
                        score++
                        score2++
                        buttonList[id]?.let { buttonList[id-6]?.let { it1 ->
                            buttonList[id-12]?.let { it2 ->
                                setGreenButton(it,
                                    it1, it2
                                )
                            }
                        } }
                    }
            if (tempActionMap[id + 7].equals("O"))
                if (tempActionMap[id + 14].equals("S"))
                    if (!sSouthEastBannedNumberList.contains(id))
                        if (isPlayer1) {
                            score++
                            score1++
                            buttonList[id]?.let { buttonList[id+7]?.let { it1 ->
                                buttonList[id+14]?.let { it2 ->
                                    setRedButton(it,
                                        it1, it2
                                    )
                                }
                            } }
                        } else {
                            score++
                            score2++
                            buttonList[id]?.let { buttonList[id+7]?.let { it1 ->
                                buttonList[id+14]?.let { it2 ->
                                    setGreenButton(it,
                                        it1, it2
                                    )
                                }
                            } }
                        }
            if (tempActionMap[id - 7].equals("O"))
                if (tempActionMap[id - 14].equals("S"))
                    if (!sNorthWestBannedNumberList.contains(id))
                        if (isPlayer1) {
                            score++
                            score1++
                            buttonList[id]?.let { buttonList[id-7]?.let { it1 ->
                                buttonList[id-14]?.let { it2 ->
                                    setRedButton(it,
                                        it1, it2
                                    )
                                }
                            } }
                        } else {
                            score++
                            score2++
                            buttonList[id]?.let { buttonList[id-7]?.let { it1 ->
                                buttonList[id-14]?.let { it2 ->
                                    setGreenButton(it,
                                        it1, it2
                                    )
                                }
                            } }
                        }
            if (tempActionMap[id + 5].equals("O"))
                if (tempActionMap[id + 10].equals("S"))
                    if (!sSouthWestBannedNumberList.contains(id))
                        if (isPlayer1) {
                            score++
                            score1++
                            buttonList[id]?.let { buttonList[id+5]?.let { it1 ->
                                buttonList[id+10]?.let { it2 ->
                                    setRedButton(it,
                                        it1, it2
                                    )
                                }
                            } }
                        } else {
                            score++
                            score2++
                            buttonList[id]?.let { buttonList[id+5]?.let { it1 ->
                                buttonList[id+10]?.let { it2 ->
                                    setGreenButton(it,
                                        it1, it2
                                    )
                                }
                            } }
                        }
            if (tempActionMap[id - 5].equals("O"))
                if (tempActionMap[id - 10].equals("S"))
                    if (!sNorthEastBannedNumberList.contains(id))
                        if (isPlayer1) {
                            score++
                            score1++
                            buttonList[id]?.let { buttonList[id-5]?.let { it1 ->
                                buttonList[id-10]?.let { it2 ->
                                    setRedButton(it,
                                        it1, it2
                                    )
                                }
                            } }
                        } else {
                            score++
                            score2++
                            buttonList[id]?.let { buttonList[id-5]?.let { it1 ->
                                buttonList[id-10]?.let { it2 ->
                                    setGreenButton(it,
                                        it1, it2
                                    )
                                }
                            } }
                        }
        }

        if(tempActionMap[id].equals("O")){
            if(tempActionMap[id+1].equals("S") && tempActionMap[id-1].equals("S"))
                if(!oRightLeftBannedNumber.contains(id))
                    if(isPlayer1){
                        score++
                        score1++
                        buttonList[id]?.let { buttonList[id+1]?.let { it1 ->
                            buttonList[id-1]?.let { it2 ->
                                setRedButton(it,
                                    it1, it2
                                )
                            }
                        } }
                    }
                    else{
                        score++
                        score2++
                        buttonList[id]?.let { buttonList[id+1]?.let { it1 ->
                            buttonList[id-1]?.let { it2 ->
                                setGreenButton(it,
                                    it1, it2
                                )
                            }
                        } }
                    }
            if(tempActionMap[id+6].equals("S") && tempActionMap[id-6].equals("S"))
                if(isPlayer1){
                    score++
                    score1++
                    buttonList[id]?.let { buttonList[id+6]?.let { it1 ->
                        buttonList[id-6]?.let { it2 ->
                            setRedButton(it,
                                it1, it2
                            )
                        }
                    } }
                }
                else{
                    score++
                    score2++
                    buttonList[id]?.let { buttonList[id+6]?.let { it1 ->
                        buttonList[id-6]?.let { it2 ->
                            setGreenButton(it,
                                it1, it2
                            )
                        }
                    } }
                }
            if(tempActionMap[id+7].equals("S") && tempActionMap[id-7].equals("S"))
                if(!oCrossBannedNumbers1.contains(id))
                    if(isPlayer1){
                        score++
                        score1++
                        buttonList[id]?.let { buttonList[id+7]?.let { it1 ->
                            buttonList[id-7]?.let { it2 ->
                                setRedButton(it,
                                    it1, it2
                                )
                            }
                        } }
                    }
                    else{
                        score++
                        score2++
                        buttonList[id]?.let { buttonList[id+7]?.let { it1 ->
                            buttonList[id-7]?.let { it2 ->
                                setGreenButton(it,
                                    it1, it2
                                )
                            }
                        } }
                    }
            if(tempActionMap[id+5].equals("S") && tempActionMap[id-5].equals("S"))
                if(!oCrossBannedNumbers2.contains(id))
                    if(isPlayer1){
                        score++
                        score1++
                        buttonList[id]?.let { buttonList[id+5]?.let { it1 ->
                            buttonList[id-5]?.let { it2 ->
                                setRedButton(it,
                                    it1, it2
                                )
                            }
                        } }
                    }
                    else{
                        score++
                        score2++
                        buttonList[id]?.let { buttonList[id+5]?.let { it1 ->
                            buttonList[id-5]?.let { it2 ->
                                setGreenButton(it,
                                    it1, it2
                                )
                            }
                        } }
                    }
        }
        if(score == tempScore)
            changeTurn()
        else{
            tempScore = score
            scoreText1.text = score1.toString()
            scoreText2.text = score2.toString()
        }
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun changeTurn(){
            if(isPlayer1){
                isPlayer1 = false
                isPlayer2 = true
                view1.visibility = View.INVISIBLE
                view2.visibility = View.VISIBLE
                view1.background = resources.getDrawable(R.drawable.rounded,resources.newTheme())
                view2.background = resources.getDrawable(R.drawable.green_rounded,resources.newTheme())
            }
            else if(isPlayer2){
                isPlayer1 = true
                isPlayer2 = false
                view1.visibility = View.VISIBLE
                view2.visibility = View.INVISIBLE
                view1.background = resources.getDrawable(R.drawable.green_rounded,resources.newTheme())
                view2.background = resources.getDrawable(R.drawable.rounded,resources.newTheme())
            }
    }
    fun reset(view:View){
        score = 0
        score1= 0
        score2 = 0
        tempScore = 0
        scoreText1.text= "0"
        scoreText2.text = "0"
        for(i in 1..36){
            buttonList[i]?.isEnabled = true
            buttonList[i]?.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            buttonList[i]?.text = ""
            buttonList[i]?.tag = null
        }
        isPlayer1 = true
        isPlayer2 = false
        tempActionMap.clear()
        buttonId = 0
    }

    private fun isGameOver():Boolean{
        var total = 0
        for(i in 1..36){
            if(buttonList[i]?.text != ""){
                total++
            }
        }
        if(total == 36)
            return true
        return false
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
            if(score1 > score2)
                winText.text = "𝕆𝕪𝕦𝕟 𝔹𝕚𝕥𝕥𝕚\n" + String.format("𝕂𝔸ℤ𝔸ℕ𝔸ℕ\t%s",name1)
            else if(score1 < score2)
                winText.text = "𝕆𝕪𝕦𝕟 𝔹𝕚𝕥𝕥𝕚\n" + String.format("𝕂𝔸ℤ𝔸ℕ𝔸ℕ\t%s",name2)
            else
                winText.text = "𝕆𝕪𝕦𝕟 𝔹𝕚𝕥𝕥𝕚\n" + "𝔹𝔼ℝ𝔸𝔹𝔼ℝ𝔼"
            infoText.text = String.format("\n%s : %s   %s : %s",name1,score1,name2,score2) + "\n𝕋𝕖𝕜𝕣𝕒𝕣 𝕆𝕪𝕟𝕒𝕞𝕒𝕜 𝕚𝕤𝕥𝕖𝕣 𝕞𝕚𝕤𝕚𝕟𝕚𝕫?"

        btnRestart.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SosGameActivity::class.java)
            intent.putExtra("Name1",name1)
            intent.putExtra("Name2",name2)
            startActivity(intent)
            this.finish()
        })
        btnCancel.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@SosGameActivity,MainActivity::class.java))
            this.finish()
        })
    }

    private fun setRedButton(button1:Button,button2:Button,button3:Button){
        if(button1.tag == null){
            button1.tag = "RED"
            button1.setBackgroundColor(resources.getColor(R.color.IndianRed))
        }
        if(button2.tag == null){
            button2.tag = "RED"
            button2.setBackgroundColor(resources.getColor(R.color.IndianRed))
        }
        if(button3.tag == null){
            button3.tag = "RED"
            button3.setBackgroundColor(resources.getColor(R.color.IndianRed))
        }
        if(button1.tag == "GREEN"){
            button1.tag = "YELLOW"
            button1.setBackgroundColor(resources.getColor(R.color.Yellow_DarkGoldenrod))
        }
        if(button2.tag == "GREEN"){
            button2.tag = "YELLOW"
            button2.setBackgroundColor(resources.getColor(R.color.Yellow_DarkGoldenrod))
        }
        if(button3.tag == "GREEN"){
            button3.tag = "YELLOW"
            button3.setBackgroundColor(resources.getColor(R.color.Yellow_DarkGoldenrod))
        }
    }
    private fun setGreenButton(button1:Button,button2:Button,button3:Button){
        if(button1.tag == null){
            button1.tag = "GREEN"
            button1.setBackgroundColor(resources.getColor(R.color.DarkOliveGreen))
        }
        if(button2.tag == null){
            button2.tag = "GREEN"
            button2.setBackgroundColor(resources.getColor(R.color.DarkOliveGreen))
        }
        if(button3.tag == null){
            button3.tag = "GREEN"
            button3.setBackgroundColor(resources.getColor(R.color.DarkOliveGreen))
        }
        if(button1.tag == "RED"){
            button1.tag = "YELLOW"
            button1.setBackgroundColor(resources.getColor(R.color.Yellow_DarkGoldenrod))
        }
        if(button2.tag == "RED"){
            button2.tag = "YELLOW"
            button2.setBackgroundColor(resources.getColor(R.color.Yellow_DarkGoldenrod))
        }
        if(button3.tag == "RED"){
            button3.tag = "YELLOW"
            button3.setBackgroundColor(resources.getColor(R.color.Yellow_DarkGoldenrod))
        }
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
        infoText.text = resources.getText(R.string.info_sos)
        btnOk.setOnClickListener(View.OnClickListener {
            isShowInfoCard = cbDontShowAgain.isChecked
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putBoolean("dontShowAgainSos",cbDontShowAgain.isChecked)
            editor.apply()
            dialog.dismiss()
        })
    }
}