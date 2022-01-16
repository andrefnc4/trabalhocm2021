package ipca.humanbenchmark.trabalhocm2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EndGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_game)

        val score = MemoryGameActivity().getValue()


    }
}