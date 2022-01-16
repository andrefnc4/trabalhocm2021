package ipca.humanbenchmark.trabalhocm2021

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import kotlin.math.absoluteValue
import kotlin.random.Random
import ipca.humanbenchmark.trabalhocm2021.databinding.ActivityMemoryGameBinding
import androidx.core.view.isVisible

class MemoryGameActivity : AppCompatActivity() {
    lateinit var binding: ActivityMemoryGameBinding
    var podejogar = 0
    var guardar_score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoryGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val start = findViewById<Button>(R.id.start)

        //Botoes
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)

        //Cor para os botoes
        button1.setBackgroundColor(Color.BLACK)
        button2.setBackgroundColor(Color.BLACK)
        button3.setBackgroundColor(Color.BLACK)
        button4.setBackgroundColor(Color.BLACK)
        button5.setBackgroundColor(Color.BLACK)
        button6.setBackgroundColor(Color.BLACK)
        button7.setBackgroundColor(Color.BLACK)
        button8.setBackgroundColor(Color.BLACK)
        button9.setBackgroundColor(Color.BLACK)


        var num = 0

        //Listas que guardam a sequencia e o que o jogador joga cada Rodada
        val list_para_butoes = mutableListOf<Int>()
        val list_para_jogar = mutableListOf<Int>()


        val texto = findViewById<TextView>(R.id.score_memory)
        texto.text = "Memory Game"

        //Muda as cores que foram randomizadas
        fun cpu(){
            var numero = 0
            var tamanho = list_para_butoes.size
            var distancia = 700
            num = 0

            //Limpa o que o jogador clicou na Ronda Passada
            list_para_jogar.clear()

            while (tamanho > 0) {

                var x = list_para_butoes[numero]
                tamanho = tamanho - 1
                numero = numero + 1
                distancia = distancia + 700

                //Da delay um a um a mudar de cor
                when (x) {

                    1 -> {
                        Handler().postDelayed({
                            button1.setBackgroundColor(Color.WHITE)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button1.setBackgroundColor(Color.BLACK)
                        }, 2500 + distancia.toLong())
                    }
                    2 -> {
                        Handler().postDelayed({
                            button2.setBackgroundColor(Color.WHITE)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button2.setBackgroundColor(Color.BLACK)
                        }, 2500 + distancia.toLong())
                    }
                    3 -> {
                        Handler().postDelayed({
                            button3.setBackgroundColor(Color.WHITE)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button3.setBackgroundColor(Color.BLACK)
                        }, 2500 + distancia.toLong())

                    }
                    4 -> {
                        Handler().postDelayed({
                            button4.setBackgroundColor(Color.WHITE)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button4.setBackgroundColor(Color.BLACK)
                        }, 2500 + distancia.toLong())
                    }
                    5 -> {
                        Handler().postDelayed({
                            button5.setBackgroundColor(Color.WHITE)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button5.setBackgroundColor(Color.BLACK)
                        }, 2500 + distancia.toLong())
                    }
                    6 -> {
                        Handler().postDelayed({
                            button6.setBackgroundColor(Color.WHITE)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button6.setBackgroundColor(Color.BLACK)
                        }, 2500 + distancia.toLong())
                    }
                    7 -> {
                        Handler().postDelayed({
                            button7.setBackgroundColor(Color.WHITE)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button7.setBackgroundColor(Color.BLACK)
                        }, 2500 + distancia.toLong())
                    }
                    8 -> {
                        Handler().postDelayed({
                            button8.setBackgroundColor(Color.WHITE)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button8.setBackgroundColor(Color.BLACK)
                        }, 2500 + distancia.toLong())
                    }
                    9 -> {
                        Handler().postDelayed({
                            button9.setBackgroundColor(Color.WHITE)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button9.setBackgroundColor(Color.BLACK)
                        }, 2500 + distancia.toLong())
                    }

                }
                val texto = findViewById<TextView>(R.id.score_memory)
                texto.text = "Ronda " + numero

            }


        }

        //Start começa o jogo
        start.setOnClickListener {

            //Randomiza o primeiro cubo a piscar
            doOperation(list_para_butoes)
            //Poe o cubo a piscar
            cpu()
            podejogar = 1

            val texto = findViewById<TextView>(R.id.score_memory)
            texto.text = "Decore a Ordem. Quando Parar de Piscar Clicka a Sequencia"

            start.isVisible = false
            start.isClickable = false

        }

        //Ver se o jogador clicou os botoes na sequencia certa

        binding.button1.setOnClickListener{
            list_para_jogar.add(1)
            if (list_para_jogar[num] == list_para_butoes[num]) {

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                    guardar_score++
                }
            }else{
                Perdeu(guardar_score)
            }
        }
        binding.button2.setOnClickListener {
            list_para_jogar.add(2)
            if (list_para_jogar[num] == list_para_butoes[num]) {

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                    guardar_score++
                }
            }else{
                Perdeu(guardar_score)
            }
        }
        binding.button3.setOnClickListener {
            list_para_jogar.add(3)
            if (list_para_jogar[num] == list_para_butoes[num]) {

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                    guardar_score++
                }
            }else{
                Perdeu(guardar_score)
            }
        }
        binding.button4.setOnClickListener {
            list_para_jogar.add(4)
            if (list_para_jogar[num] == list_para_butoes[num]) {

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                    guardar_score++
                }
            }else{
                Perdeu(guardar_score)
            }
        }
        binding.button5.setOnClickListener {
            list_para_jogar.add(5)
            if (list_para_jogar[num] == list_para_butoes[num]) {

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                    guardar_score++
                }
            }else{
                Perdeu(guardar_score)
            }
        }
        binding.button6.setOnClickListener {
            list_para_jogar.add(6)

            if (list_para_jogar[num] == list_para_butoes[num]) {

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                    guardar_score++
                }
            }else{
                Perdeu(guardar_score)
            }

        }
        binding.button7.setOnClickListener {
            list_para_jogar.add(7)
            if (list_para_jogar[num] == list_para_butoes[num]) {

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                    guardar_score++
                }
            }else{
                Perdeu(guardar_score)
            }
        }
        binding.button8.setOnClickListener {
            list_para_jogar.add(8)

            if (list_para_jogar[num] == list_para_butoes[num]) {

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                    guardar_score++
                }
            }else{
                Perdeu(guardar_score)
            }
        }
        binding.button9.setOnClickListener {
            list_para_jogar.add(9)

            if (list_para_jogar[num] == list_para_butoes[num]) {

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                    guardar_score++
                }
            }else{
                Perdeu(guardar_score)
            }
        }

        binding.buttonBackMemory.setOnClickListener{
            val intent= Intent(this@MemoryGameActivity, GamesActivity::class.java)
            startActivity(intent)
        }
    }

    //Randomiza
    fun doOperation(list: MutableList<Int>) {

        val texto = findViewById<TextView>(R.id.score_memory)
        val randomValues = List(1) { Random.nextInt(1, 9) }
        val yep = randomValues[0].absoluteValue
        list.add(yep)

        texto.text = "Memorize"

    }

    //Lose
    fun Perdeu(score: Int){
        //Meter endscreen , o score é o score final
        val intent= Intent(this@MemoryGameActivity, EndGameActivity::class.java)

        val b = Bundle()
        b.putString("score", " " +score.toString())
        intent.putExtra("score_id", b)
        startActivity(intent)

    }
}