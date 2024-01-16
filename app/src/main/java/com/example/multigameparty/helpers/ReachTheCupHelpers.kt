package com.example.multigameparty.helpers

import android.content.Context
import android.content.Intent
import android.view.View
import com.example.multigameparty.R
import com.example.multigameparty.ReachTheExitGameActvity
import kotlinx.android.synthetic.main.activity_main.view.txtTitle
import kotlinx.android.synthetic.main.button_layout.view.*

class ReachTheCupHelpers(context: Context,view: View) {
    init {
        initUI(context,view)
    }

    private fun initUI(context: Context,view: View) {
        view.txtTitle.text = context.getString(R.string.hopscotch)
        view.btnPlay.setOnClickListener(View.OnClickListener {
            context.startActivity(Intent(context,ReachTheExitGameActvity::class.java))
        })

    }
}