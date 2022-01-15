package ipca.humanbenchmark.trabalhocm2021

import android.R
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class AimTrainer : SurfaceView, Runnable {

    var playing = false
    lateinit var gameThread: Thread

    var i : Int = 1
    var id_disco : Int = 0

    var stacking : Long = 30
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
        //Perdeu e vai pro main
        context.startActivity(Intent(context, MainActivity::class.java))
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread.start()
    }

    fun update() {
        var screenWidth2: Int = 0
        var screenHeight2: Int = 0

        val tEnd = System.currentTimeMillis()
        val tDelta = tEnd - tStart
        var elapsedSeconds = tDelta / 1000

        if (elapsedSeconds == stacking){
            playing = false
            //endscreen c points
        }else{
            for (e in disc) {
                e.update()
            }
        }
    }

    fun draw() {
        if (surfaceHolder.surface.isValid) {

            canvas = surfaceHolder.lockCanvas()
            canvas?.drawColor(Color.WHITE)

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
                MotionEvent.ACTION_UP -> {
                    System.out.println("Touching up!")
                }
                MotionEvent.ACTION_MOVE -> {
                    System.out.println("Sliding your finger around on the screen.")
                }
            }
        }
        return true
    }
}