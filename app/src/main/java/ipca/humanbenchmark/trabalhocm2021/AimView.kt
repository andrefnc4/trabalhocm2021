package ipca.humanbenchmark.trabalhocm2021

import android.graphics.Point
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AimView : AppCompatActivity() {
    lateinit var gameView : AimTrainerActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aim_trainer)

        val display = windowManager.defaultDisplay
        var size = Point()
        display.getSize(size)

        gameView = AimTrainerActivity(this, size.x, size.y)
        val start = findViewById<Button>(R.id.button_aimt)

        start.setOnClickListener{
            setContentView(gameView)
        }

    }

    override fun onResume() {
        super.onResume()
        gameView.resume()
    }


}