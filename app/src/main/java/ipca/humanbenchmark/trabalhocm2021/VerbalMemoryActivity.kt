package ipca.humanbenchmark.trabalhocm2021

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ipca.humanbenchmark.trabalhocm2021.databinding.ActivityVerbalMemoryBinding


class VerbalMemoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityVerbalMemoryBinding
    var hp = 3
    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVerbalMemoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Lista de Palavras para usar
        val otherStrings = mutableListOf<String>("one", "two", "three", "four" , "five","six","seven","eigth","nine","ten"
            ,"eleven","twelve","thirteen","fourteen","fiveteen","sixteen","seventeen","eigthteen","nineteen","twenty")


        //Guarda as palavras que ja apareceram
        val gameplay_guardado = mutableListOf<String>()

        //Randomiza as palavras
        otherStrings.shuffle()

        //A primeira palavra da string que foi randomizada
        var randomValues = otherStrings[0]

        //Variaveis para Permissao
        var perdehp = false
        var perdehp2 = false

        //Texto
        binding.HealthPoints.text = hp.toString()
        binding.textView.text = randomValues

        //Butao new , para novas palavras
        binding.newButao.setOnClickListener {

            //Só para a primeira palavra , já que a lista ao inicio tá vazia
            if (gameplay_guardado.size == 0){
                gameplay_guardado.add(randomValues)
                score++
            }else{

                //Compara a palavra que ta no screen com as que ja tiveram
                for (c in 0 until gameplay_guardado.size) {

                    if (gameplay_guardado[c].compareTo(randomValues) == 0) {
                        perdehp = true
                        //é igual
                    } else {
                        gameplay_guardado.add(randomValues)
                        //nao é
                    }
                }

                //Perde vida
                if (perdehp == true){
                    hp-=1
                    binding.HealthPoints.text = hp.toString()
                    perdehp = false
                }else{
                    score++
                }

            }

            //Randomiza a proxima palavra
            otherStrings.shuffle()
            randomValues = otherStrings[0]
            binding.textView.text = randomValues

            //Quando perde
            if (hp <= 0){
                finish()

                val intent= Intent(this@VerbalMemoryActivity, EndGameActivity::class.java)

                val b = Bundle()
                b.putString("score", " " +score.toString())
                intent.putExtra("score_id", b)
                startActivity(intent)
            }

        }

        //Butao seen , para palavras vistas
        binding.seen.setOnClickListener {

            //Se nao tiver nenhum no igual perde vida
            if (gameplay_guardado.size == 0){
                hp-=1
            }else{
                //Compara
                for (c in 0 until gameplay_guardado.size) {

                    if (gameplay_guardado[c].compareTo(randomValues) == 0) {
                        perdehp2 = false
                    }else{
                        perdehp2 = true
                        break
                    }
                }

                //Perde hp
                if (perdehp2 == true){
                    hp-=1
                    perdehp2 = false
                }else{
                    score++
                }
            }

            //Randomiza
            otherStrings.shuffle()
            randomValues = otherStrings[0]
            binding.textView.text = randomValues
            binding.HealthPoints.text = hp.toString()


            //Perde
            if (hp <= 0){
                finish()

                val intent= Intent(this@VerbalMemoryActivity, EndGameActivity::class.java)

                val b = Bundle()
                b.putString("score", " " + score.toString())
                intent.putExtra("score_id", b)
                startActivity(intent)
            }


        }

        binding.buttonBack3.setOnClickListener{
            val intent= Intent(this@VerbalMemoryActivity, GamesActivity::class.java)
            startActivity(intent)
        }
    }
}