package com.example.multigameparty.helpers

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.multigameparty.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.button_layout.view.*
import kotlinx.android.synthetic.main.detail_layout.*
import kotlinx.android.synthetic.main.detail_layout.view.*

class JerryGameHelpers(context: Context,view: View) {
    private val buttonList = ArrayList<Button>()
    private var difficulty:Int = 0
    init {
        initUI(context,view)
    }

    private fun initUI(context: Context, view:View) {
        buttonList.add(view.btnEasy)
        buttonList.add(view.btnNormal)
        buttonList.add(view.btnHard)
        view.rlLayout.visibility = View.VISIBLE
        view.rlLayoutMultiOrSinglePlayer.visibility = View.INVISIBLE
        view.txtTitle.text = context.getString(R.string.catch_the_jerry)
        view.btnEasy.setOnClickListener(View.OnClickListener {
            for(i in buttonList){
                i.setBackgroundColor(Color.WHITE)
            }
            view.btnEasy.setBackgroundColor(context.resources.getColor(R.color.lime_green))
            difficulty = 500
        })
        view.btnNormal.setOnClickListener(View.OnClickListener {
            for(i in buttonList){
                i.setBackgroundColor(Color.WHITE)
            }
            view.btnNormal.setBackgroundColor(context.resources.getColor(R.color.lime_green))
            difficulty = 200
        })
        view.btnHard.setOnClickListener(View.OnClickListener {
            for(i in buttonList){
                i.setBackgroundColor(Color.WHITE)
            }
            view.btnHard.setBackgroundColor(context.resources.getColor(R.color.lime_green))
            difficulty = 50
        })
        view.btnStart.setOnClickListener(View.OnClickListener {
                if(difficulty == 0){
                    Toast.makeText(context,"Lütfen zorluk derecesi seçiniz!",Toast.LENGTH_LONG).show()
                }
                else{
                    val intent = Intent(context, TimerActivity::class.java)
                    intent.putExtra("Difficultly",difficulty)
                    context.startActivity(intent)
            }


        })
    }
}