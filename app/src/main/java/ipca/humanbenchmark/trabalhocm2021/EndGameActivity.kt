package ipca.humanbenchmark.trabalhocm2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ipca.humanbenchmark.trabalhocm2021.databinding.ActivityEndGameBinding

class EndGameActivity : AppCompatActivity() {
    lateinit var binding: ActivityEndGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.getBundleExtra("score_id")
        val score = bundle?.getString("score")

        binding = ActivityEndGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.points.text = score

        binding.mainmenu.setOnClickListener {
            val intent= Intent(this@EndGameActivity, GamesActivity::class.java)
            startActivity(intent)
        }
    }
}