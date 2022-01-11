package ipca.humanbenchmark.trabalhocm2021

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.CountDownTimer
import androidx.core.view.isVisible
import hallianinc.opensource.timecounter.StopWatch
import kotlin.random.Random
import ipca.humanbenchmark.trabalhocm2021.databinding.ActivityReactionGameBinding

class ReactionGameActivity : AppCompatActivity() {
    lateinit var binding: ActivityReactionGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReactionGameBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //StopWatch é de uma library , por isso tens que meter as cenas que tao nos build gradle e no settings gradle
        //É um cronometro basicamente
        val stopwatch_pc = StopWatch(binding.textViewCronometer)
        val stopwatch_user = StopWatch(binding.textViewFinalResult)

        binding.textViewCronometer.isVisible = false
        binding.textViewFinalResult.isVisible = false

        binding.reactionView.isClickable = false

        var ta_clicado = false

        binding.startButton.setOnClickListener{

            binding.startButton.isVisible = false

            //Timer para clicares
            object : CountDownTimer(5000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.textViewCountdown.setText(" " + millisUntilFinished / 1000)
                    //here you can have your logic to set text to edittext
                }

                override fun onFinish() {
                    binding.textViewCountdown.setText("Click When You See Green!")
                    binding.reactionView.isClickable = true
                    binding.textViewFinalResult.isVisible = false
                    var timer = Randomize().toLong()

                    object : CountDownTimer(timer, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            stopwatch_pc.start();

                            binding.reactionView.setOnClickListener{

                                if (ta_clicado == false){
                                    binding.reactionView.setBackgroundColor(Color.RED)

                                    //se clicar antes do verde
                                    val intent = intent
                                    finish()
                                    startActivity(intent)

                                }
                                if (ta_clicado == true){

                                    var resultado = stopwatch_user.getTime() * 0.01
                                    stopwatch_user.stop()

                                    binding.reactionView.isClickable = false
                                    binding.textViewFinalResult.text = resultado.toString()
                                }
                            }
                        }
                        override fun onFinish() {
                            binding.textViewFinalResult.isVisible = true
                            binding.reactionView.setBackgroundColor(Color.GREEN)
                            stopwatch_pc.stop()
                            ta_clicado = true
                            stopwatch_user.start()

                            binding.buttonBack2.setOnClickListener {
                                val intent = Intent(this@ReactionGameActivity, GamesActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }.start()
                }
            }.start()
        }
    }

    //Randomiza
    fun Randomize(): Float {

        var randomValues = Random.nextFloat() + Random.nextFloat() + Random.nextFloat()
        randomValues = randomValues * 1000

        return randomValues
    }
}