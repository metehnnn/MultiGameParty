package com.example.multigameparty.helpers

import android.content.Context
import android.content.Intent
import android.view.View
import com.example.multigameparty.R
import com.example.multigameparty.SharkHunterGameActivity
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.button_layout.*
import kotlinx.android.synthetic.main.button_layout.view.*

class SharkHunterGameHelpers(context: Context, view: View) {
    init {
        view.txtTitle.text = context.getString(R.string.shark_hunter_game)
        view.btnPlay.setOnClickListener(View.OnClickListener {
            context.startActivity(Intent(context, SharkHunterGameActivity::class.java))
        })
    }
}