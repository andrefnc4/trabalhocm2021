package ipca.humanbenchmark.trabalhocm2021

import android.content.Context
import android.graphics.*
import java.util.*
import android.graphics.Bitmap

class AimTrainerDiscGen {

    var bitmap : Bitmap
    var x : Float
    var y : Float
    var speed : Int = 0
    var pode : Boolean = false

    //var renew: Boolean = false
    var tStart = System.currentTimeMillis()
    var stacking : Long = 5

    var maxY : Float
    var minY : Float
    var maxX : Float
    var minX : Float

    var detectColosion = Rect()

    constructor(context: Context, screenWidth: Int, screenHeight : Int, Id : Int){
        maxX = screenWidth.toFloat()
        minX = 0F
        maxY = screenHeight.toFloat()
        minY = 0F

        bitmap = BitmapFactory.decodeResource(context.resources,R.drawable.aimtrainer)

        var generator = Random()
        y = generator.nextInt(1100).toFloat()
        x = generator.nextInt(800).toFloat()

        detectColosion = Rect(x.toInt(),y.toInt(),bitmap.width, bitmap.height)
    }

    fun update(){

        detectColosion.left = x.toInt()
        detectColosion.top = y.toInt()
        detectColosion.right = x.toInt() + bitmap.width
        detectColosion.bottom = y.toInt() + bitmap.height

        if (x == -300f){
            var generator = Random()
            y = generator.nextInt(1100).toFloat()
            x = generator.nextInt(800).toFloat()
        }
    }

    //testar
    fun transform(source: Bitmap): Float {

        val size = Math.min(source.width, source.height)
        val x2 = (source.width - size) / 2
        val y2 = (source.height - size) / 2

        val r = size / 2f

        return r
    }

    fun size_min(detectColosion_new : Rect, x_new : Float, y_new : Float){
    }
}