package ipca.humanbenchmark.trabalhocm2021

import android.R
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AimTrainerActivity : SurfaceView, Runnable {

    //database
    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    var playing = false
    lateinit var gameThread: Thread

    var i : Int = 1
    var id_disco : Int = 0

    //Stacking é os segundos que demora até acabar o jogo
    var stacking : Long = 10
    lateinit var surfaceHolder: SurfaceHolder
    var canvas: Canvas? = null
    lateinit var paint: Paint

    //pontos
    var points = 0
    var tStart = System.currentTimeMillis()
    var disc = arrayListOf<AimTrainerDiscGen>()

    constructor(
        context: Context?,
        screenWidth: Int,
        screenHeight: Int
    ) : super(context) {
        init(
            context,
            screenWidth,
            screenHeight
        )
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    fun init(
        context: Context?,
        screenWidth: Int = 0,
        screenHeight: Int = 0
    ) {
        surfaceHolder = holder

        paint = Paint()

        //Inicializa o disco
        for (index in 0..0) {
            context?.let { AimTrainerDiscGen(it, screenWidth, screenHeight,id_disco) }?.let { disc.add(it) }
        }

    }

    override fun run() {
        while (playing) {
            update()
            draw()
            //control()
        }

        var i = Intent(context, EndGameActivity::class.java)
        val b = Bundle()
        b.putString("score", " " + points)
        i.putExtra("score_id", b)
        context.startActivity(i)

        //Perdeu e vai pro main menu
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread.start()
    }

    fun update() {

        val tEnd = System.currentTimeMillis()
        val tDelta = tEnd - tStart
        var elapsedSeconds = tDelta / 1000

        //Se chegar ao fim dos segundos acaba o jogo
        if (elapsedSeconds == stacking){
            playing = false
        }else{
            for (e in disc) {
                e.update()
            }
        }


    }

    //Desenha
    fun draw() {
        if (surfaceHolder.surface.isValid) {

            canvas = surfaceHolder.lockCanvas()
            canvas?.drawColor(Color.GRAY)

            paint.color = Color.YELLOW

            for (e in disc) {
                canvas?.drawBitmap(e.bitmap, e.x, e.y, paint)
            }


            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control() {
        Thread.sleep(17)
    }

    //Se tocar na hitbox de o disco dá 1 ponto e elimina o disco , fazendo que dá spawn a um novo
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        var touchX = event?.getX()
        var touchY = event?.getY()

        event?.let {
            when (it.action.and(MotionEvent.ACTION_MASK)) {
                MotionEvent.ACTION_DOWN -> {
                    for (d in disc) {
                        if (touchX != null && touchY != null) {
                            if (d.detectColosion.contains(touchX.toInt(), touchY.toInt())) {
                                d.x = -300f
                                points++
                            }
                        }

                    }
                }
            }
        }
        return true
    }
    fun getValue(): Int? //returns a value
    {
        return points
    }
}