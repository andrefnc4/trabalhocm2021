package ipca.humanbenchmark.trabalhocm2021

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import ipca.humanbenchmark.trabalhocm2021.databinding.ActivityLoginBinding
import ipca.humanbenchmark.trabalhocm2021.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var password : EditText

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        email = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)

        binding.buttonRegister.setOnClickListener {
            binding.textViewError.visibility = View.INVISIBLE
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        addUserToDatabase(email.text.toString(), auth.currentUser?.uid!!)
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        finish()
                        startActivity(intent)
                    } else {
                        binding.textViewError.visibility = View.VISIBLE
                        binding.textViewError.text = "Password demasiado curta ou falha de internet, tente novamente!"
                    }
                }
        }
    }

    private fun addUserToDatabase(email : String, uid : String) {
        db.collection("users").add(User(email, uid))
    }
}