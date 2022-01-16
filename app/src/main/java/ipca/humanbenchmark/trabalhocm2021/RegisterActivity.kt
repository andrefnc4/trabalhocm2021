package ipca.humanbenchmark.trabalhocm2021

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var buttonSignUp : Button
    private lateinit var message : TextView


    private lateinit var auth : FirebaseAuth
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        email = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        buttonSignUp = findViewById(R.id.buttonRegister)
        message = findViewById(R.id.textViewErrorRegister)


        buttonSignUp.setOnClickListener{
            val email = email.text.toString()
            val password = password.text.toString()

            if(email.length > 5 && password.length >5) {
                signUp(email, password)
            } else{
                message.isVisible = true
                message.text = "Password demasiado curta ou falha de internet! Tente novamente."
            }
        }
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(email, auth.currentUser?.uid!!)
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@RegisterActivity,
                        "Password demasiado curta ou falha de internet! Tente novamente.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToDatabase(email : String, uid : String) {
        db.collection("user").add(User(email, uid))
    }



}