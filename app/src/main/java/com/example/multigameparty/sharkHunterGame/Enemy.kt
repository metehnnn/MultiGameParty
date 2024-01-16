package com.example.multigameparty.sharkHunterGame

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.example.multigameparty.R

class Enemy(res:Resources) {
    var wasShot= true
    var x = 0f
    var y= 0f
    var enemyCounter = 1
    var width = 0f
    var height = 0f
    var enemy1:Bitmap
    lateinit var enemy3:Bitmap
    lateinit var enemy4:Bitmap
    var speed = 20
    var enemyDead:Bitmap

    init {
        enemy1 = BitmapFactory.decodeResource(res, R.drawable.shark)
        width = enemy1.width.toFloat()
        height = enemy1.height.toFloat()
        width /= 6
        height /= 6
        width *= GameView.screenRatioX
        height *= GameView.screenRatioY
        enemy1 = Bitmap.createScaledBitmap(enemy1,width.toInt(),height.toInt(),false)

        enemyDead = BitmapFactory.decodeResource(res,R.drawable.blood_harpoon)
        enemyDead = Bitmap.createScaledBitmap(enemyDead,width.toInt(),height.toInt(),false)

        y = -height
    }

    fun getEnemy():Bitmap{
        if(enemyCounter == 1){
            enemyCounter++
            return enemy1
        }
        enemyCounter = 1
        return enemy1

    }
    fun getCollisionShape():Rect{
        return  Rect(x.toInt(),y.toInt(), (x+width).toInt(), (y+height).toInt())
    }
    fun getDead():Bitmap{
        return enemyDead
    }

}