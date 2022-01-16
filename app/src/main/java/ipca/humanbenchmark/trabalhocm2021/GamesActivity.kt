package ipca.humanbenchmark.trabalhocm2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ipca.humanbenchmark.trabalhocm2021.databinding.ActivityGamesBinding

class GamesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonReaction.setOnClickListener{

            val intent= Intent(this@GamesActivity, ReactionGameActivity::class.java)
            startActivity(intent)
        }

        binding.buttonMemory.setOnClickListener{
            val intent= Intent(this@GamesActivity, MemoryGameActivity::class.java)
            startActivity(intent)
        }

        binding.buttonVerbalMemory.setOnClickListener{
            val intent= Intent(this@GamesActivity, VerbalMemoryActivity::class.java)
            startActivity(intent)
        }

       binding.buttonAimTrainer.setOnClickListener {
           val intent = Intent(this@GamesActivity, AimView::class.java)
           startActivity(intent)
       }
    }
}