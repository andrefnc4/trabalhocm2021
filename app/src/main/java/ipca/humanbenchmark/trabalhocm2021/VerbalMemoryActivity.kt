package ipca.humanbenchmark.trabalhocm2021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ipca.humanbenchmark.trabalhocm2021.databinding.ActivityVerbalMemoryBinding

class VerbalMemoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityVerbalMemoryBinding
    var hp = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVerbalMemoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Talvez meter numa Android Cloud as palavras
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
        binding.textViewHealthPoints.text = hp.toString()
        binding.textView.text = randomValues

        binding.newButton.setOnClickListener {

            //So para a primeira palavra , ja que a lista ao inicio ta vazia
            if (gameplay_guardado.size == 0){
                gameplay_guardado.add(randomValues)
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
                    binding.textViewHealthPoints.text = hp.toString()
                    perdehp = false
                }
            }

            //Randomiza a proxima palavra
            otherStrings.shuffle()
            randomValues = otherStrings[0]
            binding.textView.text = randomValues

            //Quando perde reinicia
            if (hp <= 0){
                val intent = intent
                finish()
                startActivity(intent)
            }
        }

        binding.seenButton.setOnClickListener {
            //Se nao tiver nenhum no igual perde vida
            if (gameplay_guardado.size == 0){
                hp-=1
            }else{
                //Compara
                for (c in 0 until gameplay_guardado.size) {
                    if (gameplay_guardado[c].compareTo(randomValues) == 0) {
                        perdehp2 = false
                    }else{
                        perdehp2 = false
                        break
                    }
                }
                //Perde hp
                if (perdehp2 == true){
                    hp-=1
                    perdehp2 = false
                }
            }
            //Randomiza
            otherStrings.shuffle()
            randomValues = otherStrings[0]
            binding.textView.text = randomValues
            binding.textViewHealthPoints.text = hp.toString()

            //Perde e Reinicia
            if (hp <= 0){
                val intent = intent
                finish()
                startActivity(intent)
            }
        }
    }
}