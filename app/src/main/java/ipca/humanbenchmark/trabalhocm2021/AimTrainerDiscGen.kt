package ipca.humanbenchmark.trabalhocm2021

import android.content.Context
import android.graphics.*
import java.util.*
import android.graphics.Bitmap

class AimTrainerDiscGen {

    var bitmap : Bitmap
    var x : Float
    var y : Float

    var maxY : Float
    var minY : Float
    var maxX : Float
    var minX : Float

    //Detetor de colisao
    var detectColosion = Rect()

    //Constroi o disco
    constructor(context: Context, screenWidth: Int, screenHeight : Int, Id : Int){
        maxX = screenWidth.toFloat()
        minX = 0F
        maxY = screenHeight.toFloat()
        minY = 0F

        //Bitmap
        bitmap = BitmapFactory.decodeResource(context.resources,R.drawable.aimtrainer)

        //Gera as posicoes
        var generator = Random()
        y = generator.nextInt(1100).toFloat()
        x = generator.nextInt(800).toFloat()

        detectColosion = Rect(x.toInt(),y.toInt(),bitmap.width, bitmap.height)

    }

    //Se um disco for clicado da generate a um novo em outra posicao
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
}