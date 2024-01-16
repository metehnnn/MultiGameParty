package com.example.multigameparty.sharkHunterGame

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.example.multigameparty.R

class Bullet(res:Resources) {
    var x = 0f
    var y = 0f
    var width = 0f
    var height = 0f
    var bullet:Bitmap

    init {
        bullet = BitmapFactory.decodeResource(res, R.drawable.harpoon1)
        width = bullet.width.toFloat()
        height = bullet.height.toFloat()
        width /= 4
        height /= 4
        width *= GameView.screenRatioX
        height *= GameView.screenRatioY.toInt()
        bullet = Bitmap.createScaledBitmap(bullet,width.toInt(),height.toInt(),false)
    }
    fun getCollisionShape(): Rect {
        return  Rect(x.toInt(),y.toInt(), (x+width).toInt(), (y+height).toInt())
    }
}