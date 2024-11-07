package br.unisanta.androidloginfirebase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.androidloginfirebase.databinding.ActivityMainBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Firebase.firestore

        binding.btnSalvar.setOnClickListener{
            val name = binding.edtName.text.toString()
            val age = binding.edtAge.text.toString().toInt()
            val time = FieldValue.serverTimestamp()
            val client = hashMapOf(
                "name" to name,
                "idade" to  age,
                "registro" to time
            )
            db.collection("clientes")
                .add(client)
                .addOnSuccessListener {
                    Toast.makeText(this, "Boa mlk", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Mandou mal mlk", Toast.LENGTH_SHORT).show()
                }

        }

        }
    }