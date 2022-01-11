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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoryGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val start = findViewById<Button>(R.id.start)
        val back = findViewById<Button>(R.id.buttonBack)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)

        button1.setBackgroundColor(Color.BLUE)
        button2.setBackgroundColor(Color.BLUE)
        button3.setBackgroundColor(Color.BLUE)
        button4.setBackgroundColor(Color.BLUE)
        button5.setBackgroundColor(Color.BLUE)
        button6.setBackgroundColor(Color.BLUE)
        button7.setBackgroundColor(Color.BLUE)
        button8.setBackgroundColor(Color.BLUE)
        button9.setBackgroundColor(Color.BLUE)

        button1.isClickable = false
        button2.isClickable = false
        button3.isClickable = false
        button4.isClickable = false
        button5.isClickable = false
        button6.isClickable = false
        button7.isClickable = false
        button8.isClickable = false
        button8.isClickable = false



        var podejogar = 1
        var num = 0
        val list_para_butoes = mutableListOf<Int>()
        val list_para_jogar = mutableListOf<Int>()

        //Muda as cores que foram randomizadas
        fun cpu(){
            var numero = 0
            var tamanho = list_para_butoes.size
            var distancia = 700
            num = 0

            list_para_jogar.clear()

            //POE LOSE SCREEN
            if (podejogar == 1){
                val texto = findViewById<TextView>(R.id.score_memory)
                texto.text = "Perdeu"
            }

            while (tamanho > 0) {
                //time delay

                var x = list_para_butoes[numero]
                tamanho = tamanho - 1
                numero = numero + 1
                distancia = distancia + 700

                when (x) {

                    1 -> {
                        Handler().postDelayed({
                            button1.setBackgroundColor(Color.RED)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button1.setBackgroundColor(Color.BLUE)
                        }, 2500 + distancia.toLong())
                    }
                    2 -> {
                        Handler().postDelayed({
                            button2.setBackgroundColor(Color.RED)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button2.setBackgroundColor(Color.BLUE)
                        }, 2500 + distancia.toLong())
                    }
                    3 -> {
                        Handler().postDelayed({
                            button3.setBackgroundColor(Color.RED)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button3.setBackgroundColor(Color.BLUE)
                        }, 2500 + distancia.toLong())

                    }
                    4 -> {
                        Handler().postDelayed({
                            button4.setBackgroundColor(Color.RED)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button4.setBackgroundColor(Color.BLUE)
                        }, 2500 + distancia.toLong())
                    }
                    5 -> {
                        Handler().postDelayed({
                            button5.setBackgroundColor(Color.RED)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button5.setBackgroundColor(Color.BLUE)
                        }, 2500 + distancia.toLong())
                    }
                    6 -> {
                        Handler().postDelayed({
                            button6.setBackgroundColor(Color.RED)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button6.setBackgroundColor(Color.BLUE)
                        }, 2500 + distancia.toLong())
                    }
                    7 -> {
                        Handler().postDelayed({
                            button7.setBackgroundColor(Color.RED)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button7.setBackgroundColor(Color.BLUE)
                        }, 2500 + distancia.toLong())
                    }
                    8 -> {
                        Handler().postDelayed({
                            button8.setBackgroundColor(Color.RED)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button8.setBackgroundColor(Color.BLUE)
                        }, 2500 + distancia.toLong())
                    }
                    9 -> {
                        Handler().postDelayed({
                            button9.setBackgroundColor(Color.RED)
                        }, 2000 + distancia.toLong())
                        Handler().postDelayed({
                            button9.setBackgroundColor(Color.BLUE)
                        }, 2500 + distancia.toLong())
                    }

                }
            }
            val texto = findViewById<TextView>(R.id.score_memory)
            texto.text = "Jogando"
        }

        start.setOnClickListener {
            doOperation(list_para_butoes)
            cpu()
            podejogar = 0

            val texto = findViewById<TextView>(R.id.score_memory)
            texto.text = "Jogando"

            start.isVisible = false
            start.isClickable = false

        }

        //Playing
        button1.isClickable = true
        button2.isClickable = true
        button3.isClickable = true
        button4.isClickable = true
        button5.isClickable = true
        button6.isClickable = true
        button7.isClickable = true
        button8.isClickable = true
        button8.isClickable = true

        binding.button1.setOnClickListener {
            list_para_jogar.add(1)
            if (list_para_jogar[num] == list_para_butoes[num]) {
                podejogar = 0

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                }
            }
        }
        binding.button2.setOnClickListener {
            list_para_jogar.add(2)
            if (list_para_jogar[num] == list_para_butoes[num]) {
                podejogar = 0

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                }
            }
        }
        binding.button3.setOnClickListener {
            list_para_jogar.add(3)
            if (list_para_jogar[num] == list_para_butoes[num]) {
                podejogar = 0

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                }
            }
        }
        binding.button4.setOnClickListener {
            list_para_jogar.add(4)
            if (list_para_jogar[num] == list_para_butoes[num]) {
                podejogar = 0

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                }
            }
        }
        binding.button5.setOnClickListener {
            list_para_jogar.add(5)
            if (list_para_jogar[num] == list_para_butoes[num]) {
                podejogar = 0

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                }
            }
        }
        binding.button6.setOnClickListener {
            list_para_jogar.add(6)

            if (list_para_jogar[num] == list_para_butoes[num]) {
                podejogar = 0

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                }
            }
        }
        binding.button7.setOnClickListener {
            list_para_jogar.add(7)
            if (list_para_jogar[num] == list_para_butoes[num]) {
                podejogar = 0

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                }
            }
        }
        binding.button8.setOnClickListener {
            list_para_jogar.add(8)

            if (list_para_jogar[num] == list_para_butoes[num]) {
                podejogar = 0

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                }
            }
        }
        binding.button9.setOnClickListener {
            list_para_jogar.add(9)

            if (list_para_jogar[num] == list_para_butoes[num]) {
                podejogar = 0

                if (list_para_jogar.size == list_para_butoes.size){
                    doOperation(list_para_butoes)
                    cpu()
                }else{
                    num++
                }
            }
        }
        binding.buttonBack.setOnClickListener {
            val intent = Intent(this@MemoryGameActivity, GamesActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    //Randomiza
    private fun doOperation(list: MutableList<Int>) {

        val texto = findViewById<TextView>(R.id.score_memory)
        val randomValues = List(1) { Random.nextInt(1, 9) }
        val yep = randomValues[0].absoluteValue
        list.add(yep)

        texto.text = "Memorize"

    }

    fun Playing(
        list_para_butoes: MutableList<Int>,
        list_para_jogar: MutableList<Int>,
        num: Int): Boolean {


        val texto = findViewById<TextView>(R.id.score_memory)
        texto.text = "Jogando"


        var num = num

        while (num < list_para_butoes.size) {

            binding.button1.setOnClickListener {
                list_para_jogar[num] = 1
                num++
            }
            binding.button1.setOnClickListener {
                list_para_jogar[num] = 2
                num++
            }
            binding.button1.setOnClickListener {
                list_para_jogar[num] = 3
                num++
            }
            binding.button1.setOnClickListener {
                list_para_jogar[num] = 4
                num++
            }
            binding.button1.setOnClickListener {
                list_para_jogar[num] = 5
                num++
            }
            binding.button1.setOnClickListener {
                list_para_jogar[num] = 6
                num++
            }
            binding.button1.setOnClickListener {
                list_para_jogar[num] = 7
                num++
            }
            binding.button1.setOnClickListener {
                list_para_jogar[num] = 8
                num++
            }

            if (list_para_jogar[num] != list_para_butoes[num]) {
                return false

            }
        }
        return true
    }
}