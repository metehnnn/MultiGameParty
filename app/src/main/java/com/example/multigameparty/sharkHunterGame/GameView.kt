package com.example.multigameparty.sharkHunterGame

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import android.widget.TextView
import com.example.multigameparty.MainActivity
import com.example.multigameparty.R
import com.example.multigameparty.SharkHunterGameActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Random

class GameView(activity: SharkHunterGameActivity, private val screenX: Int, private val screenY: Int) :
    SurfaceView(activity), Runnable {
    private var thread: Thread? = null
    private var isPlaying = false
    private var isGameOver = false
    private var score = 0
    private val background1: Background
    private val background2: Background
    private val paint: Paint
    private val paintHighScore: Paint
    private var swim:Swimming
    private var random: Random
    private var enemys = arrayListOf<Enemy>()
    private var bullets = ArrayList<Bullet>()
    private var activity:Activity
    private var mMediaPlayer: MediaPlayer? = null
    private var mMediaPlayerKill: MediaPlayer? = null
    private lateinit var sharedPreferences:SharedPreferences
    companion object{
        var screenRatioX: Float = 0.0f
        var screenRatioY: Float = 0.0f
    }

    init {
        this.activity = activity
        if (context != null) {
            sharedPreferences = context.getSharedPreferences("game",Context.MODE_PRIVATE)
        }
        if(mMediaPlayer == null && mMediaPlayerKill == null){
            mMediaPlayer = MediaPlayer.create(activity,R.raw.hunt_shark_effect)
            mMediaPlayerKill = MediaPlayer.create(activity,R.raw.kill_effect)
        }

        screenRatioX = 1920f / screenX
        screenRatioY = 1080f / screenY
        background1 = Background(screenX, screenY, resources)
        background2 = Background(screenX, screenY, resources)
        swim = Swimming(this,screenY,resources)
        background2.x = screenX
        paint = Paint()
        paintHighScore = Paint()
        paint.textSize = 128f
        paint.color = Color.WHITE
        paintHighScore.textSize = 64f
        paintHighScore.color = Color.WHITE
        for(i in 0..1){
            val enemy = Enemy(resources)
            enemys.add(enemy)
        }
        random = Random()
    }

    override fun run() {
        while (isPlaying) {
            update()
            draw()
            sleep()
        }
    }

    private fun update() {
        background1.x -= (10 * screenRatioX).toInt()
        background2.x -= (10 * screenRatioX).toInt()
        if (background1.x + background1.background.width < 0) {
            background1.x = screenX
        }
        if (background2.x + background2.background.width < 0) {
            background2.x = screenX
        }
        if(swim.isGoingUp){
            swim.y -= 30 * screenRatioY.toInt()
        }
        else{
            swim.y += 30 * screenRatioY.toInt()
        }
        if(swim.y < 0)
            swim.y = 0f
        if(swim.y > screenY - swim.heigth)
            swim.y = screenY - swim.heigth

        val trash = ArrayList<Bullet>()
        for(bullet in bullets){
           if(bullet.x > screenX)
               trash.add(bullet)
            bullet.x += 50 * screenRatioX
            for(enemy in enemys){
                if(Rect.intersects(enemy.getCollisionShape(),bullet.getCollisionShape())){
                    mMediaPlayer!!.start()
                    score++
                    enemy.x = -500f
                    bullet.x = (screenX + 500).toFloat()
                    enemy.wasShot = true
                }
            }
        }
        for(bullet in trash){
            bullets.remove(bullet)
        }
        for(enemy in enemys){
            enemy.x -= enemy.speed
            enemy.x -= enemy.speed
            if(enemy.x + enemy.width < 0){
                val bound = (30 * screenRatioX).toInt()
                enemy.speed = random.nextInt(bound)
                if(enemy.speed < 10 * screenRatioX)
                    enemy.speed = (10 * screenRatioX).toInt()
                enemy.x = screenX.toFloat()
                enemy.y = random.nextInt((screenY - enemy.height).toInt()).toFloat()
                enemy.wasShot = false
            }
            if(Rect.intersects(enemy.getCollisionShape(),swim.getCollisionShape())){
                isGameOver = true
                return
            }
        }
    }

    private fun draw() {
        if (holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            canvas.drawBitmap(
                background1.background,
                background1.x.toFloat(),
                background1.y.toFloat(),
                paint
            )
            canvas.drawBitmap(
                background2.background,
                background2.x.toFloat(),
                background2.y.toFloat(),
                paint
            )
            for(enemy in enemys){
                canvas.drawBitmap(
                    enemy.getEnemy(),
                    enemy.x,
                    enemy.y,
                    paint
                )
            }
            canvas.drawText(score.toString() + "",screenX / 2f,164f,paint)
            canvas.drawText("ℍ𝕚𝕘𝕙 𝕊𝕔𝕠𝕣𝕖:"+ ""+sharedPreferences.getInt("highScore",0),screenX / 10f,164f,paintHighScore)
            if(isGameOver){
                isPlaying = false
                mMediaPlayerKill!!.start()
                canvas.drawBitmap(
                    swim.getDead(),
                    swim.x,
                    swim.y,
                    paint
                )
                saveHighScore()
                holder.unlockCanvasAndPost(canvas)
                openWinDialog()
                return
            }

            canvas.drawBitmap(
                swim.getSwim(),
                swim.x,
                swim.y,
                paint
            )
            for(bullet in bullets){
                canvas.drawBitmap(
                    bullet.bullet,
                    bullet.x,
                    bullet.y,
                    paint
                )
            }
            for(enemy in enemys){
                canvas.drawBitmap(
                    enemy.enemy1,
                    enemy.x,
                    enemy.y,
                    paint
                )
            }
            holder.unlockCanvasAndPost(canvas)
        }
    }
    private fun saveHighScore() {
        if(sharedPreferences.getInt("highScore",0) < score){
            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("highScore",score)
            editor.apply()
        }
    }

    private fun sleep() {
        try {
            Thread.sleep(17)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }

    fun resume() {
        isPlaying = true
        thread = Thread(this)
        thread!!.start()
    }

    fun pause() {
        try {
            isPlaying = false
            thread!!.join()
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            when(event.action){
              MotionEvent.ACTION_DOWN ->{
                  if(event.x < screenX / 2){
                      swim.isGoingUp = true;
                  }
              }
                MotionEvent.ACTION_UP ->{
                    swim.isGoingUp = false
                    if(event.x > screenX / 2){
                        swim.toShoot++
                    }
                }
            }
        }
        return true
    }

    fun newBullet() {
        val bullet = Bullet(resources)
        bullet.x = swim.x + swim.width
        bullet.y = swim.y + (swim.heigth / 2)
        bullets.add(bullet)
    }
    private fun openWinDialog(){
        activity.runOnUiThread(Runnable {
            val dialog = Dialog(activity)
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
                winText.text = "𝔾𝔸𝕄𝔼 𝕆𝕍𝔼ℝ!"
            infoText.text = "𝕋𝕖𝕜𝕣𝕒𝕣 𝕠𝕪𝕟𝕒𝕞𝕒𝕜 𝕚𝕤𝕥𝕖𝕣 𝕞𝕚𝕤𝕚𝕟𝕚𝕫?"

            btnRestart.setOnClickListener(View.OnClickListener {
                val intent = Intent(activity, SharkHunterGameActivity::class.java)
                activity.startActivity(intent)
                activity.finish()
            })
            btnCancel.setOnClickListener(View.OnClickListener {
                activity.finish()
                activity.startActivity(Intent(activity,MainActivity::class.java))

            })
        })


    }

}