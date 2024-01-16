package com.example.multigameparty.sharkHunterGame

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.example.multigameparty.R

class Swimming(gameView: GameView,screenY: Int, res: Resources) {
    var x = 0f
    var y = 0f
    var fathomCounter = 0
    var width = 0f
    var heigth = 0f
    var swim1: Bitmap
    var swim2: Bitmap
    var shoot1: Bitmap
    var deadDiver : Bitmap
    var isGoingUp = false
    var toShoot = 0
    var shootCounter = 1
    var gameView:GameView

    init {
        this.gameView = gameView
        swim1 = BitmapFactory.decodeResource(res, R.drawable.diver1)
        swim2 = BitmapFactory.decodeResource(res, R.drawable.diver1)
        width = swim1.width.toFloat()
        heigth = swim1.height.toFloat()
        width /= 4
        heigth /= 4
        heigth *= GameView.screenRatioY.toInt()
        swim1 = Bitmap.createScaledBitmap(swim1,width.toInt(),heigth.toInt(),false)
        swim2 = Bitmap.createScaledBitmap(swim2,width.toInt(),heigth.toInt(),false)

        shoot1 = BitmapFactory.decodeResource(res,R.drawable.diver1)
        shoot1 = Bitmap.createScaledBitmap(shoot1,width.toInt(),heigth.toInt(),false)

        deadDiver = BitmapFactory.decodeResource(res,R.drawable.bubble)
        deadDiver = Bitmap.createScaledBitmap(deadDiver,width.toInt(),heigth.toInt(),false)
        y = (screenY / 2).toFloat()
        x= (64 * GameView.screenRatioX)

    }
    fun getSwim():Bitmap{
        if(toShoot != 0){
            shootCounter = 1
            toShoot--
            gameView.newBullet()
            return shoot1
        }
        if(fathomCounter == 0){
            fathomCounter++
            return swim1
        }
        fathomCounter--
        return swim2
    }
    fun getCollisionShape(): Rect {
        return  Rect(x.toInt(),y.toInt(), (x+width).toInt(), (y+heigth).toInt())
    }
    fun getDead():Bitmap{
        return deadDiver
    }

}