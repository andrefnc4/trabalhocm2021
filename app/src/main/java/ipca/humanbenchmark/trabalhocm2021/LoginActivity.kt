package ipca.humanbenchmark.trabalhocm2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.humanbenchmark.trabalhocm2021.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.buttonLogin.setOnClickListener {
            binding.textViewError.visibility = View.INVISIBLE
            auth.signInWithEmailAndPassword(
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this@LoginActivity, GamesActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        binding.textViewError.visibility = View.VISIBLE
                        binding.textViewError.text = "Password errada ou falha de internet"
                    }
                }
        }

        binding.buttonCreateAccount.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}