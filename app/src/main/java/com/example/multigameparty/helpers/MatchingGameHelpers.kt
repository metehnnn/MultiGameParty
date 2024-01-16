package com.example.multigameparty.helpers

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.multigameparty.MatchingGameActivity
import com.example.multigameparty.R
import com.example.multigameparty.TimerActivity
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_matching_game.*
import kotlinx.android.synthetic.main.button_layout.view.*
import kotlinx.android.synthetic.main.detail_layout.view.*

class MatchingGameHelpers(context: Context, view: View) {
    private val buttonList = ArrayList<Button>()
    private var gameType = 0
    private var name1 = ""
    private var name2 = ""
    init {
        initUI(context,view)
    }

    private fun initUI(context: Context, view:View) {
        buttonList.add(view.btnSinglePlayer)
        buttonList.add(view.btnTwoPlayer)

        view.txtTitle.text = context.getString(R.string.matching_game)
        view.rlLayoutMultiOrSinglePlayer.visibility = View.VISIBLE
        view.btnSinglePlayer.visibility = View.VISIBLE
        view.btnTwoPlayer.visibility = View.VISIBLE
        view.rlLayout.visibility = View.INVISIBLE
        view.edtTextPlayerName1.visibility = View.INVISIBLE
        view.edtTextPlayerName2.visibility = View.INVISIBLE
        view.btnSinglePlayer.setOnClickListener(View.OnClickListener {
            for(i in buttonList){
                i.setBackgroundColor(Color.WHITE)
            }
            view.btnSinglePlayer.setBackgroundColor(context.resources.getColor(R.color.lime_green))
            view.edtTextPlayerName1.visibility = View.INVISIBLE
            view.edtTextPlayerName2.visibility = View.INVISIBLE
            gameType = 1
        })
        view.btnTwoPlayer.setOnClickListener(View.OnClickListener {
            for(i in buttonList){
                i.setBackgroundColor(Color.WHITE)
            }
            view.btnTwoPlayer.setBackgroundColor(context.resources.getColor(R.color.lime_green))
            view.edtTextPlayerName1.visibility = View.VISIBLE
            view.edtTextPlayerName2.visibility = View.VISIBLE
            gameType = 2

        })

        view.btnStart.setOnClickListener(View.OnClickListener {
            if(gameType == 2){
                if (view.edtTextPlayerName1.text.toString() != "" && view.edtTextPlayerName2.text.toString() !=""){
                    name1 = view.edtTextPlayerName1.text.toString()
                    name2 = view.edtTextPlayerName2.text.toString()
                    val intent = Intent(context, MatchingGameActivity::class.java)
                    intent.putExtra("GameType",gameType)
                    intent.putExtra("Name1",name1)
                    intent.putExtra("Name2",name2)
                    context.startActivity(intent)
                }
                else{
                    Toast.makeText(context,"Lütfen oyuncu isim alanlarını doldurunuz!",Toast.LENGTH_LONG).show()
                }
            }
            else if(gameType == 1){
                val intent = Intent(context, MatchingGameActivity::class.java)
                intent.putExtra("GameType",gameType)
                context.startActivity(intent)
            }
            else if(gameType == 0){
                Toast.makeText(context,"Lütfen oyun türünü seçiniz.",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(context,"Bir hata oluştu. Lütfen tekrar deneyiniz.",Toast.LENGTH_LONG).show()
            }
        })
    }
    }
