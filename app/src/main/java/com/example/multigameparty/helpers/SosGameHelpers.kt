package com.example.multigameparty.helpers

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.multigameparty.R
import com.example.multigameparty.SosGameActivity
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.detail_layout.view.*


class SosGameHelpers(context: Context, view: View) {
    private var name1 = ""
    private var name2 = ""
    init {
        initUI(context,view)
    }

    private fun initUI(context: Context, view: View) {
        view.txtTitle.text = context.getString(R.string.sos_game)
        view.rlLayoutMultiOrSinglePlayer.visibility = View.VISIBLE
        view.rlLayout.visibility = View.INVISIBLE
        view.btnSinglePlayer.visibility = View.INVISIBLE
        view.btnTwoPlayer.visibility = View.INVISIBLE
        view.edtTextPlayerName1.visibility = View.VISIBLE
        view.edtTextPlayerName2.visibility = View.VISIBLE

        view.btnStart.setOnClickListener(View.OnClickListener {
            if (view.edtTextPlayerName1.text.toString() != "" && view.edtTextPlayerName2.text.toString() !=""){
                name1 = view.edtTextPlayerName1.text.toString()
                name2 = view.edtTextPlayerName2.text.toString()
                val intent = Intent(context, SosGameActivity::class.java)
                intent.putExtra("Name1",name1)
                intent.putExtra("Name2",name2)
                context.startActivity(intent)
            }
            else{
                Toast.makeText(context,"Lütfen oyuncu isim alanlarını doldurunuz!", Toast.LENGTH_LONG).show()
            }
        })
    }
}